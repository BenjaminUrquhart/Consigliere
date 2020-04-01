package net.benjaminurquhart.tos.game;

import java.awt.Color;

import org.json.JSONObject;

public class Role {

	private Faction faction;
	private String type;
	private String name;
	private int id;
	
	private Defense defense;
	private Attack attack;
	
	private Color color;
	
	public Role(JSONObject json) {
		//System.err.println(json);
		if(json.opt("Defense") instanceof String) {
			this.defense = Defense.values()[Integer.parseInt(json.optString("Defense", "0"))];
		}
		else {
			this.defense = Defense.values()[Integer.parseInt(json.getJSONObject("Defense").getString("text"))];
		}
		if(json.opt("Attack") instanceof String) {
			this.attack = Attack.values()[Integer.parseInt(json.optString("Attack", "0"))];
		}
		else {
			this.attack = Attack.values()[Integer.parseInt(json.getJSONObject("Attack").getString("text"))];
		}
		
		this.color = new Color(Integer.parseInt(json.getString("Color").substring(1), 16));
		
		this.faction = json.has("Faction") ? Game.FACTIONS[Integer.parseInt(json.getString("Faction"))-1] : null;
		this.name = json.getJSONObject("Name").getString("text");
		this.type = json.getJSONObject("Type").getString("text");
		this.id = Integer.parseInt(json.getString("id"));
	}
	public int getID() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Color getColor() {
		return color;
	}
	public String getType() {
		return type;
	}
	public Attack getAttack() {
		return attack;
	}
	public Defense getDefense() {
		return defense;
	}
	public Faction getFaction() {
		return faction;
	}
	@Override
	public String toString() {
		return String.format("%s (%s %s). Attack: %s, Defense: %s", name, faction == null ? "Neutral" : faction.getName(), type, attack, defense);
	}
}
