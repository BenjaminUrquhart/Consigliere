package net.benjaminurquhart.tos.handlers;

import java.util.Arrays;

import net.benjaminurquhart.tos.game.ANSI;

public abstract class MessageHandler {
	
	private String origin;
	
	public MessageHandler(String origin) {
		this.origin = origin;
	}
	
	public abstract void processCommand(byte[] command);
	
	public void parseCommands(byte[] data) {
		int start = 0, end = this.indexOf(data, (byte)0);
		if(end < 0) {
			return;
		}
		end++;
		byte[] command;
		while(start > -1 && end > -1) {
			command = Arrays.copyOfRange(data, start, end);
			this.processCommand(command);
			start = end;
			if(start >= data.length) {
				break;
			}
			end = this.indexOf(data, (byte)0, start)+1;
		}
	}

	public void onDefaultFunction(byte[] command) {
    	onUnhandledCommand(command);
    }
	protected void onUnhandledCommand(byte[] command) {
		System.out.printf("%s%s: (0x%02x %03d): %s%s\n", ANSI.GRAY, origin, command[0], ((int)command[0])&(int)0b11111111, convertToString(command), ANSI.RESET);
	}
	protected String convertToString(byte[] command) {
		return convertToString(command, true);
	}
	protected String convertToString(byte[] command, boolean trim) {
		if(trim) {
			command = Arrays.copyOfRange(command, 1, command.length-1);
		}
		StringBuilder sb = new StringBuilder();
		boolean wasUnprintable = false;
		String now = null;
		for(byte b : command) {
			if((now = new String(new byte[]{b})).matches("\\p{C}")) {
				if(!wasUnprintable && sb.length() > 0) {
					sb.append(' ');
				}
				sb.append(String.format("0x%02x ", b));
				wasUnprintable = true;
			}
			else {
				if(wasUnprintable) {
					if(now.matches("\\p{P}")) {
						sb.deleteCharAt(sb.length()-1);
					}
					wasUnprintable = false;
				}
				sb.append((char)b);
			}
		}
		return sb.toString();
	}
	protected int indexOf(byte[] array, byte element) {
		return this.indexOf(array, element, 0);
	}
	protected int indexOf(byte[] array, byte element, int start) {
		if(start >= array.length) {
			throw new IllegalArgumentException(String.format("Start index (%d) must be smaller than array length (%d)", start, array.length));
		}
		for(int i = start; i < array.length; i++) {
			if(array[i] == element) {
				return i;
			}
		}
		return -1;
	}
}
