package net.benjaminurquhart.tos;

public class Arguments {

	private String filename;
	private Mode mode;
	
	public Arguments(String... args) {
		mode = Mode.LIVE;
		if(args.length > 0) {
			switch(args[0].toLowerCase()) {
			case "--file": mode = Mode.REVIEW; break;
			case "--preview": mode = Mode.SUMMARIZE; break;
			case "--replay": mode = Mode.REPLAY; break;
			default: return;
			}
			if(args.length == 1) {
				System.out.println("Missing filepath argument after " + args[0]);
				System.exit(0);
			}
			else {
				filename = args[1];
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
