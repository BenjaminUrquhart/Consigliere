package net.benjaminurquhart.tos.handlers;

import java.util.Arrays;

import net.benjaminurquhart.tos.game.Game;

public class ClientMessageHandler extends MessageHandler {
	
	public ClientMessageHandler() {
		super("Client");
	}
	@Override
    public void processCommand(byte[] command) {
    	switch(((int)command[0])&(int)0b11111111) {
    	case 2: onLoginAttempt(command); break;
    	case 3: onChatBoxMessage(command); break;
    	case 17: onWillUpdate(command); break;
    	case 20: onCustomizationUpdate(command); break;
    	case 85: onSteamLoginAttempt(command); break;
    	case 74: onItemPurchase(command); break;
    	case 127: onKeepAlive(command); break;
    	default: this.onUnhandledCommand(command); break;
    	}
    }

	private void onWillUpdate(byte[] command) {
		System.out.println("Will updated:\n" + new String(Arrays.copyOfRange(command, 1, command.length-1)));
	}
	private void onChatBoxMessage(byte[] command) {
		
	}
	private void onSteamLoginAttempt(byte[] command) {
		System.out.println("Attemping to log in via Steam...");
	}
	private void onKeepAlive(byte[] command) {
		onUnhandledCommand(command);
		
	}
	private void onItemPurchase(byte[] command) {
		onUnhandledCommand(command);
		
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
									   .mapToObj(i -> Game.SCROLLS[i == -2 ? Game.SCROLLS.length-1 : i])
									   .map(String::valueOf)
									   .toArray(String[]::new);
		System.out.println("Default Name: " + data[data.length-1]);
		System.out.println("Scrolls: " + Arrays.toString(scrolledRoles));
	}

}
