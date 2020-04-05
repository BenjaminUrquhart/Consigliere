package net.benjaminurquhart.tos.game;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class Game {
	
	public static Map<String, StringTableMessage> STRING_TABLE;
	public static Map<String, Faction> FACTION_TABLE;
	public static Map<String, Genre> GENRE_TABLE;
	public static Map<String, Role> ROLE_TABLE;
	
	public static Map<Integer, String> GAME_MODE_TABLE;
	
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
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
