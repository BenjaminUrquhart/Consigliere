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
	
	public static Faction[] FACTIONS;
	public static Killer[] KILLERS;
	public static Scroll[] SCROLLS;
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
			for(int i = 0, length = roles.length(); i < length; i++) {
				tmp = roles.getJSONObject(i);
				if(!tmp.has("Type")) {
					ROLES[i] = new RandomRole(tmp);
				}
				else {
					ROLES[i] = new Role(tmp);
				}
			}
			JSONArray killers = json.getJSONArray("Killers");
			KILLERS = new Killer[killers.length()];
			for(int i = 0, length = killers.length(); i < length; i++) {
				KILLERS[i] = new Killer(killers.getJSONObject(i));
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
			JSONArray table = new JSONArray(sb.toString());
			STRING_TABLE = new HashMap<>();
			StringTableMessage msg;
			for(int i = 0, length = table.length(); i < length; i++) {
				msg = new StringTableMessage(table.getJSONObject(i));
				STRING_TABLE.put(msg.getID(), msg);
			}
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
