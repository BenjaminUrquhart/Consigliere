package net.benjaminurquhart.tos.handlers;

import java.util.Arrays;
import java.util.stream.Collectors;

import net.benjaminurquhart.tos.game.ANSI;
import net.benjaminurquhart.tos.game.Game;
import net.benjaminurquhart.tos.game.GamePhase;
import net.benjaminurquhart.tos.game.entities.StringTableMessage;

public class ClientMessageHandler extends MessageHandler {
	
	private String selfName;
	private Game game;
	
	public ClientMessageHandler(Game game) {
		super("Client");
		this.game = game;
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
    	case 16: onUserSelectedDayTarget(command); break;
    	case 17: onWillUpdate(command); break;
    	case 18: onDeathNoteUpdate(command); break;
    	case 19: onUserChangedTarget(command); break;
    	case 20: onCustomizationUpdate(command); break;
    	case 21: onNameSubmission(command); break;
    	case 22: onUserReported(command); break;
    	case 30: onUserJoinedLobby(command); break;
    	case 39: onLeaveGame(command); break;
    	case 62: onUserAcceptedRankedMatch(command); break;
    	case 74: onItemPurchase(command); break;
    	case 78: onUserChosePirateAttack(command); break;
    	case 79: onUserChosePotion(command); break;
    	case 82: onUserChoseHypnotistMessage(command); break;
    	case 85: onSteamLoginAttempt(command); break;
    	case 127: onKeepAlive(command); break;
    	default: this.onUnhandledCommand(command); break;
    	}
    }

	private void onUserChosePirateAttack(byte[] command) {
		onUnhandledCommand(command);
		String attack = "";
		switch(command[1]) {
		case 2: attack = "Scimitar"; break;
		case 3: attack = "Pistol"; break;
		case 4: attack = "Rapier"; break;
		}
		System.out.printf("%sUser chose %s\n", ANSI.GRAY, attack);
	}
	private void onUserChoseHypnotistMessage(byte[] command) {
		StringTableMessage message = Game.STRING_TABLE.get("GAME_"+this.convertToString(command));
		System.out.printf(
				"%sUser chose Hypnotist message: '%s%s%s'\n",
				ANSI.GRAY,
				ANSI.LIGHT_GRAY,
				message.getText(),
				ANSI.GRAY
		);
	}
	private void onUserChosePotion(byte[] command) {
		onUnhandledCommand(command);
		String potion = "";
		ANSI color = null;
		switch(command[1]) {
		case 4: potion = "Healing"; color = ANSI.GREEN; break;
		case 3: potion = "Revealing"; color = ANSI.CYAN; break;
		case 2: potion = "Killing"; color = ANSI.RED; break;
		}
		System.out.printf(
				"%sUser chose %s%s%s potion\n", 
				ANSI.GRAY, 
				color,
				potion,
				ANSI.GRAY
		);
	}
	private void onUserSelectedDayTarget(byte[] command) {
		onUserSelectedTarget(command);
	}
	private void onUserAcceptedRankedMatch(byte[] command) {
		System.out.printf("%sUser accepted Ranked game\n", ANSI.GRAY);
	}
	private void onUserJoinedLobby(byte[] command) {
		
	}
	private void onUserReported(byte[] command) {
		System.out.printf("%sA report was filed for Player %d (%s)\n", ANSI.GRAY, command[1], new String(Arrays.copyOfRange(command, 3, command.length-1)));
	}
	private void onUserChangedTarget(byte[] command) {
		onUnhandledCommand(command);
	}
	private void onUserSelectedTarget(byte[] command) {
		StringTableMessage msg;
		if(command[1] == 30) {
			msg = Game.STRING_TABLE.get("GUI_YOU_CHANGED_MIND");
			System.out.printf("%s%s\n", ANSI.GRAY, msg.getText());
		}
		else {
			String msgID = "GUI_ROLE_"+game.getSelfPlayer().getRole().getID()+"_TARGETING_X1";
			if(game.getPhase() == GamePhase.NIGHT) {
				msg = Game.STRING_TABLE.get(msgID+"_FIXED");
				if(msg == null) {
					msg = Game.STRING_TABLE.get(msgID);
				}
			}
			else {
				msg = Game.STRING_TABLE.get(msgID);
			}
			System.out.printf("%s%s\n", ANSI.GRAY, msg.getText().replace("%target%", game.getPlayer(command[1]).toString()));
		}
		
	}
	private void onUserJudgedInnocent(byte[] command) {
		
	}
	private void onUserJudgedGuilty(byte[] command) {
		
	}
	private void onLeaveGame(byte[] command) {
		if(selfName == null) {
			selfName = "User";
		}
		System.out.printf("%s%s left the game%s\n", ANSI.YELLOW, selfName, ANSI.GRAY);
	}
	private void onNameSubmission(byte[] command) {
		System.out.printf("%sName selected: %s\n", ANSI.GRAY, selfName = this.convertToString(command));
	}
	private void onWhisper(byte[] command) {
		
	}
	private void onDeathNoteUpdate(byte[] command) {
		System.out.printf("%sDeath Note updated:\n%s\n", ANSI.GRAY, new String(Arrays.copyOfRange(command, 1, command.length-1)));
	}
	private void onUserVoteUpdate(byte[] command) {
		
	}
	private void onWillUpdate(byte[] command) {
		System.out.printf("%sWill updated:\n%s\n", ANSI.GRAY, new String(Arrays.copyOfRange(command, 1, command.length-1)));
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
		System.out.println(ANSI.RESET+"Customization updated:");
		String scrolledRoles = Arrays.stream(Arrays.copyOfRange(data, 6, 9))
									 .mapToInt(Integer::parseInt)
									 .mapToObj(i -> Game.SCROLLS[i == -2 ? Game.SCROLLS.length-1 : i])
									 .map(String::valueOf)
									 .collect(Collectors.joining(", "));
		System.out.println("Default Name: " + data[data.length-1]);
		System.out.println("Scrolls: " + Game.insertColors(scrolledRoles)+ANSI.GRAY);
	}

}
