package net.benjaminurquhart.tos.game;

import java.awt.Color;

import org.json.JSONObject;

public class StringTableMessage {

	private String text, id;
	private Color color;
	
	public StringTableMessage(JSONObject json) {
		this.color = new Color(Integer.parseInt(json.optString("Color", "0x000000").substring(2), 16));
		this.text = json.getString("Text");
		this.id = json.getString("id");
	}
	public String getText() {
		return text;
	}
	public Color getColor() {
		return color;
	}
	public String getID() {
		return id;
	}
	@Override
	public String toString() {
		return text + " (" + id + ")";
	}
}
