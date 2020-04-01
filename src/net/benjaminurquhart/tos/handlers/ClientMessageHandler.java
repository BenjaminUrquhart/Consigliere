package net.benjaminurquhart.tos.handlers;

import java.util.Arrays;

import net.benjaminurquhart.tos.game.Game;

public class ClientMessageHandler extends MessageHandler {
	
	public ClientMessageHandler() {
		super("Client");
	}
	@Override
    public void processCommand(byte[] command) {
    	switch((int)command[0]) {
    	case 2: onLoginAttempt(command); break;
    	case 20: onCustomizationUpdate(command); break;
    	default: this.onUnhandledCommand(command); break;
    	}
    }

	private void onLoginAttempt(byte[] command) {
		int start = this.indexOf(command, (byte)0x1e)+1;
		int end = this.indexOf(command, (byte)0x1e, start);
		System.out.println("Attempting to log in as user " + new String(Arrays.copyOfRange(command, start, end)) + "...");
	}
	public void onCustomizationUpdate(byte[] command) {
		String[] data = this.convertToString(command).split(",");
		System.out.println("Customization updated:");
		String[] scrolledRoles = Arrays.stream(Arrays.copyOfRange(data, 6, 9))
									   .mapToInt(Integer::parseInt)
									   .mapToObj(i -> Game.SCROLLS[i])
									   .map(String::valueOf)
									   .toArray(String[]::new);
		System.out.println("Default Name: " + data[data.length-1]);
		System.out.println("Scrolls: " + Arrays.toString(scrolledRoles));
	}

}
