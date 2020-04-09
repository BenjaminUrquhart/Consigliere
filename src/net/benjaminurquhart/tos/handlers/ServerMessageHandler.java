package net.benjaminurquhart.tos.handlers;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import net.benjaminurquhart.tos.game.ANSI;
import net.benjaminurquhart.tos.game.Game;
import net.benjaminurquhart.tos.game.GamePhase;
import net.benjaminurquhart.tos.game.PlayerTag;
import net.benjaminurquhart.tos.game.entities.Achievement;
import net.benjaminurquhart.tos.game.entities.Faction;
import net.benjaminurquhart.tos.game.entities.Genre;
import net.benjaminurquhart.tos.game.entities.Killer;
import net.benjaminurquhart.tos.game.entities.Player;
import net.benjaminurquhart.tos.game.entities.Role;
import net.benjaminurquhart.tos.game.entities.Scroll;
import net.benjaminurquhart.tos.game.entities.StringTableMessage;
import net.benjaminurquhart.tos.game.entities.Winner;

public class ServerMessageHandler extends MessageHandler {
	
	private static final Pattern END_GAME_RESULTS = Pattern.compile("\\(([^\\)]+)\\)");
	
	private Game game;
	
    public ServerMessageHandler(Game game) {
		super("Server");
		this.game = game;
	}
    
