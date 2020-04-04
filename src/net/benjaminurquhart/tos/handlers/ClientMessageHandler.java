package net.benjaminurquhart.tos.handlers;

import java.util.Arrays;

import net.benjaminurquhart.tos.game.ANSI;
import net.benjaminurquhart.tos.game.Game;

public class ClientMessageHandler extends MessageHandler {
	
	private String selfName;
	
	public ClientMessageHandler() {
		super("Client");
	}
	@Override
    public void processCommand(byte[] command) {
    	switch(((int)command[0])&0xff) {
    	case 0: break;
    	case 2: onLoginAttempt(command); break;
    	case 3: onChat(command); break;
    	case 8: onWhisper(command); break;
    	case 10: onUserVoteUpdate(command); break;
    	case 11: onUserSelectedTarget(command); break;
    	case 14: onUserJudgedGuilty(command); break;
    	case 15: onUserJudgedInnocent(command); break;
    	case 17: onWillUpdate(command); break;
    	case 18: onDeathNoteUpdate(command); break;
    	case 19: onUserChangedTarget(command); break;
    	case 20: onCustomizationUpdate(command); break;
    	case 21: onNameSubmission(command); break;
    	case 22: onUserReported(command); break;
    	case 30: onUserJoinedLobby(command); break;
    	case 39: onLeaveGame(command); break;
    	case 62: onUserAcceptedRankedMatch(command); break;
    	case 79: onUserChoseMultiAction(command); break;
    	case 85: onSteamLoginAttempt(command); break;
    	case 74: onItemPurchase(command); break;
    	case 127: onKeepAlive(command); break;
    	default: this.onUnhandledCommand(command); break;
    	}
    }

	private void onUserAcceptedRankedMatch(byte[] command) {
		System.out.printf("%sUser accepted Ranked game\n", ANSI.GRAY);
	}
	private void onUserJoinedLobby(byte[] command) {
		
	}
	private void onUserReported(byte[] command) {
		System.out.printf("%sA report was filed for Player %d (%s)\n", ANSI.GRAY, command[1], new String(Arrays.copyOfRange(command, 3, command.length-1)));
	}
	private void onUserChoseMultiAction(byte[] command) {
		System.out.printf("%sUser chose action %d\n", ANSI.GRAY, command[1]);
	}
	private void onUserChangedTarget(byte[] command) {
		
	}
	private void onUserSelectedTarget(byte[] command) {
		System.out.printf("%sUser targeted Player %d\n", ANSI.GRAY, command[1]);
	}
	private void onUserJudgedInnocent(byte[] command) {
		
	}
	private void onUserJudgedGuilty(byte[] command) {
		
	}
	private void onLeaveGame(byte[] command) {
		System.out.printf("%s%s left the game%s\n", ANSI.YELLOW, selfName, ANSI.GRAY);
	}
	private void onNameSubmission(byte[] command) {
		System.out.println("Name selected: " + (selfName = new String(Arrays.copyOfRange(command, 1, command.length-1))));
	}
	private void onWhisper(byte[] command) {
		
	}
	private void onDeathNoteUpdate(byte[] command) {
		System.out.println("Death note updated:\n" + new String(Arrays.copyOfRange(command, 1, command.length-1)));
	}
	private void onUserVoteUpdate(byte[] command) {
		
	}
	private void onWillUpdate(byte[] command) {
		System.out.println("Will updated:\n" + new String(Arrays.copyOfRange(command, 1, command.length-1)));
	}
	private void onChat(byte[] command) {
		
	}
	private void onSteamLoginAttempt(byte[] command) {
		System.out.println("Attemping to log in via Steam...");
	}
	private void onKeepAlive(byte[] command) {
		//onUnhandledCommand(command);
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
