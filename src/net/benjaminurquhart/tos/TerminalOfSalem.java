package net.benjaminurquhart.tos;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

//import org.pcap4j.core.BpfProgram.BpfCompileMode;

import org.pcap4j.core.PcapHandle;
//import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.core.Pcaps;

import org.pcap4j.packet.*;

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
		Class.forName("net.benjaminurquhart.tos.game.Game");
		MessageHandler server = new ServerMessageHandler(), client = new ClientMessageHandler();
		
		PcapHandle handle = Pcaps.openOffline("tos2.pcapng");//Pcaps.getDevByAddress(InetAddress.getByName("192.168.254.16")).openLive(1<<16, PromiscuousMode.NONPROMISCUOUS, 10);
		//handle.setFilter("(((ip[2:2] - ((ip[0]&0xf)<<2)) - ((tcp[12]&0xf0)>>2)) != 0)", BpfCompileMode.OPTIMIZE);
		IpV4Packet ipv4Packet;
		TcpPacket packet;
		Packet tmp;
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
				server.parseCommands(packet.getPayload().getRawData());
			}
			else if(ipv4Packet.getHeader().getDstAddr().equals(TOS_SERVER)) {
				client.parseCommands(packet.getPayload().getRawData());
			}
		}
		handle.close();
	}

}