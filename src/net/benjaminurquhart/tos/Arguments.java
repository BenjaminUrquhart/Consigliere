package net.benjaminurquhart.tos;

public class Arguments {

	private String filename;
	private Mode mode;
	
	public Arguments(String... args) {
		mode = Mode.LIVE;
		for(int i = 0; i < args.length; i++) {
			if(args[i].equalsIgnoreCase("--file") || args[i].equalsIgnoreCase("--preview")) {
				if(args.length <= i+1) {
					throw new IllegalArgumentException("No file argument provided");
				}
				filename = args[++i];
				mode = args[i].equalsIgnoreCase("--file") ? Mode.REVIEW : Mode.SUMMARIZE;
			}
			else {
				System.out.println("Ignoring invalid argument: " + args[i]);
			}
		}
	}
	public Mode getMode() {
		return mode;
	}
	public String getFilename() {
		if(mode == Mode.LIVE) {
			throw new IllegalStateException("Cannot get filename during a live session!");
		}
		return filename;
	}
}
