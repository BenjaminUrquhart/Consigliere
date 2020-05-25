package net.benjaminurquhart.tos.game;

//import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum PlayerTag {

	FULLMOON_ATTACK,
	CLEANED,
	STONED,
	TARGET,
	LOVER,
	
	VIP("VIP");
	
	private static final Pattern NAME_PATTERN = Pattern.compile("([A-Z])(.+?)?( |$)");
	private String name;
	
	/*
	static {
		System.out.println(Arrays.toString(values()));
	}*/
	
	private PlayerTag() {
		this(null);
	}
	private PlayerTag(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		if(name == null) {
			Matcher matcher = NAME_PATTERN.matcher(this.name().replace('_', ' '));
			StringBuilder sb = new StringBuilder();
			while(matcher.find()) {
				sb.append(matcher.group(1).toUpperCase());
				sb.append(matcher.group(2).toLowerCase());
				sb.append(matcher.group(3));
			}
			name = sb.toString();
		}
		return name;
	}
}
