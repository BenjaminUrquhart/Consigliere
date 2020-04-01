package net.benjaminurquhart.tos.game;

import java.io.InputStream;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;

public class Game {
	
	public static Faction[] FACTIONS;
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
			for(int i = 0, length = factions.length(); i < length; i++) {
				FACTIONS[i] = new Faction(factions.getJSONObject(i));
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
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
