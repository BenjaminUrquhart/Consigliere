package net.benjaminurquhart.tos.game;

import java.awt.Color;

public enum ANSI {
	
	TOWN(new Color(0x7FFF00)),
	MAFIA(new Color(0xDD0000)),
	COVEN(new Color(0xBF5FFF)),
	VAMPIRE(new Color(0x7B8968)),
	
	RED(Color.RED),
	GRAY(Color.GRAY),
	CYAN(Color.CYAN),
	BLUE(Color.BLUE),
	GREEN(Color.GREEN),
	BLACK(Color.BLACK),
	WHITE(Color.WHITE),
	YELLOW(Color.YELLOW),
	LIGHT_GRAY(Color.LIGHT_GRAY);
	
	public static final String RESET = "\u001b[0;1m";
	
	private final Color color;
	
	private ANSI(Color color) {
		this.color = color;
	}
	private static String toTrueColorImpl(Color color) {
		return String.format("\u001b[38;2;%d;%d;%dm", color.getRed(), color.getGreen(), color.getBlue());
	}
	public static String toTrueColor(Color color) {
		if((color.getRGB()&0xffffff) == 0xffffff) {
			return RESET;
		}
		return toTrueColorImpl(color);
	}
	public static String toTrueColorBackground(Color color) {
		return String.format("\u001b[48;2;%d;%d;%dm", color.getRed(), color.getGreen(), color.getBlue());
	}
	
	@Override
	public String toString() {
		return toTrueColorImpl(color);
	}
}
