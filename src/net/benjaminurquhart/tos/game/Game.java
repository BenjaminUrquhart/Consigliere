package net.benjaminurquhart.tos.game;

import java.awt.Color;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import net.benjaminurquhart.tos.game.entities.*;

public class Game {
	
	public static Map<String, StringTableMessage> STRING_TABLE;
	public static Map<String, List<Role>> ROLE_LIST_TABLE;
	public static Map<String, GameMode> GAME_MODE_TABLE;
	public static Map<String, Faction> FACTION_TABLE;
	public static Map<String, Genre> GENRE_TABLE;
	public static Map<String, Role> ROLE_TABLE;
	
	public static Map<String, List<String>> ROLE_ABBREVIATIONS;
	
	public static Map<Integer, GameMode> GAME_MODE_ID_TABLE;
	public static Map<Integer, Role> ROLE_ID_TABLE;
	
	public static Achievement[] ACHIEVEMENTS;
	public static Faction[] FACTIONS;
	public static Killer[] KILLERS;
	public static Scroll[] SCROLLS;
	public static Winner[] WINNERS;
	public static String[] TAUNTS;
	public static Role[] ROLES;
	
	private static Map<String, String> REPLACEMENT_CACHE = new HashMap<>();
	private static Map<String, Pattern> REGEX_CACHE = new HashMap<>();