    @Override
	public void processCommand(byte[] command) {
    	//this.onUnhandledCommand(command);
        switch (((int)command[0])&0xff) { 
                 case 0: onDefaultFunction(command); break;
                 case 1: onLoginSuccess(command); break;
                 case 2: onJoinedGameLobby(command); break;
                 case 3: onDefaultFunction(command); break;
                 case 4: onUsersJoinedLobby(command); break;
                 case 5: onUserLeftGame(command); break;
                 case 6: onChatBoxMessage(command); break;
                 case 7: onDefaultFunction(command); break;
                 case 8: onDefaultFunction(command); break;
                 case 9: onDefaultFunction(command); break;
                 case 10: onDefaultFunction(command); break;
                 case 11: onGameStartCountdown(command); break;
                 case 12: onDefaultFunction(command); break;
                 case 13: onDefaultFunction(command); break;
                 case 14: onVoteToRepickHost(command); break;
                 case 15: onDefaultFunction(command); break;
                 case 16: onDoNotSpam(command); break;
                 case 17: onGameStatus(command); break;
                 case 18: onSystemMessage(command); break;
                 case 19: onStringTableMessage(command); break;
                 case 20: onDefaultFunction(command); break;
                 case 21: onDefaultFunction(command); break;
                 case 22: onDefaultFunction(command); break;
                 case 23: onDefaultFunction(command); break;
                 case 24: onDefaultFunction(command); break;
                 case 25: onDefaultFunction(command); break;
                 case 26: onFriendUpdate(command); break;
                 case 27: onDefaultFunction(command); break;
                 case 28: onDefaultFunction(command); break;
                 case 29: onDefaultFunction(command); break;
                 case 30: onDefaultFunction(command); break;
                 case 31: onPartyInvite(command); break;
                 case 32: onJoinedParty(command); break;
                 case 33: onUsersJoinedParty(command); break;
                 case 34: onDefaultFunction(command); break;
                 case 35: onPartyChatBoxMessage(command); break;
                 case 36: onDefaultFunction(command); break;
                 case 37: onDefaultFunction(command); break;
                 case 38: onDefaultFunction(command); break;
                 case 39: onForcedLogout(command); break;
                 case 40: onReturnToHomePage(command); break;
                 case 41: onDefaultFunction(command); break;
                 case 42: onShopPurchaseSuccess(command); break;
                 case 43: onDefaultFunction(command); break;
                 case 44: onDefaultFunction(command); break;
                 case 45: onDefaultFunction(command); break;
                 case 46: onDefaultFunction(command); break;
                 case 47: onDefaultFunction(command); break;
                 case 48: onUpdatePaidCurrency(command); break;
                 case 49: onDefaultFunction(command); break;
                 case 50: onDefaultFunction(command); break;
                 case 51: onSetLastBonusWinTime(command); break;
                 case 52: onDefaultFunction(command); break;
                 case 53: onDefaultFunction(command); break;
                 case 54: onDefaultFunction(command); break;
                 case 55: onDefaultFunction(command); break;
                 case 56: onDefaultFunction(command); break;
                 case 57: onDefaultFunction(command); break;
                 case 58: onUserBecamePartyHost(command); break;
                 case 59: onDefaultFunction(command); break;
                 case 60: onDefaultFunction(command); break;
                 case 61: onDefaultFunction(command); break;
                 case 62: onUserCanInviteToParty(command); break;
                 case 63: onDefaultFunction(command); break;
                 case 64: onDefaultFunction(command); break;
                 case 65: onDefaultFunction(command); break;
                 case 66: onUpdateFriendName(command); break;
                 case 67: onDefaultFunction(command); break;
                 case 68: onDefaultFunction(command); break;
                 case 69: onDefaultFunction(command); break;
                 case 70: onDefaultFunction(command); break;
                 case 71: onJoinRankedQueue(command); break;
                 case 72: onDefaultFunction(command); break;
                 case 73: onRankedGameAvailable(command); break;
                 case 74: onUserStatistics(command); break;
                 case 75: onDefaultFunction(command); break;
                 case 76: onDefaultFunction(command); break;
                 case 77: onModeratorMessage(command); break;
                 case 78: onReferAFriendUpdate(command); break;
                 case 79: onPlayerStatistics(command); break;
                 case 80: onScrollConsumed(command); break;
                 case 81: onDefaultFunction(command); break;
                 case 82: onDefaultFunction(command); break;
                 case 83: onPromotionPopup(command); break;
                 case 84: onDefaultFunction(command); break;
                 case 85: onDefaultFunction(command); break;
                 case 86: onDefaultFunction(command); break;
                 case 87: onDefaultFunction(command); break;
                 case 88: onCurrencyMultiplier(command); break;
                 case 89: onDefaultFunction(command); break;

                 case 90: onPickNames(command); break;
                 case 91: onNamesAndPositionsOfUsers(command); break;
                 case 92: onRoleAndPosition(command); break;
                 case 93: onStartNight(command); break;
                 case 94: onStartDay(command); break;
                 case 95: onWhoDiedAndHow(command); break;
                 case 96: onStartDiscussion(command); break;
                 case 97: onStartVoting(command); break;
                 case 98: onStartDefenseTransition(command); break;
                 case 99: onStartJudgement(command); break;
                 case 100: onTrialFoundGuilty(command); break;
                 case 101: onTrialFoundNotGuilty(command); break;
                 case 102: onLookoutNightAbilityMessage(command); break;
                 case 103: onUserVoted(command); break;
                 case 104: onUserCanceledVote(command); break;
                 case 105: onUserChangedVote(command); break;
                 case 106: onUserDied(command); break;
                 case 107: onResurrection(command); break;
                 case 108: onTellRoleList(command); break;
                 case 109: onUserChosenName(command); break;
                 case 110: onOtherMafia(command); break;
                 case 111: onTellTownAmnesiacChangedRole(command); break;
                 case 112: onAmnesiacChangedRole(command); break;
                 case 113: onBroughtBackToLife(command); break;
                 case 114: onStartFirstDay(command); break;
                 case 115: onBeingJailed(command); break;
                 case 116: onJailedTarget(command); break;
                 case 117: onUserJudgementVoted(command); break;
                 case 118: onUserChangedJudgementVote(command); break;
                 case 119: onUserCanceledJudgementVote(command); break;
                 case 120: onTellJudgementVotes(command); break;
                 case 121: onExecutionerCompletedGoal(command); break;
                 case 122: onJesterCompletedGoal(command); break;
                 case 123: onMayorRevealed(command); break;
                 case 124: onMayorRevealedAndAlreadyVoted(command); break;
                 case 125: onDisguiserStoleYourIdentity(command); break;
                 case 126: onDisguiserChangedIdentity(command); break;
                 case 127: onDisguiserChangedUpdateMafia(command); break;
                 case 128: onMediumIsTalkingToUs(command); break;
                 case 129: onMediumCommunicating(command); break;
                 case 130: onTellLastWill(command); break;
                 case 131: onHowManyAbilitiesLeft(command); break;
                 case 132: onMafiaTargeting(command); break;
                 case 133: onTellJanitorTargetsRole(command); break;
                 case 134: onTellJanitorTargetsWill(command); break;
                 case 135: onSomeoneHasWon(command); break;
                 case 136: onMafiosoPromotedToGodfather(command); break;
                 case 137: onMafiosoPromotedToGodfatherUpdateMafia(command); break;
                 case 138: onMafiaPromotedToMafioso(command); break;
                 case 139: onTellMafiaAboutMafiosoPromotion(command); break;
                 case 140: onExecutionerConvertedToJester(command); break;
                 case 141: onAmnesiacBecameMafiaOrWitch(command); break;
                 case 142: onUserDisconnected(command); break;
                 case 143: onMafiaWasJailed(command); break;
                 case 144: onInvalidNameMessage(command); break;
                 case 145: onStartNightTransition(command); break;
                 case 146: onStartDayTransition(command); break;
                 case 147: onLynchUser(command); break;
                 case 148: onDeathNote(command); break;
                 case 149: onDefaultFunction(command); break;
                 case 150: onHousesChosen(command); break;
                 case 151: onFirstDayTransition(command); break;
                 case 152: onDefaultFunction(command); break;
                 case 153: onCharactersChosen(command); break;
                 case 154: onResurrectionSetAlive(command); break;
                 case 155: onStartDefense(command); break;
                 case 156: onDefaultFunction(command); break;
                 case 157: onUserLeftDuringSelection(command); break;
                 case 158: onVigilanteKilledTown(command); break;
                 case 159: onNotifyUsersOfPrivateMessage(command); break;
                 case 160: onPrivateMessage(command); break;
                 case 161: onEarnedAchievements(command); break;
                 case 162: onDefaultFunction(command); break;
                 case 163: onSpyNightAbilityMessage(command); break;
                 case 164: onOneDayBeforeStalemate(command); break;
                 case 165: onPetsChosen(command); break;
                 case 166: onFacebookShareAchievement(command); break;
                 case 167: onFacebookShareWin(command); break;
                 case 168: onDeathAnimationsChosen(command); break;
                 case 169: onFullMoonNight(command); break;
                 case 170: onIdentify(command); break;
                 case 171: onEndGameInfo(command); break;
                 case 172: onEndGameChatMessage(command); break;
                 case 173: onEndGameUserUpdate(command); break;
                 case 174: onJoinedGameLobby(command); break;
                 case 175: onRoleLotsInfoMesssage(command); break;
                 case 176: onPayPalShowApprovalPage(command); break;
                 case 177: onDefaultFunction(command); break;
                 case 178: onDefaultFunction(command); break;
                 case 179: onDefaultFunction(command); break;
                 case 180: onVampirePromotion(command); break;
                 case 181: onOtherVampires(command); break;
                 case 182: onAddVampire(command); break;
                 case 183: onCanVampiresConvert(command); break;
                 case 184: onVampireDied(command); break;
                 case 185: onVampireHunterPromoted(command); break;
                 case 186: onVampireVisitedMessage(command); break;
                 //case 186: onDefaultFunction(command); break;
                 case 187: onDefaultFunction(command); break;
                 case 188: onDefaultFunction(command); break;
                 case 189: onDefaultFunction(command); break;
                 case 190: onDefaultFunction(command); break;
                 case 191: onDefaultFunction(command); break;
                 case 192: onTransporterNotification(command); break;
                 case 193: onItemPurchased(command); break;
                 case 194: onUpdateFreeCurrency(command); break;
                 case 195: onActiveEvents(command); break;
                 case 196: onDefaultFunction(command); break;
                 case 197: onTauntResult(command); break;
                 case 198: onTauntActivated(command); break;
                 case 199: onTauntConsumed(command); break;
                 case 200: onTrackerNightAbility(command); break;
                 case 201: onAmbusherNightAbility(command); break;
                 case 202: onGuardianAngelProtection(command); break;
                 case 203: onPirateDuel(command); break;
                 case 204: onDuelTarget(command); break;
                 case 205: onPotionMasterPotions(command); break;
                 case 206: onHasNecronomicon(command); break;
                 case 207: onOtherCoven(command); break;
                 case 208: onPsychicNightAbility(command); break;
                 case 209: onTrapperNightAbility(command); break;
                 case 210: onTrapperTrapStatus(command); break;
                 case 211: onPestilenceConversion(command); break;
                 case 212: onJuggernautKillCount(command); break;
                 case 213: onCovenGotNecronomicon(command); break;
                 case 214: onGuardianAngelPromoted(command); break;
                 case 215: onVIPTarget(command); break;
                 case 216: onPirateDuelOutcome(command); break;
                 case 217: onPartyGamemodeUpdate(command); break;
                 case 218: onDefaultFunction(command); break;
                 case 219: onAccountFlags(command); break;
                 case 220: onZombieRotted(command); break;
                 case 221: onLoverTarget(command); break;
                 case 222: onPlagueSpread(command); break;
                 case 223: onRivalTarget(command); break;
                 case 224: onRankedInfo(command); break;
                 case 225: onJailorDeathNote(command); break;
                 case 226: onLoginFailure(command); break;
                 case 227: onSpyNightInfo(command); break;
                 case 228: onDefaultFunction(command); break;
                default: onUnhandledCommand(command); break;
        }
    }

