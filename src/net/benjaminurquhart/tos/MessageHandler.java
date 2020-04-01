package net.benjaminurquhart.tos;

import java.util.Arrays;

import net.benjaminurquhart.tos.game.*;

public class MessageHandler {
	
    public void parseServerCommand(byte[] command) {
    	this.onUnhandledCommand(command, true);
        switch ((int)command[0]) { 
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
                 case 11: onDefaultFunction(command); break;
                 case 12: onDefaultFunction(command); break;
                 case 13: onDefaultFunction(command); break;
                 case 14: onDefaultFunction(command); break;
                 case 15: onDefaultFunction(command); break;
                 case 16: onDoNotSpam(command); break;
                 case 17: onDefaultFunction(command); break;
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
                 case 193: onDefaultFunction(command); break;
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
                default: break;
        }
    }
    public void parseClientCommand(byte[] command) {
    	this.onUnhandledCommand(command, false);
    	switch((int)command[0]) {
    	case 20: onCustomizationUpdate(command); break;
    	}
    }

	private void onCustomizationUpdate(byte[] command) {
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

	private void onSpyNightInfo(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onLoginFailure(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onJailorDeathNote(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onRankedInfo(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onRivalTarget(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onPlagueSpread(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onLoverTarget(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onZombieRotted(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onAccountFlags(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onPirateDuelOutcome(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onVIPTarget(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onGuardianAngelPromoted(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onCovenGotNecronomicon(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onJuggernautKillCount(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onPestilenceConversion(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onTrapperTrapStatus(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onTrapperNightAbility(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onPsychicNightAbility(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onOtherWitches(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onHasNecronomicon(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onPotionMasterPotions(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onDuelTarget(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onPirateDuel(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onGuardianAngelProtection(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onAmbusherNightAbility(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onTrackerNightAbility(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onTauntConsumed(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onTauntActivated(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onTauntResult(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onActiveEvents(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onUpdateFreeCurrency(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onTransporterNotification(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onVampireVisitedMessage(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onVampireHunterPromoted(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onVampireDied(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onCanVampiresConvert(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onAddVampire(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onOtherVampires(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onVampirePromotion(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onPayPalShowApprovalPage(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onRoleLotsInfoMesssage(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onEndGameUserUpdate(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onEndGameChatMessage(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onEndGameInfo(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onIdentify(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onFullMoonNight(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onDeathAnimationsChosen(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onFacebookShareWin(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onFacebookShareAchievement(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onPetsChosen(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onOneDayBeforeStalemate(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onSpyNightAbilityMessage(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onEarnedAchievements(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onPrivateMessage(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onNotifyUsersOfPrivateMessage(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onVigilanteKilledTown(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onUserLeftDuringSelection(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onStartDefense(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onResurrectionSetAlive(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onCharactersChosen(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onFirstDayTransition(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onHousesChosen(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onDeathNote(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onLynchUser(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onStartDayTransition(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onStartNightTransition(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onInvalidNameMessage(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onMafiaWasJailed(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onUserDisconnected(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onAmnesiacBecameMafiaOrWitch(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onExecutionerConvertedToJester(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onTellMafiaAboutMafiosoPromotion(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onMafiaPromotedToMafioso(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onMafiosoPromotedToGodfatherUpdateMafia(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onMafiosoPromotedToGodfather(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onSomeoneHasWon(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onTellJanitorTargetsWill(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onTellJanitorTargetsRole(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onMafiaTargeting(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onHowManyAbilitiesLeft(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onTellLastWill(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onMediumCommunicating(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onMediumIsTalkingToUs(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onDisguiserChangedUpdateMafia(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onDisguiserChangedIdentity(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onDisguiserStoleYourIdentity(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onMayorRevealedAndAlreadyVoted(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onMayorRevealed(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onJesterCompletedGoal(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onExecutionerCompletedGoal(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onTellJudgementVotes(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onUserCanceledJudgementVote(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onUserChangedJudgementVote(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onUserJudgementVoted(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onJailedTarget(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onBeingJailed(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onStartFirstDay(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onBroughtBackToLife(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onAmnesiacChangedRole(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onTellTownAmnesiacChangedRole(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onOtherMafia(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onUserChosenName(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onTellRoleList(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onResurrection(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onUserDied(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onUserChangedVote(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onUserCanceledVote(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onUserVoted(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onLookoutNightAbilityMessage(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onTrialFoundNotGuilty(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onTrialFoundGuilty(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onStartJudgement(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onStartDefenseTransition(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onStartVoting(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onStartDiscussion(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onWhoDiedAndHow(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onStartDay(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onStartNight(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onRoleAndPosition(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onNamesAndPositionsOfUsers(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onPickNames(byte[] command) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	private void onSetLastBonusWinTime(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onUpdatePaidCurrency(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onShopPurchaseSuccess(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onReturnToHomePage(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onForcedLogout(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onFriendUpdate(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onStringTableMessage(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onSystemMessage(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onDoNotSpam(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onChatBoxMessage(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onUserLeftGame(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onUsersJoinedLobby(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onJoinedGameLobby(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	private void onLoginSuccess(byte[] command) {
		// TODO Auto-generated method stub
		
	}

	public void onDefaultFunction(byte[] command) {
    	onUnhandledCommand(command, true);
    }
	public void onUnhandledCommand(byte[] command, boolean server) {
		System.out.printf("%s: (0x%02x %03d): %s\n", server ? "Server" : "Client", command[0], ((int)command[0])&(int)0b11111111, convertToString(command));
	}
	private String convertToString(byte[] command) {
		StringBuilder sb = new StringBuilder();
		boolean wasUnprintable = false;
		String now = null;
		for(byte b : Arrays.copyOfRange(command, 1, command.length-1)) {
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
}
