package net.benjaminurquhart.tos.handlers;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.benjaminurquhart.tos.game.ANSI;
import net.benjaminurquhart.tos.game.Game;
import net.benjaminurquhart.tos.game.Killer;
import net.benjaminurquhart.tos.game.Role;
import net.benjaminurquhart.tos.game.StringTableMessage;

public class ServerMessageHandler extends MessageHandler {
	
	private boolean[] alive;
	private String[] names;
	private Role[] roles;
	
    public ServerMessageHandler() {
		super("Server");
		this.alive = new boolean[15];
		this.names = new String[15];
		this.roles = new Role[15];
	}
    
    @Override
	public void processCommand(byte[] command) {
    	//this.onUnhandledCommand(command);
        switch (((int)command[0])&(int)0b11111111) { 
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
                 case 14: onDefaultFunction(command); break;
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
                 case 31: onDefaultFunction(command); break;
                 case 32: onDefaultFunction(command); break;
                 case 33: onDefaultFunction(command); break;
                 case 34: onDefaultFunction(command); break;
                 case 35: onDefaultFunction(command); break;
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
                 case 58: onDefaultFunction(command); break;
                 case 59: onDefaultFunction(command); break;
                 case 60: onDefaultFunction(command); break;
                 case 61: onDefaultFunction(command); break;
                 case 62: onDefaultFunction(command); break;
                 case 63: onDefaultFunction(command); break;
                 case 64: onDefaultFunction(command); break;
                 case 65: onDefaultFunction(command); break;
                 case 66: onUpdateFriendName(command); break;
                 case 67: onDefaultFunction(command); break;
                 case 68: onDefaultFunction(command); break;
                 case 69: onDefaultFunction(command); break;
                 case 70: onDefaultFunction(command); break;
                 case 71: onDefaultFunction(command); break;
                 case 72: onDefaultFunction(command); break;
                 case 73: onDefaultFunction(command); break;
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
                 case 207: onOtherWitches(command); break;
                 case 208: onPsychicNightAbility(command); break;
                 case 209: onTrapperNightAbility(command); break;
                 case 210: onTrapperTrapStatus(command); break;
                 case 211: onPestilenceConversion(command); break;
                 case 212: onJuggernautKillCount(command); break;
                 case 213: onCovenGotNecronomicon(command); break;
                 case 214: onGuardianAngelPromoted(command); break;
                 case 215: onVIPTarget(command); break;
                 case 216: onPirateDuelOutcome(command); break;
                 case 217: onDefaultFunction(command); break;
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
                default: onUnhandledCommand(command); break;
        }
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
		int games = Integer.parseInt(new String(Arrays.copyOfRange(command, asterisk+1, command.length-2)));
		System.out.printf("%s%d players online (%d games running)%s\n", ANSI.GREEN, players, games, ANSI.GRAY);
	}