	private void onVoteToRepickHost(byte[] command) {
		System.out.printf(
				"%s%d more votes are needed to repick the host\n",
				ANSI.GRAY,
				command[1]-1
		);
	}

	private void onRankedGameAvailable(byte[] command) {
		System.out.printf("%sA Ranked match is available%s\n", ANSI.GREEN, ANSI.GRAY);
	}

	private void onJoinRankedQueue(byte[] command) {
		System.out.printf(
				"%sJoined %s Ranked queue (%s seconds remaining)%s\n", 
				ANSI.GREEN, command[1] == 1 ? "Classic" : "Coven",
				new String(Arrays.copyOfRange(command, 2, command.length-1)),
				ANSI.GRAY
		);
	}

	private void onPartyGamemodeUpdate(byte[] command) {
		//onUnhandledCommand(command);
		System.out.printf("%sGame Mode updated to %s%s\n", ANSI.RESET, Game.GAME_MODE_TABLE.get((int)command[2]-1), ANSI.GRAY);
	}

	private void onUserBecamePartyHost(byte[] command) {
		System.out.printf(
				"%s%s is now the host%s\n",
				ANSI.GREEN,
				this.convertToString(command),
				ANSI.GRAY
		);
	}

	private void onUsersJoinedParty(byte[] command) {
		int seperator = this.indexOf(command, (byte)'*');
		System.out.printf("%s%s joined the party\n", ANSI.GRAY, new String(Arrays.copyOfRange(command, 1, seperator)));
	}

	private void onJoinedParty(byte[] command) {
		System.out.printf("%sJoined party%s\n", ANSI.RESET, ANSI.GRAY);
	}

	private void onUserCanInviteToParty(byte[] command) {
		System.out.printf(
				"%s%s%s can now invite others to the party%s\n", 
				ANSI.RESET,
				new String(Arrays.copyOfRange(command, 1, command.length-1)),
				ANSI.GREEN,
				ANSI.GRAY
		);
	}

	private void onPartyInvite(byte[] command) {
		int seperator = this.indexOf(command, (byte)'*');
		String username = new String(Arrays.copyOfRange(command, seperator+1, command.length-1));
		String party = new String(Arrays.copyOfRange(command, 1, seperator));
		System.out.printf("%sReceived party invite from %s%s (%s)%s\n", ANSI.GREEN, ANSI.RESET, username, party, ANSI.GRAY);
	}

	private void onPartyChatBoxMessage(byte[] command) {
		int seperator = this.indexOf(command, (byte)'*');
		String message = new String(Arrays.copyOfRange(command, seperator+1, command.length-1));
		String name = new String(Arrays.copyOfRange(command, 1, seperator));
		System.out.printf("%s%s: %s%s\n", ANSI.RESET, name, message, ANSI.GRAY);
	}

