package net.benjaminurquhart.tos.game;

public enum PlayerTag {

	FULLMOONATTACK("Full Moon Attack"),
	CLEANED("Cleaned"),
	STONED("Stoned");
	
	private final String name;
	
	private PlayerTag(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return name;
	}
}