	private void onSpyNightInfo(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onLoginFailure(byte[] command) {
		System.out.println("Login failed");
	}

	private void onJailorDeathNote(byte[] command) {
		onUnhandledCommand(command);
		
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
		onUnhandledCommand(command);
		
	}

	private void onLoverTarget(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onZombieRotted(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onAccountFlags(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onPirateDuelOutcome(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onVIPTarget(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onGuardianAngelPromoted(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onCovenGotNecronomicon(byte[] command) {
		onUnhandledCommand(command);
		
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

	private void onOtherWitches(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onHasNecronomicon(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onPotionMasterPotions(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onDuelTarget(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onPirateDuel(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onGuardianAngelProtection(byte[] command) {
		onUnhandledCommand(command);
		
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
		onUnhandledCommand(command);
		
	}

	private void onTauntResult(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onActiveEvents(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onUpdateFreeCurrency(byte[] command) {
		onUnhandledCommand(command);
		
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
		onUnhandledCommand(command);
		
	}

	private void onVampirePromotion(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onPayPalShowApprovalPage(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onRoleLotsInfoMesssage(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onEndGameUserUpdate(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onEndGameChatMessage(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onEndGameInfo(byte[] command) {
		onUnhandledCommand(command);
		
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
		onUnhandledCommand(command);
		
	}

	private void onFacebookShareWin(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onFacebookShareAchievement(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onPetsChosen(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onOneDayBeforeStalemate(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onSpyNightAbilityMessage(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onEarnedAchievements(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onPrivateMessage(byte[] command) {
		String color = ANSI.toTrueColor(Color.MAGENTA);
		System.out.printf(
				"%s%s %s%s:%s %s%s\n",
				color,
				command[1] == 0x01 ? "To" : "From",
				ANSI.RESET,
				names[command[2]-1],
				color,
				new String(Arrays.copyOfRange(command, 3, command.length-1)),
				ANSI.GRAY
		);
	}

	private void onNotifyUsersOfPrivateMessage(byte[] command) {
		System.out.printf(
				"%s%s%s is whispering to %s%s%s\n",
				ANSI.LIGHT_GRAY,
				names[command[1]-1],
				ANSI.toTrueColor(Color.MAGENTA),
				ANSI.LIGHT_GRAY,
				names[command[2]-1],
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
		alive[command[1]-1] = true;
	}

	private void onCharactersChosen(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onFirstDayTransition(byte[] command) {
		System.out.println(ANSI.GRAY+"---------- Start ----------");
	}

	private void onHousesChosen(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onDeathNote(byte[] command) {
		System.out.printf("%sDeath Note:\n%s%s%s\n", ANSI.RESET, ANSI.RED, new String(Arrays.copyOfRange(command, 3, command.length-1)), ANSI.GRAY);
	}

	private void onLynchUser(byte[] command) {
		//onUnhandledCommand(command);
	}

	private void onStartDayTransition(byte[] command) {
		
	}

	private void onStartNightTransition(byte[] command) {
		
	}

	private void onInvalidNameMessage(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onMafiaWasJailed(byte[] command) {
		onUnhandledCommand(command);
		
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
		onUnhandledCommand(command);
		
	}

	private void onMafiaPromotedToMafioso(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onMafiosoPromotedToGodfatherUpdateMafia(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onMafiosoPromotedToGodfather(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onSomeoneHasWon(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onTellJanitorTargetsWill(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onTellJanitorTargetsRole(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onMafiaTargeting(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onHowManyAbilitiesLeft(byte[] command) {
		System.out.printf("%sYou have %d ability uses left%s\n", ANSI.RESET, command[1], ANSI.GRAY);
	}

	private void onTellLastWill(byte[] command) {
		String will = new String(Arrays.copyOfRange(command, 3, command.length-1));
		if(!will.trim().isEmpty()) {
			System.out.printf("%sWill:\n%s%s\n\n", ANSI.RESET, will, ANSI.GRAY);
		}
		else {
			System.out.println(ANSI.RESET+"We could not find a last will\n");
		}
	}

	private void onMediumCommunicating(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onMediumIsTalkingToUs(byte[] command) {
		onUnhandledCommand(command);
		
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
		onUnhandledCommand(command);
		
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
				names[command[1]-1],
				ANSI.GREEN,
				verb,
				ANSI.toTrueColor(color),
				vote,
				ANSI.GRAY
		);
	}

	private void onUserCanceledJudgementVote(byte[] command) {
		System.out.printf("%s%s%s has canceled their vote%s\n", ANSI.RESET, names[command[1]-1], ANSI.GREEN, ANSI.GRAY);
	}

	private void onUserChangedJudgementVote(byte[] command) {
		System.out.printf("%s%s%s has changed their vote%s\n", ANSI.RESET, names[command[1]-1], ANSI.GREEN, ANSI.GRAY);
	}

	private void onUserJudgementVoted(byte[] command) {
		System.out.printf("%s%s%s has voted%s\n", ANSI.RESET, names[command[1]-1], ANSI.GREEN, ANSI.GRAY);
	}

	private void onJailedTarget(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onBeingJailed(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onStartFirstDay(byte[] command) {
		//onUnhandledCommand(command);
	}

	private void onBroughtBackToLife(byte[] command) {
		System.out.println(ANSI.GREEN+"You were resurrected by a Retributionist!"+ANSI.GRAY);//onUnhandledCommand(command);
	}

	private void onAmnesiacChangedRole(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onTellTownAmnesiacChangedRole(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onOtherMafia(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onUserChosenName(byte[] command) {
		//onUnhandledCommand(command);
		String name = new String(Arrays.copyOfRange(command, 3, command.length-1));
		names[command[2]-1] = name;
		System.out.printf("%s%s (%d) has joined the Town%s\n", ANSI.GREEN, name, command[2], ANSI.GRAY);
	}

	private void onTellRoleList(byte[] command) {
		System.out.println("Possible Roles:");
		Color color;
		Role role;
		for(int i = 1; i < command.length-1; i++) {
			role = Game.ROLES[command[i]-1];
			color = role.getColor();
			System.out.printf("%s%s%s\n", (color.getRGB()&0xffffff) == 0xffffff ? ANSI.RESET : ANSI.toTrueColor(color), role.getName(), ANSI.GRAY);
		}
	}

	private void onResurrection(byte[] command) {
		Role role = roles[command[1]-1];
		System.out.printf(
				"%s%s (%s%s%s) was brought back to life!%s\n",
				ANSI.RESET,
				names[command[1]-1],
				ANSI.toTrueColor(role.getColor()),
				role.getName(),
				ANSI.RESET,
				ANSI.LIGHT_GRAY
		);
	}

	private void onUserDied(byte[] command) {
		System.out.printf("%s%sYou have died!%s%s\n", ANSI.WHITE, ANSI.toTrueColorBackground(Color.RED), ANSI.RESET, ANSI.GRAY);
	}

	private void onUserChangedVote(byte[] command) {
		String voter = names[command[1]-1], voted = names[command[2]-1];
		System.out.printf(
				"%s%s%s has changed their vote to %s%s (Vote Worth: %d)%s\n",
				ANSI.RESET,
				voter,
				ANSI.GREEN,
				ANSI.RESET,
				voted,
				command[3],
				ANSI.GRAY
		);
	}

	private void onUserCanceledVote(byte[] command) {
		System.out.printf(
				"%s%s%s has canceled their vote%s\n",
				ANSI.RESET,
				names[command[1]-1],
				ANSI.GREEN,
				ANSI.GRAY
		);
	}

	private void onUserVoted(byte[] command) {
		String voter = names[command[1]-1], voted = names[command[2]-1];
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
		System.out.printf(
				"%sDefendant found %sinnocent%s by a vote of %s%s%s to %s%d%s\n",
				ANSI.RESET,
				ANSI.GREEN,
				ANSI.RESET,
				ANSI.RED,
				command[1],
				ANSI.RESET,
				ANSI.GREEN,
				command[2],
				ANSI.GRAY
		);
	}

	private void onTrialFoundGuilty(byte[] command) {
		System.out.printf(
				"%sDefendant found %sguilty%s by a vote of %s%s%s to %s%d%s\n",
				ANSI.RESET,
				ANSI.RED,
				ANSI.RESET,
				ANSI.RED,
				command[1],
				ANSI.RESET,
				ANSI.GREEN,
				command[2],
				ANSI.GRAY
		);
	}

	private void onStartJudgement(byte[] command) {
		System.out.println(ANSI.GREEN+"The Town may now vote on the fate of the defendant"+ANSI.GRAY);
		System.out.println(ANSI.GRAY+"---------Judgement---------");
	}
	private void onStartDefenseTransition(byte[] command) {
		System.out.printf(
				"%sThe Town has decided to put %s%s%s on trial%s\n",
				ANSI.GREEN,
				ANSI.RESET,
				names[command[1]-1],
				ANSI.GREEN,
				ANSI.GRAY
		);
	}

	private void onStartVoting(byte[] command) {
		System.out.println(ANSI.GRAY+"----------Voting-----------");
	}

	private void onStartDiscussion(byte[] command) {
		System.out.println(ANSI.GRAY+"--------Discussion---------");
	}

	private void onWhoDiedAndHow(byte[] command) {
		//System.out.println(ANSI.RESET+"-----------Death-----------");
		this.onUnhandledCommand(command);
		System.out.printf("%x\n", command[2]);
		Role role = Game.ROLES[command[2]-1];
		roles[command[1]-1] = role;
		alive[command[1]-1] = false;
		List<String> killers = new ArrayList<>();
		Killer killer;
		for(int i = 4; i < command.length-1; i++) {
			killer = Game.KILLERS[command[i]-1];
			killers.add(String.format("%s%s%s", ANSI.toTrueColor(killer.getColor()), killer.getName(), ANSI.RESET));
		}
		System.out.printf("\n%s%s (%d) was killed%s%s\n", ANSI.RESET, names[command[1]-1], command[1], killers.size() > 0 ? " by " : "", String.join(", ", killers));
		System.out.printf("Role: %s%s%s\n", ANSI.toTrueColor(role.getColor()), role.getName(), ANSI.GRAY);
	}

	private void onStartDay(byte[] command) {
		System.out.println(ANSI.GRAY+"------------Day------------");
	}

	private void onStartNight(byte[] command) {
		System.out.println(ANSI.GRAY+"-----------Night-----------");
	}

	private void onRoleAndPosition(byte[] command) {
		Role role = Game.ROLES[command[1]-1];
		roles[command[2]-1] = role;
		System.out.printf("%sRole: %s%s%s\nPosition: %d%s\n", ANSI.RESET, ANSI.toTrueColor(role.getColor()), role.getName(), ANSI.RESET, command[2], ANSI.GRAY);
	}

	private void onNamesAndPositionsOfUsers(byte[] command) {
		names[command[1]-1] = new String(Arrays.copyOfRange(command, 2, command.length-1));
	}

	private void onPickNames(byte[] command) {
		Arrays.fill(names, null);
		System.out.println("Name selection has begun");
		//onUnhandledCommand(command);
	}

	private void onCurrencyMultiplier(byte[] command) {
		System.err.println("Currency multiplier active: " + this.convertToString(command));
		
	}

	private void onPromotionPopup(byte[] command) {
		System.err.println("Promotion appeared: " + this.convertToString(command));
		
	}

	private void onScrollConsumed(byte[] command) {
		System.err.println("Consumed scroll: " + this.convertToString(command));
		
	}

	private void onPlayerStatistics(byte[] command) {
		System.err.println("Player Statistics: " + this.convertToString(command));
		
	}

	private void onReferAFriendUpdate(byte[] command) {
		System.err.println("Referred a friend: " + this.convertToString(command));
		
	}

	private void onModeratorMessage(byte[] command) {
		System.err.println("Moderator Message: " + this.convertToString(command));
		
	}

	private void onUserStatistics(byte[] command) {
		System.err.println("User Statistics: " + this.convertToString(command));
		
	}

	private void onUpdateFriendName(byte[] command) {
		onUnhandledCommand(command);
		
	}

	private void onSetLastBonusWinTime(byte[] command) {
		onUnhandledCommand(command);
		
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
		onUnhandledCommand(command);
		
	}

	private void onStringTableMessage(byte[] command) {
		String id = String.valueOf(command[1]-1);
		StringTableMessage msg = Game.STRING_TABLE.get(id);
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
		int player = command[1+offset]-1;
		boolean alive;
		
		String name;
		if(player == 44) {
			name = ANSI.CYAN+"Medium";
			alive = true;
		}
		else {
			name = names[player];
			alive = this.alive[player];
		}
		System.out.printf("%s%s%s: %s%s\n", alive ? ANSI.RESET : ANSI.RED, name, ANSI.RESET, new String(Arrays.copyOfRange(command, 2+offset, command.length-1)), ANSI.GRAY);
	}

	private void onUserLeftGame(byte[] command) {
		System.out.printf("%s%s left the game%s\n", ANSI.YELLOW, names[command[3]-1], ANSI.GRAY);
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
		names[command[asterisk+1]-1] = name;
	}

	private void onJoinedGameLobby(byte[] command) {
		Arrays.fill(alive, true);
		System.out.println(ANSI.RESET+"Joined game lobby"+ANSI.GRAY);
		//onUnhandledCommand(command);
	}

	private void onLoginSuccess(byte[] command) {
		System.out.println("Log in succeeded");
		//onUnhandledCommand(command);
	}
}
