package net.benjaminurquhart.tos.game;

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

public class Game {
	
	public static Map<String, StringTableMessage> STRING_TABLE;
	public static Map<String, Faction> FACTION_TABLE;
	public static Map<String, Genre> GENRE_TABLE;
	public static Map<String, Role> ROLE_TABLE;
	
	public static Map<String, List<String>> ROLE_ABBREVIATIONS;
	
	public static Map<Integer, String> GAME_MODE_TABLE;
	public static Map<Integer, Role> ROLE_ID_TABLE;
	
	public static Achievement[] ACHIEVEMENTS;
	public static Faction[] FACTIONS;
	public static Killer[] KILLERS;
	public static Scroll[] SCROLLS;
	public static Winner[] WINNERS;
	public static Role[] ROLES;

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
			KILLERS = new Killer[killers.length()];
			for(int i = 0, length = killers.length(); i < length; i++) {
				KILLERS[i] = new Killer(killers.getJSONObject(i));
			}
			JSONArray gamemodes = json.getJSONArray("Modes");
			GAME_MODE_TABLE = new HashMap<>();
			for(int i = 0, length = gamemodes.length(); i < length; i++) {
				tmp = gamemodes.getJSONObject(i);
				if(tmp.get("id") instanceof JSONArray) {
					tmp.put("id", tmp.getJSONArray("id").getString(0));
				}
				GAME_MODE_TABLE.put(Integer.parseInt(tmp.getString("id")), tmp.getString("Name"));
			}
			JSONArray winners = json.getJSONArray("WinningGroups");
			WINNERS = new Winner[winners.length()];
			for(int i = 0, length = winners.length(); i < length; i++) {
				WINNERS[i] = new Winner(winners.getJSONObject(i));
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
		for(Role role : ROLES) {
			for(String abbreviation : ROLE_ABBREVIATIONS.computeIfAbsent(role.getName(), n -> new ArrayList<>())) {
				text = text.replaceAll(
						"(?i)(^|\\s|\\p{P})("+Pattern.quote(abbreviation)+"s?)(\\s|$|\\p{P})",
						"$1"+ANSI.toTrueColor(role.getColor())+"$2"+ANSI.RESET+"$3"
				);
			}
			text = text.replaceAll(
					"(?i)(^|\\s|\\p{P})"+Pattern.quote(role.getName())+"(\\s|$|\\p{P})",
					"$1"+ANSI.toTrueColor(role.getColor())+role.getName()+ANSI.RESET+"$2"
			);
		}
		for(Faction faction : FACTIONS) {
			text = text.replaceAll(
					"(?i)(^|\\s|\\p{P})"+Pattern.quote(faction.getName())+"(\\s|$|\\p{P})",
					"$1"+ANSI.valueOf(faction.getName().toUpperCase())+faction.getName()+ANSI.RESET+"$2"
			);
		}
		return text;
	}
}
