package net.benjaminurquhart.tos;

import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

import org.pcap4j.core.PcapDumper;

//import org.pcap4j.core.BpfProgram.BpfCompileMode;

import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.core.Pcaps;

import org.pcap4j.packet.*;

import net.benjaminurquhart.tos.game.ANSI;
import net.benjaminurquhart.tos.game.Game;
import net.benjaminurquhart.tos.handlers.*;

//import net.benjaminurquhart.tos.game.Game;

public class TerminalOfSalem {
	
	public static InetAddress TOS_SERVER;
	
	static {
		try {
			TOS_SERVER = Inet4Address.getByName("104.239.145.241");
		}
		catch(UnknownHostException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.print(ANSI.RESET);
		
		int i = 2;
		File file = new File("saves/tos1.pcap"), ng = new File("saves/tos1.pcapng");
		while(file.exists() || ng.exists()) {
			file = new File("saves/tos"+i+".pcap");
			ng = new File("saves/tos"+i+".pcapng");
			i++;
		}
		
		final String filename = file.getName();
		
		Game game = new Game();
		MessageHandler server = new ServerMessageHandler(game), client = new ClientMessageHandler(game);
		PromiscuousMode mode = PromiscuousMode.NONPROMISCUOUS;
		PcapDumper dumper;
		PcapHandle handle;
		boolean live = true;
		if(args.length > 0) {
			if(args[0].equalsIgnoreCase("--file")) {
				if(args.length == 1) {
					System.out.println("Please provide a file name.");
					return;
				}
				live = false;
			}
			else if(args[0].equalsIgnoreCase("--promiscuous")) {
				mode = PromiscuousMode.NONPROMISCUOUS;
			}
		}
		if(live) {
			if(file.exists()) {
				file.delete();
			}
			PcapNetworkInterface captureInterface = Pcaps.findAllDevs().get(0);
			/*
			for(PcapNetworkInterface iface : Pcaps.findAllDevs()) {
				System.out.printf("%s (%s): %s\n", iface.getName(), iface.getAddresses(), iface.getDescription());
			}*/
			System.out.printf(
					"Capturing on interface %s (%s): %s...\n", 
					captureInterface.getName(), 
					captureInterface.getAddresses(), 
					captureInterface.getDescription()
			);
			handle = captureInterface.openLive(1<<16, mode, 10);
			dumper = handle.dumpOpen(filename);
			System.out.println("Writing to " + filename);
		}
		else {
			System.out.println("Reading file " + args[1] + "...");
			handle = Pcaps.openOffline(args[1]);
			dumper = null;
		}
		//handle.setFilter("(((ip[2:2] - ((ip[0]&0xf)<<2)) - ((tcp[12]&0xf0)>>2)) != 0)", BpfCompileMode.OPTIMIZE);
		IpV4Packet ipv4Packet;
		TcpPacket packet;
		Packet tmp;
		
		Set<Long> clientSeq = new HashSet<>(), serverSeq = new HashSet<>();
		
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			if(dumper != null) {
				System.out.printf("\n\n%sSaved session as %s\n", ANSI.RESET, filename);
				dumper.close();
			}
			handle.close();
		}));
		while(true) {
			try {
				tmp = handle.getNextPacketEx();
			}
			catch(Exception e) {
				break;
			}
			ipv4Packet = tmp.get(IpV4Packet.class);
			packet = tmp.get(TcpPacket.class);
			if(packet == null || ipv4Packet == null) {
				continue;
			}
			if(!packet.getHeader().getPsh()) {
				continue;
			}
			if(ipv4Packet.getHeader().getSrcAddr().equals(TOS_SERVER)) {
				if(!serverSeq.add(packet.getHeader().getSequenceNumberAsLong())) {
					System.out.printf("%sIgnoring packet retransmission...\n", ANSI.GRAY);
					continue;
				}
				server.parseCommands(packet.getPayload().getRawData());
			}
			else if(ipv4Packet.getHeader().getDstAddr().equals(TOS_SERVER)) {
				if(!clientSeq.add(packet.getHeader().getSequenceNumberAsLong())) {
					System.out.printf("%sIgnoring packet retransmission...\n", ANSI.GRAY);
					continue;
				}
				client.parseCommands(packet.getPayload().getRawData());
			}
			else {
				continue;
			}
			if(live) {
				dumper.dump(tmp);
			}
		}
	}

}
