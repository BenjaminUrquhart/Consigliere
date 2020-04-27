package net.benjaminurquhart.tos.handlers;

import java.util.Arrays;
import java.util.stream.Collectors;

import net.benjaminurquhart.tos.game.ANSI;
import net.benjaminurquhart.tos.game.Game;
import net.benjaminurquhart.tos.game.GamePhase;
import net.benjaminurquhart.tos.game.entities.Player;
import net.benjaminurquhart.tos.game.entities.StringTableMessage;

public class ClientMessageHandler extends MessageHandler {
	
	private int potion = -1;
	
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
    	case 12: onUserSelectedSecondTarget(command); break;
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
    	case 60: onUserSelectedLobby(command); break;
    	case 62: onUserAcceptedRankedMatch(command); break;
    	case 74: onItemPurchase(command); break;
    	case 77: onUserSelectedTaunt(command); break;
    	case 78: onUserChosePirateAction(command); break;
    	case 79: onUserChosePotion(command); break;
    	case 82: onUserChoseHypnotistMessage(command); break;
    	case 85: onSteamLoginAttempt(command); break;
    	case 127: onKeepAlive(command); break;
    	default: this.onUnhandledCommand(command); break;
    	}
    }

	private void onUserSelectedTaunt(byte[] command) {
		// TODO Auto-generated method stub
	}
	private void onUserSelectedLobby(byte[] command) {
		game.setMode(Game.GAME_MODE_ID_TABLE.get((int)command[1]));
	}
	private void onUserChosePirateAction(byte[] command) {
		//onUnhandledCommand(command);
		StringTableMessage msg;
		String action;
		if(game.getSelfPlayer().getRole().equals(Game.ROLE_TABLE.get("Pirate"))) {
			action = "ATTACK";
		}
		else {
			action = "DEFEND";
		}
		msg = Game.STRING_TABLE.get("GUI_PIRATE_"+action+command[1]+"NOTICE");
		System.out.printf("%s%s%s\n", ANSI.GREEN, msg.getText().replace("%name%", String.valueOf(game.getSelfPlayer().getTarget())), ANSI.RESET);
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
		//onUnhandledCommand(command);
		this.potion = command[1];
		String potion = "Unknown potion: " + this.potion;
		ANSI color = ANSI.GRAY;
		switch(this.potion) {
		case 1: potion = "Healing"; color = ANSI.GREEN; break;
		case 2: potion = "Killing"; color = ANSI.RED; break;
		case 3: potion = "Revealing"; color = ANSI.CYAN; break;
		default: this.potion = -1;
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
		//onUnhandledCommand(command);
	}
	private void onUserSelectedTarget(byte[] command) {
		StringTableMessage msg;
		Player self = game.getSelfPlayer();
		if(command[1] == 30) {
			game.getSelfPlayer().setTarget(null);
			msg = Game.STRING_TABLE.get("GUI_YOU_CHANGED_MIND");
			System.out.printf("%s%s\n", ANSI.GRAY, msg.getText());
			self.setTarget(null);
		}
		else {
			String msgID = "GUI_ROLE_"+self.getRole().getID()+"_TARGETING_X";
			if(self.getTarget() != null && self.getTarget().getPosition() != command[1]) {
				if(Game.STRING_TABLE.containsKey(msgID+"1_INSTEAD")) {
					msgID += "1_INSTEAD";
				}
				else if(Game.STRING_TABLE.containsKey(msgID+"2")) {
					msgID += "2";
				}
			}
			else {
				msgID += "1";
			}
			self.setTarget(game.getPlayer(command[1]));
			if(command[1] == self.getPosition()) {
				msgID += "_SELF";
			}
			if(self.getRole().equals(Game.ROLE_TABLE.get("Potion Master")) && potion > 1) {
				msgID += "_ALT" + (potion == 3 ? "1" : "2");
			}
			if(game.getPhase() == GamePhase.NIGHT) {
				msg = Game.STRING_TABLE.get(msgID+"_FIXED");
				if(msg == null) {
					msg = Game.STRING_TABLE.get(msgID);
				}
			}
			else {
				msg = Game.STRING_TABLE.get(msgID);
			}
			if(msg == null) {
				return;
			}
			System.out.printf("%s%s\n", ANSI.GRAY, msg.getText().replace("%target%", self.getTarget().getName()));
		}
	}
	private void onUserSelectedSecondTarget(byte[] command) {
		StringTableMessage msg;
		if(command[1] == 30) {
			msg = Game.STRING_TABLE.get("GUI_YOU_CHANGED_MIND");
			System.out.printf("%s%s\n", ANSI.GRAY, msg.getText());
		}
		else {
			Player self = game.getSelfPlayer();
			Player first = self.getTarget(), second = game.getPlayer(command[1]);
			String msgID = "GUI_ROLE_"+self.getRole().getID()+"_TARGETING_X1_TARGET2";
			if(game.getPhase() == GamePhase.NIGHT) {
				msg = Game.STRING_TABLE.get(msgID+"_FIXED");
				if(msg == null) {
					msg = Game.STRING_TABLE.get(msgID);
				}
			}
			else {
				msg = Game.STRING_TABLE.get(msgID);
			}
			String type = "target", action = "target";
			if(self.getRole().equals(Game.ROLE_TABLE.get("Necromancer"))) {
				if(first == self) {
					type = "ghoul";
					action = "attack";
				}
				else {
					type = "zombie";
					action = Game.STRING_TABLE.get("GUI_ACTION_VERB"+first.getRole().getID()).getText();
				}
			}
			System.out.printf(
					"%s%s\n", 
					ANSI.GRAY, 
					msg.getText().replace("%target%", second.getName())
								 .replace("%monstertype%", type)
								 .replace("%actiontype%", action)
			);
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
		System.out.printf(
				"%sDeath Note updated:\n%s\n",
				ANSI.GRAY,
				new String(Arrays.copyOfRange(command, 1, command.length-1)).replace((char)0x0d, '\n')
		);
	}
	private void onUserVoteUpdate(byte[] command) {
		
	}
	private void onWillUpdate(byte[] command) {
		System.out.printf(
				"%sWill updated:\n%s\n", 
				ANSI.GRAY, 
				new String(Arrays.copyOfRange(command, 1, command.length-1)).replace((char)0x0d, '\n')
		);
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
