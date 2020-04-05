package net.benjaminurquhart.tos.game;

import java.awt.Color;

import org.json.JSONObject;

public class StringTableMessage {

	private String text, id;
	private Color color;
	
	public StringTableMessage(String id, JSONObject json) {
		switch(json.optString("style", "none")) {
		case "positive": color = Color.GREEN; break;
		case "alert": color = Color.RED; break;
		case "spy": color = Color.BLACK; break;
		default: color = Color.GRAY; break;
		}
		this.text = json.optString("text");
		this.id = id;
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
