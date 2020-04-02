package net.benjaminurquhart.tos.game;

import java.awt.Color;

import org.json.JSONObject;

public class Killer {

	private String name;
	private Color color;
	private int id;
	
	public Killer(JSONObject json) {
		//System.out.println(json);
		this.color = new Color(Integer.parseInt(json.optString("Color", "#000000").substring(1), 16));
		this.id = Integer.parseInt(json.getString("id"));
		
		this.name = json.optString("Name", json.getString("Icon"));
	}
	public Color getColor() {
		return color;
	}
	public String getName() {
		return name;
	}
	public int getID() {
		return id;
	}
	@Override
	public String toString() {
		return name + " (" + id + ")";
	}
}