	static {
		try {
			StringBuilder sb = new StringBuilder();
			InputStream stream = Game.class.getResourceAsStream("/GameRules.json");
			byte[] buff = new byte[1024];
			int read;
			while((read = stream.read(buff)) != -1) {
				if(read == 0) continue;
				sb.append(new String(Arrays.copyOfRange(buff, 0, read)));
			}
			stream.close();
			JSONObject json = new JSONObject(sb.toString()), tmp;
			JSONArray factions = json.getJSONArray("Factions");
			FACTIONS = new Faction[factions.length()];
			FACTION_TABLE = new HashMap<>();
			for(int i = 0, length = factions.length(); i < length; i++) {
				FACTIONS[i] = new Faction(factions.getJSONObject(i));
				FACTION_TABLE.put(FACTIONS[i].getName(), FACTIONS[i]);
			}
			JSONArray roles = json.getJSONArray("Roles");
			ROLES = new Role[roles.length()];
			ROLE_ID_TABLE = new HashMap<>();
			ROLE_TABLE = new HashMap<>();
			Role role;
			for(int i = 0, length = roles.length(); i < length; i++) {
				tmp = roles.getJSONObject(i);
				//System.err.println(tmp);
				if(!tmp.has("Type")) {
					role = new RandomRole(tmp);
				}
				else {
					role = new Role(tmp);
				}
				ROLES[i] = role;
				ROLE_TABLE.put(role.getName(), role);
				ROLE_ID_TABLE.put(role.getID(), role);
			}
			JSONArray killers = json.getJSONArray("Killers");
			KILLERS = new Killer[killers.length()+1];
			for(int i = 0, length = killers.length(); i < length; i++) {
				KILLERS[i] = new Killer(killers.getJSONObject(i));
			}
			KILLERS[KILLERS.length-1] = Killer.LYNCHING;
			JSONArray gamemodes = json.getJSONArray("Modes");
			GAME_MODE_ID_TABLE = new HashMap<>();
			GAME_MODE_TABLE = new HashMap<>();
			GameMode mode;
			for(int i = 0, length = gamemodes.length(); i < length; i++) {
				tmp = gamemodes.getJSONObject(i);
				if(tmp.get("id") instanceof JSONArray) {
					tmp.put("id", tmp.getJSONArray("id").getString(0));
				}
				mode = new GameMode(tmp);
				GAME_MODE_ID_TABLE.put(mode.getID(), mode);
				GAME_MODE_TABLE.put(mode.getName(), mode);
			}
			JSONArray winners = json.getJSONArray("WinningGroups");
			WINNERS = new Winner[winners.length()];
			for(int i = 0, length = winners.length(); i < length; i++) {
				WINNERS[i] = new Winner(winners.getJSONObject(i));
			}
			JSONArray rolelists = json.getJSONArray("RoleLists");
			ROLE_LIST_TABLE = new HashMap<>();
			for(int i = 0, length = rolelists.length(); i < length; i++) {
				tmp = rolelists.getJSONObject(i);
				ROLE_LIST_TABLE.put(
						tmp.getString("id"), 
						tmp.getJSONArray("Role").toList()
												.stream()
												.map(Map.class::cast)
												.map(m -> (String)m.get("id"))
												.map(Integer::parseInt)
												.map(ROLE_ID_TABLE::get)
												.collect(Collectors.toList())
				);
			}
			sb.delete(0, sb.length());
			stream = Game.class.getResourceAsStream("/Customization.json");
			while((read = stream.read(buff)) != -1) {
				if(read == 0) continue;
				sb.append(new String(Arrays.copyOfRange(buff, 0, read)));
			}
			stream.close();
			json = new JSONObject(sb.toString());
			JSONArray scrolls = json.getJSONArray("CustomizationScrolls");
			SCROLLS = new Scroll[scrolls.length()];
			for(int i = 0, length = scrolls.length(); i < length; i++) {
				tmp = scrolls.getJSONObject(i);
				if(!tmp.has("Role")) {
					SCROLLS[i] = new NoScroll(tmp);
				}
				else {
					SCROLLS[i] = new Scroll(tmp);
				}
			}
			JSONArray taunts = json.getJSONArray("CustomizationTaunts");
			TAUNTS = new String[taunts.length()];
			for(int i = 0, length = taunts.length(); i < length; i++) {
				tmp = taunts.getJSONObject(i);
				TAUNTS[i] = tmp.getJSONObject("Name").getString("text");
			}
			sb.delete(0, sb.length());
			stream = Game.class.getResourceAsStream("/StringTable.json");
			while((read = stream.read(buff)) != -1) {
				if(read == 0) continue;
				sb.append(new String(Arrays.copyOfRange(buff, 0, read)));
			}
			stream.close();
			JSONObject table = new JSONObject(sb.toString());
			STRING_TABLE = new HashMap<>();
			for(String key : table.keySet()) {
				STRING_TABLE.put(key, new StringTableMessage(key, table.getJSONObject(key)));
			}
			sb.delete(0, sb.length());
			stream = Game.class.getResourceAsStream("/Achievements.json");
			while((read = stream.read(buff)) != -1) {
				if(read == 0) continue;
				sb.append(new String(Arrays.copyOfRange(buff, 0, read)));
			}
			stream.close();
			tmp = new JSONObject(sb.toString());
			JSONArray achievements = tmp.getJSONArray("Achievement");
			ACHIEVEMENTS = new Achievement[achievements.length()];
			for(int i = 0, length = achievements.length(); i < length; i++) {
				ACHIEVEMENTS[i] = new Achievement(achievements.getJSONObject(i));
			}
			JSONArray genres = tmp.getJSONArray("Genre");
			GENRE_TABLE = new HashMap<>();
			Genre genre;
			for(int i = 0, length = genres.length(); i < length; i++) {
				genre = new Genre(genres.getJSONObject(i));
				GENRE_TABLE.put(genre.getID(), genre);
			}
			sb.delete(0, sb.length());
			stream = Game.class.getResourceAsStream("/RoleAliases.json");
			while((read = stream.read(buff)) != -1) {
				if(read == 0) continue;
				sb.append(new String(Arrays.copyOfRange(buff, 0, read)));
			}
			stream.close();
			final JSONObject abbreviationJSON = new JSONObject(sb.toString());
			ROLE_ABBREVIATIONS = abbreviationJSON.keySet()
												 .stream()
												 .collect(Collectors.toMap(key -> key, key -> {
													 JSONArray abbreviations = abbreviationJSON.getJSONArray((String)key);
													 return abbreviations.toList().stream().map(String::valueOf).collect(Collectors.toList());
												 }));
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static String insertColors(String text) {
		return insertColors(text, ANSI.RESET);
	}
	public static String insertColors(String text, ANSI defaultColor) {
		return insertColors(text, defaultColor.toString());
	}
	public static String insertColors(String text, Color defaultColor) {
		return insertColors(text, ANSI.toTrueColor(defaultColor));
	}
	public static String insertColors(String text, String defaultColor) {
		Pattern pattern;
		String match;
		for(Role role : ROLES) {
			for(String abbreviation : ROLE_ABBREVIATIONS.computeIfAbsent(role.getName(), n -> new ArrayList<>())) {
				pattern = REGEX_CACHE.computeIfAbsent(abbreviation, a -> Pattern.compile("(?i)(^|\\s|\\p{P})("+Pattern.quote(a)+"s?)(\\s|$|\\p{P})"));
				match = String.format(REPLACEMENT_CACHE.computeIfAbsent(abbreviation, a -> "$1"+ANSI.toTrueColor(role.getColor())+"$2%s$3"), defaultColor);
				text = pattern.matcher(text).replaceAll(match);
				
			}
			pattern = REGEX_CACHE.computeIfAbsent(role.getName(), name -> Pattern.compile("(?i)(^|\\s|\\p{P})("+Pattern.quote(name)+"s?)(\\s|$|\\p{P})"));
			match = String.format(REPLACEMENT_CACHE.computeIfAbsent(role.getName(), name -> "$1"+ANSI.toTrueColor(role.getColor())+"$2%s$3"), defaultColor);
			text = pattern.matcher(text).replaceAll(match);
		}
		for(Faction faction : FACTIONS) {
			pattern = REGEX_CACHE.computeIfAbsent(faction.getName(), f -> Pattern.compile("(?i)(^|\\s|\\p{P})("+Pattern.quote(faction.getName())+"s?)(\\s|$|\\p{P})"));
			match = String.format(REPLACEMENT_CACHE.computeIfAbsent(faction.getName(), name -> "$1"+ANSI.valueOf(name.toUpperCase())+"$2%s$3"), defaultColor);
			text = pattern.matcher(text).replaceAll(match);
		}
		return text;
	}
	
	private List<Role> rolelist;
	private int playerOnTrial;
	
	private Player[] players;
	private int selfPosition;
	
	private GamePhase phase;
	private GameMode mode;
	
	private int abilitiesLeft;
	
	public Game() {
		this.players = new Player[15];
		this.selfPosition = -1;
	}
	public GameMode getMode() {
		if(mode == null) {
			this.inferModeFromRoleList();
		}
		return mode;
	}
	public GamePhase getPhase() {
		return phase;
	}
	public Player[] getPlayers() {
		return players;
	}
	public Player getPlayer(int position) {
		return players[position-1];
	}
	public Player getSelfPlayer() {
		return players[selfPosition-1];
	}
	public List<Role> getRoleList() {
		return rolelist;
	}
	public void setMode(GameMode mode) {
		this.mode = mode;
	}
	public void setPhase(GamePhase phase) {
		this.phase = phase;
		if(phase == GamePhase.PICK_NAME || phase == GamePhase.LOBBY) {
			for(Player p : players) {
				if(p != null) {
					p.getTags().clear();
					p.setTarget(null);
					p.setRole(null);
					p.resurrect();
				}
			}
		}
	}
	public void setRoleList(List<Role> list) {
		this.rolelist = list;
	}
	public void setSelfPosition(int position) {
		selfPosition = position;
	}
	
	public void updatePlayerName(String name, int position) {
		if(players[position-1] != null) {
			players[position-1].setName(name);
		}
		else {
			players[position-1] = new Player(name, position);
		}
	}
	
	public void placePlayerOnTrial(int position) {
		playerOnTrial = position;
	}
	public void clearPlayerOnTrial() {
		playerOnTrial = -1;
	}
	public Player getPlayerOnTrial() {
		if(playerOnTrial < 0) {
			return null;
		}
		return players[playerOnTrial-1];
	}
	public void setAbilitiesLeft(int left) {
		abilitiesLeft = left;
	}
	public int getAbilitiesLeft() {
		return abilitiesLeft;
	}
	public void consumeAbility() {
		abilitiesLeft = Math.min(0, abilitiesLeft-1);
	}
	public String getDayAbilitiesLeftMessage() {
		StringTableMessage msg = this.getSelfPlayer().getRole().getDayAbilityUsesLeftMessage(abilitiesLeft);
		if(msg == null) {
			return null;
		}
		return formatMsg(msg);
	}
	public String getNightAbilitiesLeftMessage() {
		StringTableMessage msg = this.getSelfPlayer().getRole().getNightAbilityUsesLeftMessage(abilitiesLeft);
		if(msg == null) {
			return null;
		}
		return formatMsg(msg);
	}
	private String formatMsg(StringTableMessage msg) {
		return String.format(
				"%s%s%s%s", 
				ANSI.WHITE, 
				ANSI.toTrueColorBackground(msg.getColor()), 
				msg.getText().replace("%number%", String.valueOf(abilitiesLeft)), 
				ANSI.RESET, 
				ANSI.GRAY
		);
	}
	private void inferModeFromRoleList() {
		if(rolelist == null) {
			return;
		}
		// TODO: implement
	}
}