	private void onItemPurchased(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onGameStartCountdown(byte[] command) {
		System.out.println(ANSI.GREEN+"Lobby is full, game is starting..."+ANSI.GRAY);
	}

	private void onGameStatus(byte[] command) {
		int asterisk = this.indexOf(command, (byte)'*');
		int players = Integer.parseInt(new String(Arrays.copyOfRange(command, 1, asterisk)));
		int games = Integer.parseInt(new String(Arrays.copyOfRange(command, asterisk+1, command.length-1)));
		System.out.printf("%s%d players online (%d games running)%s\n", ANSI.GREEN, players, games, ANSI.GRAY);
	}

	private void onSpyNightInfo(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onLoginFailure(byte[] command) {
		System.out.println("Login failed");
	}

	private void onJailorDeathNote(byte[] command) {
		String message;
		switch(command[3]) {
		case 1: message = "No reason specified."; break;
		case 2: message = "They are known to be an evildoer."; break;
		case 3: message = "Their confession was contradictory."; break;
		case 4: message = "They are possessed and talking nonsense."; break;
		case 5: message = "They were too quiet or won't respond to questioning."; break;
		case 6: message = "They are an outsider that might turn against us."; break;
		case 7: message = "I'm using my own discretion."; break;
		default: message = "Unknown jailor message: " + command[3]; break;
		}
		System.out.printf(
				"%sDeath Note: %s%s%s\n",
				ANSI.RESET,
				ANSI.YELLOW,
				message,
				ANSI.GRAY
		);
	}

	private void onRankedInfo(byte[] command) {
		int[] stats = Arrays.stream(this.convertToString(command, true).split(","))
							.mapToInt(Integer::parseInt)
							.toArray();
		//System.out.println(Arrays.toString(stats));
		boolean coven = stats[0]/6 == 2;
		
		int practiceGamesRequired = stats[9];
		int practiceGamesPlayed = stats[1];
		int rankedGamesDrawn = stats[12];
		int rankedGamesLost = stats[11];
		int rankedGamesWon = stats[10];
		
		int seasonHigh = stats[14];
		int elo = stats[15];
		
		System.out.println("-----------------------------------------------");
		System.out.println((coven ? "Coven" : "Classic") + " Ranked Statistics:");
		System.out.printf("Practice Games: %d/%d\n", practiceGamesPlayed, practiceGamesRequired);
		if(practiceGamesRequired <= practiceGamesPlayed) {
			System.out.printf("Current Elo: %d\nSeason High: %d\n", elo, seasonHigh);
			System.out.printf("Wins: %d | Losses: %d | Draws: %d\n", rankedGamesWon, rankedGamesLost, rankedGamesDrawn);
		}
		System.out.println("-----------------------------------------------");
	}

	private void onRivalTarget(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onPlagueSpread(byte[] command) {
		for(int i = 1; i < command.length-1; i++) {
			System.out.printf("%s%s was infected with the plague\n", ANSI.GRAY, game.getPlayer(command[i]));
		}
	}

	private void onLoverTarget(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onZombieRotted(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onAccountFlags(byte[] command) {
		//onUnhandledCommand(command);
		
	}

	private void onPirateDuelOutcome(byte[] command) {
		if(command[1]-1 == command[2]) {
			System.out.printf("%sYou won the duel!%s\n", ANSI.GREEN, ANSI.RESET);
		}
		else {
			System.out.printf("%sYou lost the duel!%s\n", ANSI.RED, ANSI.RESET);
		}
	}

	private void onVIPTarget(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onGuardianAngelPromoted(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onCovenGotNecronomicon(byte[] command) {
		//onUnhandledCommand(command);
		Player player = game.getPlayer(command[2]);
		// Previous holder died, passed on to next member
		// Otherwise, first time Coven has Necronomicon (Night 3)
		if(command[1] == 2) {
			System.out.printf(
					"%s%s (%s%s%s) has died while possessing the Necronomicon!%s\n",
					ANSI.RED,
					player,
					ANSI.COVEN,
					player.getRole().getName(),
					ANSI.RED,
					ANSI.GRAY
			);
			player = game.getPlayer(command[3]);
		}
		System.out.printf(
				"%s%s (%s%s%s) possesses the Necronomicon. Their powers are enhanced!%s\n",
				ANSI.GREEN,
				player,
				ANSI.COVEN,
				player.getRole().getName(),
				ANSI.GREEN,
				ANSI.GRAY
		);
	}

	private void onJuggernautKillCount(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onPestilenceConversion(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onTrapperTrapStatus(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onTrapperNightAbility(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onPsychicNightAbility(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onOtherCoven(byte[] command) {
		this.tellFactionMembers(command, Game.FACTION_TABLE.get("Coven"));
	}

	private void onHasNecronomicon(byte[] command) {
		//onUnhandledCommand(command);
	}

	private void onPotionMasterPotions(byte[] command) {
		System.out.printf(
				"%sPotion Status%s:\n%sHeal%s: %d/3\n%sReveal%s: %d/3\n%sKill%s: %d/3%s\n",
				ANSI.COVEN,
				ANSI.RESET,
				ANSI.GREEN,
				ANSI.RESET,
				4-command[1],
				ANSI.CYAN,
				ANSI.RESET,
				4-command[2],
				ANSI.RED,
				ANSI.RESET,
				4-command[3],
				ANSI.GRAY
		);
	}

	private void onDuelTarget(byte[] command) {
		System.out.printf("%sDueling %s...\n", ANSI.GRAY, game.getPlayer(command[1]));
	}

	private void onPirateDuel(byte[] command) {
		System.out.printf(
				"%s%sThe pirate duel has roleblocked you!%s%s\n",
				ANSI.toTrueColorBackground(Color.LIGHT_GRAY),
				ANSI.BLACK,
				ANSI.RESET,
				ANSI.GRAY
		);
	}

	private void onGuardianAngelProtection(byte[] command) {
		System.out.printf("%sThe Guardian Angel was watching over %s%s%s\n", ANSI.GREEN, ANSI.RESET, game.getPlayer(command[1]), ANSI.GRAY);
	}

	private void onAmbusherNightAbility(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onTrackerNightAbility(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onTauntConsumed(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onTauntActivated(byte[] command) {
		//onUnhandledCommand(command);
		System.out.printf("%s%s was taunted with %s\n", ANSI.GRAY, game.getPlayer(command[1]), Game.TAUNTS[command[2]-1]);
	}

	private void onTauntResult(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onActiveEvents(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onUpdateFreeCurrency(byte[] command) {
		System.out.printf("%sMerit Points updated: %s%s\n", ANSI.LIGHT_GRAY, this.convertToString(command), ANSI.GRAY);
	}

	private void onTransporterNotification(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onVampireVisitedMessage(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onVampireHunterPromoted(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onVampireDied(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onCanVampiresConvert(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onAddVampire(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onOtherVampires(byte[] command) {
		this.tellFactionMembers(command, Game.FACTION_TABLE.get("Vampire"));
	}

	private void onVampirePromotion(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onPayPalShowApprovalPage(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onRoleLotsInfoMesssage(byte[] command) {
		//onUnhandledCommand(command);
		
	}

	private void onEndGameUserUpdate(byte[] command) {
		//onUnhandledCommand(command);
		
	}

	private void onEndGameChatMessage(byte[] command) {
		onChatBoxMessage(command);
	}

	private void onEndGameInfo(byte[] command) {
		//onUnhandledCommand(command);
		System.out.printf("%s---------------Game Information----------------\n", ANSI.RESET);
		String infoString = this.convertToString(Arrays.copyOfRange(command, this.indexOf(command, (byte)'(')-1, command.length));
		Matcher matcher = END_GAME_RESULTS.matcher(infoString);
		String username, name;
		int position, roleID;
		Role role;
		
		Player player;
		
		String[] info, table = new String[15];
		while(matcher.find()) {
			info = matcher.group(1).split(",");
			username = info[1];
			name = info[0];
			
			if(info[3].length() == 1) {
				roleID = info[3].charAt(0);
			}
			else {
				roleID = Integer.parseInt(info[3].trim().substring(2), 16);
			}
			
			position = Integer.parseInt(info[2].trim().substring(2), 16);
			
			role = Game.ROLES[roleID-1];
			
			player = game.getPlayer(position);
			player.setName(username);
			player.setRole(role);
			
			table[position-1] = String.format(
					"%s%-20s %-20s %s%-20s%s",
					ANSI.RESET,
					name,
					username,
					ANSI.toTrueColor(role.getColor()),
					role.getName(),
					ANSI.GRAY
			);
		}
		for(String s : table) {
			System.out.println(s);
		}
		System.out.printf("%s-----------------------------------------------%s\n", ANSI.RESET, ANSI.GRAY);
	}

	private void onIdentify(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onFullMoonNight(byte[] command) {
		System.out.printf(
				"%s%sThere is a full moon out tonight%s%s\n",
				ANSI.WHITE,
				ANSI.toTrueColorBackground(Color.CYAN),
				ANSI.RESET,
				ANSI.GRAY
		);
	}

	private void onDeathAnimationsChosen(byte[] command) {
		//onUnhandledCommand(command);
		
	}

	private void onFacebookShareWin(byte[] command) {
		//onUnhandledCommand(command);
		System.out.printf("%sShare this win on Facebook%s\n", ANSI.BLUE, ANSI.GRAY);
	}

	private void onFacebookShareAchievement(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onPetsChosen(byte[] command) {
		//onUnhandledCommand(command);
		
	}

	private void onOneDayBeforeStalemate(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onSpyNightAbilityMessage(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onEarnedAchievements(byte[] command) {
		Achievement achievement = Game.ACHIEVEMENTS[Integer.parseInt(this.convertToString(command))];
		Role role = achievement.getRole();
		
		StringTableMessage message = Game.STRING_TABLE.get("GUI_ACHIEVEMENT_DESC"+achievement.getID());
		String name, text;
		Color color;
		
		if(role == null) {
			Genre genre = Game.GENRE_TABLE.get(achievement.getGenre());
			color = genre.getColor();
			name = genre.getID();
		}
		else {
			color = role.getColor();
			name = role.getName();
		}
		text = Game.insertColors(message.getText());
		
		System.out.printf(
				"%sReceived %s%s%s achievement: %s%s\n",
				ANSI.RESET,
				ANSI.toTrueColor(color),
				name,
				ANSI.RESET,
				text,
				ANSI.GRAY
		);
	}

	private void onPrivateMessage(byte[] command) {
		//onUnhandledCommand(command);
		String color = ANSI.toTrueColor(Color.MAGENTA);
		// Blackmailer
		if(command[1] == 3) {
			System.out.printf(
					"%sFrom %s%s%s to %s%s:%s %s%s\n",
					color,
					ANSI.GRAY,
					game.getPlayer(command[2]),
					color,
					
					ANSI.GRAY,
					game.getPlayer(command[3]),
					color,
					new String(Arrays.copyOfRange(command, 4, command.length-1)),
					ANSI.GRAY
			);
		}
		else {
			System.out.printf(
					"%s%s %s%s:%s %s%s\n",
					color,
					command[1] == 0x01 ? "To" : "From",
					ANSI.GRAY,
					game.getPlayer(command[2]),
					color,
					new String(Arrays.copyOfRange(command, 3, command.length-1)),
					ANSI.GRAY
			);
		}
	}

	private void onNotifyUsersOfPrivateMessage(byte[] command) {
		System.out.printf(
				"%s%s%s is whispering to %s%s%s\n",
				ANSI.LIGHT_GRAY,
				game.getPlayer(command[1]),
				ANSI.toTrueColor(Color.MAGENTA),
				ANSI.LIGHT_GRAY,
				game.getPlayer(command[2]),
				ANSI.GRAY
		);
	}

	private void onVigilanteKilledTown(byte[] command) {
		onUnhandledCommand(command);
	}

	private void onUserLeftDuringSelection(byte[] command) {
		onUnhandledCommand(command);
	}

	private void onStartDefense(byte[] command) {
		System.out.println(ANSI.GRAY+"----------Defense----------");
	}

	private void onResurrectionSetAlive(byte[] command) {
		game.getPlayer(command[1]).resurrect();
	}

	private void onCharactersChosen(byte[] command) {
		//onUnhandledCommand(command);
		
	}

	private void onFirstDayTransition(byte[] command) {
		game.setPhase(GamePhase.FIRST_DAY_TRANSITION);
		System.out.println(ANSI.GRAY+"---------- Start ----------");
	}

	private void onHousesChosen(byte[] command) {
		//onUnhandledCommand(command);
		
	}

	private void onDeathNote(byte[] command) {
		System.out.printf("%sDeath Note:\n%s%s%s\n", ANSI.RESET, ANSI.RED, new String(Arrays.copyOfRange(command, 3, command.length-1)), ANSI.GRAY);
	}

	private void onLynchUser(byte[] command) {
		game.setPhase(GamePhase.EXECUTION);
		game.getPlayerOnTrial().kill(Killer.LYNCHING);
	}

	private void onStartDayTransition(byte[] command) {
		if(command.length == 2) {
			System.out.printf("%sNo deaths last night...\n", ANSI.GRAY);
		}
		for(int i = 1; i < command.length-1; i++) {
			game.getPlayer(command[i]).kill();
		}
	}

	private void onStartNightTransition(byte[] command) {
		game.setPhase(GamePhase.NIGHT_TRANSITION);
	}

	private void onInvalidNameMessage(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onMafiaWasJailed(byte[] command) {
		//onUnhandledCommand(command);
		System.out.printf(
				"%s%s (%s%s%s) was hauled off to jail!%s\n",
				ANSI.LIGHT_GRAY,
				game.getPlayer(command[1]),
				ANSI.MAFIA,
				game.getPlayer(command[1]).getRole().getName(),
				ANSI.LIGHT_GRAY,
				ANSI.GRAY
		);
	}

	private void onUserDisconnected(byte[] command) {
		//onUnhandledCommand(command);
	}

	private void onAmnesiacBecameMafiaOrWitch(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onExecutionerConvertedToJester(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onTellMafiaAboutMafiosoPromotion(byte[] command) {
		//onUnhandledCommand(command);
		Role role = Game.ROLE_TABLE.get("Mafioso");
		game.getPlayer(command[1]).setRole(role);
		System.out.printf(
				"%s was promoted to %s%s%s\n",
				game.getPlayer(command[1]),
				ANSI.toTrueColor(role.getColor()),
				role.getName(),
				ANSI.GRAY
		);
	}

	private void onMafiaPromotedToMafioso(byte[] command) {
		game.getSelfPlayer().setRole(Game.ROLE_TABLE.get("Mafioso"));
		System.out.printf(
				"%s%s%s%s%s\n",
				ANSI.toTrueColorBackground(Color.LIGHT_GRAY),
				ANSI.BLACK,
				Game.STRING_TABLE.get("GUI_PROMOTED_TO_23").getText(),
				ANSI.RESET,
				ANSI.GRAY
		);
	}

	private void onMafiosoPromotedToGodfatherUpdateMafia(byte[] command) {
		Role role = Game.ROLE_TABLE.get("Godfather");
		game.getPlayer(command[1]).setRole(role);
		System.out.printf(
				"%s was promoted to %s%s%s\n",
				game.getPlayer(command[1]),
				ANSI.toTrueColor(role.getColor()),
				role.getName(),
				ANSI.GRAY
		);
	}

	private void onMafiosoPromotedToGodfather(byte[] command) {
		game.getSelfPlayer().setRole(Game.ROLE_TABLE.get("Godfather"));
		System.out.printf(
				"%s%s%s%s%s\n",
				ANSI.toTrueColorBackground(Color.LIGHT_GRAY),
				ANSI.BLACK,
				Game.STRING_TABLE.get("GUI_PROMOTED_TO_23").getText(),
				ANSI.RESET,
				ANSI.GRAY
		);
	}

	private void onSomeoneHasWon(byte[] command) {
		//onUnhandledCommand(command);
		Winner winner = Game.WINNERS[command[1]-1];
		
		List<String> winners = new ArrayList<>();
		for(int i = 2; i < command.length-1; i++) {
			winners.add(game.getPlayer(i).getName());
		}
		System.out.printf(
				"%s%s%s\n",
				ANSI.toTrueColor(winner.getColor()),
				winner,
				ANSI.GRAY
		);
		if(winners.size() > 0) {
			System.out.printf(
					"%s%s %s won the game%s\n", 
					ANSI.GREEN,
					String.join(", ", winners),
					winners.size() == 1 ? "has" : "have",
					ANSI.GRAY
			);
		}
	}

	private void onTellJanitorTargetsWill(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onTellJanitorTargetsRole(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onMafiaTargeting(byte[] command) {
		//onUnhandledCommand(command);
		Player member = game.getPlayer(command[1]);
		Role role = member.getRole();
		if(command[3] == 30) {
			System.out.printf(
					"%s%s (%s%s%s) has changed their mind\n",
					ANSI.GRAY,
					member,
					ANSI.toTrueColor(role.getColor()),
					role.getName(),
					ANSI.GRAY
			);
		}
		else {
			StringTableMessage msg = Game.STRING_TABLE.get("GUI_FACTION_TARGETING_"+role.getID());
			String title = String.format(
					 "%s (%s%s%s)",
					 member,
					 ANSI.toTrueColor(role.getColor()),
					 role.getName(),
					 ANSI.GRAY
			);
			System.out.printf(
					"%s%s\n",
					ANSI.GRAY,
					msg.getText().replace("%name1%", title)
								 .replace("%name2%", game.getPlayer(command[3]).getName())
			);
		}
	}

	private void onHowManyAbilitiesLeft(byte[] command) {
		game.setAbilitiesLeft(command[1]-1);
	}

	private void onTellLastWill(byte[] command) {
		String will = new String(Arrays.copyOfRange(command, 3, command.length-1));
		if(!will.trim().isEmpty()) {
			System.out.printf("%sWill:\n%s%s\n\n", ANSI.RESET, Game.insertColors(will), ANSI.GRAY);
		}
		else {
			System.out.println(ANSI.RESET+"We could not find a last will\n");
		}
	}

	private void onMediumCommunicating(byte[] command) {
		System.out.printf("%sYou have opened a connection to the living!%s\n", ANSI.CYAN, ANSI.GRAY);
	}

	private void onMediumIsTalkingToUs(byte[] command) {
		System.out.printf("%sA Medium is speaking to you!%s\n", ANSI.CYAN, ANSI.GRAY);
	}

	private void onDisguiserChangedUpdateMafia(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onDisguiserChangedIdentity(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onDisguiserStoleYourIdentity(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onMayorRevealedAndAlreadyVoted(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onMayorRevealed(byte[] command) {
		System.out.printf("%s%s%s has revealed themselves as Mayor!%s\n", ANSI.RESET, game.getPlayer(command[1]), ANSI.RED, ANSI.GRAY);
		game.getPlayer(command[1]).setRole(Game.ROLE_TABLE.get("Mayor"));
	}

	private void onJesterCompletedGoal(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onExecutionerCompletedGoal(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onTellJudgementVotes(byte[] command) {
		String verb = "voted ";
		Color color;
		String vote;
		switch(command[2]) {
		case 1: color = Color.RED; vote = "guilty"; break;
		case 2: color = Color.GREEN; vote = "innocent"; break;
		case 3: color = Color.CYAN; vote = "abstained"; verb = ""; break;
		default: color = Color.GRAY; vote = "???";
		}
		System.out.printf(
				"%s%s%s %s%s%s%s\n",
				ANSI.RESET,
				game.getPlayer(command[1]),
				ANSI.GREEN,
				verb,
				ANSI.toTrueColor(color),
				vote,
				ANSI.GRAY
		);
	}

	private void onUserCanceledJudgementVote(byte[] command) {
		System.out.printf("%s%s%s has canceled their vote%s\n", ANSI.RESET, game.getPlayer(command[1]), ANSI.GREEN, ANSI.GRAY);
	}

	private void onUserChangedJudgementVote(byte[] command) {
		System.out.printf("%s%s%s has changed their vote%s\n", ANSI.RESET, game.getPlayer(command[1]), ANSI.GREEN, ANSI.GRAY);
	}

	private void onUserJudgementVoted(byte[] command) {
		System.out.printf("%s%s%s has voted%s\n", ANSI.RESET, game.getPlayer(command[1]), ANSI.GREEN, ANSI.GRAY);
	}

	private void onJailedTarget(byte[] command) {
		Player jailee = game.getPlayer(command[1]);
		System.out.printf(
				"%s%sYou hauled %s off to jail!%s\n", 
				ANSI.toTrueColorBackground(Color.LIGHT_GRAY), 
				ANSI.WHITE,
				jailee,
				ANSI.RESET, 
				ANSI.GRAY
		);
	}

	private void onBeingJailed(byte[] command) {
		System.out.printf(
				"%s%sYou were hauled off to jail!%s\n", 
				ANSI.toTrueColorBackground(Color.LIGHT_GRAY), 
				ANSI.WHITE,
				ANSI.RESET, 
				ANSI.GRAY
		);
	}

	private void onStartFirstDay(byte[] command) {
		game.setPhase(GamePhase.FIRST_DAY);
	}

	private void onBroughtBackToLife(byte[] command) {
		System.out.println(ANSI.GREEN+"You were resurrected by a Retributionist!"+ANSI.GRAY);//onUnhandledCommand(command);
	}

	private void onAmnesiacChangedRole(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onTellTownAmnesiacChangedRole(byte[] command) {
		Role role = Game.ROLES[command[1]-1];
		System.out.printf(
				"%sAn amnesiac has remembered that they were like the %s%s%s\n",
				ANSI.GREEN,
				ANSI.toTrueColor(role.getColor()),
				role.getName(),
				ANSI.GRAY
		);
	}

	private void onOtherMafia(byte[] command) {
		//onUnhandledCommand(command);
		this.tellFactionMembers(command, Game.FACTION_TABLE.get("Mafia"));
	}

	private void onUserChosenName(byte[] command) {
		//onUnhandledCommand(command);
		String name = new String(Arrays.copyOfRange(command, 3, command.length-1));
		game.updatePlayerName(name, command[2]);
		System.out.printf("%s%s (%d) has joined the Town%s\n", ANSI.GREEN, name, command[2], ANSI.GRAY);
	}

	private void onTellRoleList(byte[] command) {
		System.out.println("Role List:");
		Color color;
		Role role;
		for(int i = 1; i < command.length-1; i++) {
			role = Game.ROLES[command[i]-1];
			color = role.getColor();
			System.out.printf("%s%s%s\n", ANSI.toTrueColor(color), role.getName(), ANSI.GRAY);
		}
	}

	private void onResurrection(byte[] command) {
		Player player = game.getPlayer(command[1]);
		System.out.printf(
				"%s%s (%s%s%s) was brought back to life!%s\n",
				ANSI.RESET,
				player,
				ANSI.toTrueColor(player.getRole().getColor()),
				player.getRole(),
				ANSI.RESET,
				ANSI.LIGHT_GRAY
		);
	}

	private void onUserDied(byte[] command) {
		game.getSelfPlayer().kill();
		System.out.printf("%s%sYou have died!%s%s\n", ANSI.WHITE, ANSI.toTrueColorBackground(Color.RED), ANSI.RESET, ANSI.GRAY);
	}

	private void onUserChangedVote(byte[] command) {
		//onUnhandledCommand(command);
		Player voter = game.getPlayer(command[1]), voted = game.getPlayer(command[2]);
		System.out.printf(
				"%s%s%s has changed their vote to %s%s (Vote Worth: %d)%s\n",
				ANSI.RESET,
				voter,
				ANSI.GREEN,
				ANSI.RESET,
				voted,
				command[4],
				ANSI.GRAY
		);
	}

	private void onUserCanceledVote(byte[] command) {
		System.out.printf(
				"%s%s%s has canceled their vote%s\n",
				ANSI.RESET,
				game.getPlayer(command[1]),
				ANSI.GREEN,
				ANSI.GRAY
		);
	}

	private void onUserVoted(byte[] command) {
		Player voter = game.getPlayer(command[1]), voted = game.getPlayer(command[2]);
		System.out.printf(
				"%s%s%s has voted against %s%s (Vote Worth: %d)%s\n",
				ANSI.RESET,
				voter,
				ANSI.GREEN,
				ANSI.RESET,
				voted,
				command[3],
				ANSI.GRAY
		);
	}

	private void onLookoutNightAbilityMessage(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onTrialFoundNotGuilty(byte[] command) {
		game.setPhase(GamePhase.DEEMED_NOT_GUILTY);
		System.out.printf(
				"%s%s was found %sinnocent%s by a vote of %s%s%s to %s%d%s\n",
				ANSI.RESET,
				game.getPlayerOnTrial(),
				ANSI.GREEN,
				ANSI.RESET,
				ANSI.RED,
				command[1]-1,
				ANSI.RESET,
				ANSI.GREEN,
				command[2]-1,
				ANSI.GRAY
		);
		game.clearPlayerOnTrial();
	}

	private void onTrialFoundGuilty(byte[] command) {
		game.setPhase(GamePhase.DEEMED_GUILTY);
		System.out.printf(
				"%s%s was found %sguilty%s by a vote of %s%s%s to %s%d%s\n",
				ANSI.RESET,
				game.getPlayerOnTrial(),
				ANSI.RED,
				ANSI.RESET,
				ANSI.RED,
				command[1]-1,
				ANSI.RESET,
				ANSI.GREEN,
				command[2]-1,
				ANSI.GRAY
		);
	}

	private void onStartJudgement(byte[] command) {
		System.out.printf("%sThe Town may now vote on the fate of %s%s%s\n", ANSI.GREEN, ANSI.RESET, game.getPlayerOnTrial(), ANSI.GRAY);
		System.out.println("---------Judgement---------");
	}
	private void onStartDefenseTransition(byte[] command) {
		game.placePlayerOnTrial(command[1]);
		System.out.printf(
				"%sThe Town has decided to put %s%s%s on trial%s\n",
				ANSI.GREEN,
				ANSI.RESET,
				game.getPlayerOnTrial(),
				ANSI.GREEN,
				ANSI.GRAY
		);
	}

	private void onStartVoting(byte[] command) {
		game.setPhase(GamePhase.VOTING);
		System.out.println(ANSI.GRAY+"----------Voting-----------");
	}

	private void onStartDiscussion(byte[] command) {
		game.setPhase(GamePhase.DISCUSSION);
		System.out.println(ANSI.GRAY+"--------Discussion---------");
		String msg = game.getDayAbilitiesLeftMessage();
		if(msg != null) {
			System.out.println(msg);
		}
	}

	private void onWhoDiedAndHow(byte[] command) {
		//System.out.println(ANSI.RESET+"-----------Death-----------");
		//this.onUnhandledCommand(command);
		int roleID = (command[2]&0xff)-1;
		Role role = Game.ROLE_ID_TABLE.get(roleID);
		Player player = game.getPlayer(command[1]);
		
		List<Killer> killers = new ArrayList<>();
		
		for(Killer k : player.getKillers()) {
			killers.add(k);
		}
		for(int i = 4; i < command.length-1; i++) {
			killers.add(Game.KILLERS[command[i]-1]);
		}
		String killersStr = killers.stream()
								   .map(k -> String.format("%s%s%s", ANSI.toTrueColor(k.getColor()), k.getName(), ANSI.RESET))
								   .collect(Collectors.joining(", "));
		/*
		if(lynched[command[1]-1]) {
			killers.add(String.format("%sLynching%s", ANSI.toTrueColor(Killer.DEFAULT_COLOR), ANSI.RESET));
		}*/
		System.out.printf("\n%s%s (%d) was killed%s%s\n", ANSI.RESET, player, command[1], killers.size() > 0 ? " by " : "", killersStr);
		if(role == null) {
			System.out.printf("We could not determine their role (Unknown role: %d)%s\n", roleID, ANSI.GRAY);
		}
		else if (role.getTags().contains(PlayerTag.STONED) || role.getTags().contains(PlayerTag.CLEANED)) {
			System.out.printf(
					"We could not determine their role (%s%s%s)%s\n",
					ANSI.toTrueColor(role.getColor()),
					role.getTags().stream().map(String::valueOf).collect(Collectors.joining(", ")),
					ANSI.RESET,
					ANSI.GRAY
			);
			if(player.getRole() != null) {
				player.setRole(role);
			}
		}
		else {
			System.out.printf("Role: %s%s%s\n", ANSI.toTrueColor(role.getColor()), role.getName(), ANSI.GRAY);
			player.setRole(role);
		}
	}

	private void onStartDay(byte[] command) {
		game.setPhase(GamePhase.DAY_TRANSITION);
		System.out.println(ANSI.GRAY+"------------Day------------");
	}

	private void onStartNight(byte[] command) {
		game.setPhase(GamePhase.NIGHT);
		System.out.println(ANSI.GRAY+"-----------Night-----------");
		String msg = game.getNightAbilitiesLeftMessage();
		if(msg != null) {
			System.out.println(msg);
		}
	}

	private void onRoleAndPosition(byte[] command) {
		game.setPhase(GamePhase.GET_ROLE);
		Role role = Game.ROLES[command[1]-1];
		Player self = game.getPlayer(command[2]);
		game.setSelfPosition(command[2]);
		self.setRole(role);
		System.out.printf("%sRole: %s%s%s\nPosition: %d%s\n", ANSI.RESET, ANSI.toTrueColor(role.getColor()), role.getName(), ANSI.RESET, command[2], ANSI.GRAY);
	}

	private void onNamesAndPositionsOfUsers(byte[] command) {
		game.updatePlayerName(new String(Arrays.copyOfRange(command, 2, command.length-1)), command[1]);
	}

	private void onPickNames(byte[] command) {
		game.setPhase(GamePhase.PICK_NAME);
		System.out.println("Name selection has begun");
	}

	private void onCurrencyMultiplier(byte[] command) {
		onUnhandledCommand(command);
	}

	private void onPromotionPopup(byte[] command) {
		onUnhandledCommand(command);
	}

	private void onScrollConsumed(byte[] command) {
		//onUnhandledCommand(command);
		Scroll scroll = Game.SCROLLS[Integer.parseInt(new String(Arrays.copyOfRange(command, 1, command.length-1)))];
		System.out.printf("%sScroll used: %s%s\n", ANSI.RESET, Game.insertColors(scroll.toString()), ANSI.GRAY);
	}

	private void onPlayerStatistics(byte[] command) {
		onUnhandledCommand(command);
	}

	private void onReferAFriendUpdate(byte[] command) {
		//onUnhandledCommand(command);
	}

	private void onModeratorMessage(byte[] command) {
		System.err.println("Moderator Message: " + this.convertToString(command));
	}

	private void onUserStatistics(byte[] command) {
		onUnhandledCommand(command);
	}

	private void onUpdateFriendName(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onSetLastBonusWinTime(byte[] command) {
		//onUnhandledCommand(command);
	}

	private void onUpdatePaidCurrency(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onShopPurchaseSuccess(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onReturnToHomePage(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onForcedLogout(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onFriendUpdate(byte[] command) {
		//onUnhandledCommand(command);
		
	}

	private void onStringTableMessage(byte[] command) {
		String id = String.valueOf((command[1]&0xff)-1);
		StringTableMessage msg = Game.STRING_TABLE.get("GAME_"+id);
		System.out.printf("%s%s%s%s\n", ANSI.WHITE, ANSI.toTrueColorBackground(msg.getColor()), msg.getText(), ANSI.RESET, ANSI.GRAY);
	}

	private void onSystemMessage(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onDoNotSpam(byte[] command) {
		System.out.println(ANSI.YELLOW+"Please do not spam the chat"+ANSI.GRAY);
	}

	private void onChatBoxMessage(byte[] command) {
		int offset = command[1] == (byte)0xff ? 1 : 0;
		int position = command[1+offset];
		boolean alive;
		
		Player player;
		
		String name, role = " (???)";
		if(position == 45) {
			name = ANSI.CYAN+"Medium";
			alive = true;
			role = "";
		}
		else if(position == 30) {
			name = ANSI.YELLOW+"Jailor";
			alive = true;
			role = "";
		}
		else {
			player = game.getPlayer(position);
			alive = player.isAlive();
			name = player.getName();
			if(player.getRole() != null) {
				role = String.format(
						" (%s%s%s)",
						ANSI.toTrueColor(player.getRole().getColor()),
						player.getRole().getName(),
						ANSI.RESET
				);
			}
		}
		System.out.printf(
				"%s%s%s%s: %s%s\n", 
				alive ? ANSI.RESET : ANSI.RED, 
				name, 
				ANSI.RESET, 
				role,
				Game.insertColors(new String(Arrays.copyOfRange(command, 2+offset, command.length-1))), 
				ANSI.GRAY
		);
	}

	private void onUserLeftGame(byte[] command) {
		System.out.printf("%s%s left the game%s\n", ANSI.YELLOW, game.getPlayer(command[3]), ANSI.GRAY);
		if(game.getPhase() == GamePhase.LOBBY) {
			for(int i = command[3]; i < 15; i++) {
				game.getPlayers()[i-1] = game.getPlayers()[i];
			}
			game.getPlayers()[14] = null;
		}
	}

	private void onUsersJoinedLobby(byte[] command) {
		int asterisk = this.indexOf(command, (byte)'*');
		String name = new String(Arrays.copyOfRange(command, 3, asterisk));
		System.out.printf(
				"Player #%d (%s) joined the game%s\n",
				command[asterisk+1], 
				name,
				command[1] == 0x02 ? " (Host)" : ""
		);
		game.updatePlayerName(name, command[asterisk+1]);
	}

	private void onJoinedGameLobby(byte[] command) {
		//onUnhandledCommand(command);
		game.setPhase(GamePhase.LOBBY);
		System.out.printf("%sJoined game lobby (Game Mode: %s)%s\n", ANSI.RESET, Game.GAME_MODE_TABLE.get((int)command[2]), ANSI.GRAY);
	}

	private void onLoginSuccess(byte[] command) {
		System.out.println("Log in succeeded");
		//onUnhandledCommand(command);
	}
	
	private void tellFactionMembers(byte[] command, Faction faction) {
		System.out.printf("%s%s%s Members:\n", ANSI.valueOf(faction.getName().toUpperCase()), faction.getName(), ANSI.RESET);
		Player member;
		Role role;
		for(int i = 1; i < command.length-1; i+=2) {
			member = game.getPlayer(command[i]);
			role = Game.ROLES[command[i+1]-1];
			member.setRole(role);
			System.out.printf("%s (%s%s%s)\n", member, ANSI.toTrueColor(role.getColor()), role.getName(), ANSI.RESET);
		}
		System.out.print(ANSI.GRAY);
	}
}
