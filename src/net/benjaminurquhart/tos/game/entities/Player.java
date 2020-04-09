package net.benjaminurquhart.tos.game.entities;

public class Player {
	
	private static final Killer[] NO_KILLERS = new Killer[0];

	private boolean alive;
	private int position;
	private String name;
	private Role role;
	
	private Killer[] killers;
	
	public Player(String name, int position) {
		this.position = position;
		this.name = name;
		
		this.killers = NO_KILLERS;
		this.alive = true;
	}
	
	public Killer[] getKillers() {
		return killers;
	}
	public int getPosition() {
		return position;
	}
	public boolean isAlive() {
		return alive;
	}
	public String getName() {
		return name;
	}
	public Role getRole() {
		return role;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	public void kill(Killer... killers) {
		if(this.killers.length > 0) {
			Killer[] tmp = new Killer[this.killers.length+killers.length];
			System.arraycopy(this.killers, 0, tmp, 0, this.killers.length);
			System.arraycopy(killers, 0, tmp, this.killers.length, killers.length);
			this.killers = tmp;
		}
		else {
			this.killers = killers;
		}
		alive = false;
		//System.out.println("Killed " + name + " (" + position + ")");
	}
	public void resurrect() {
		killers = NO_KILLERS;
		alive = true;
	}
	@Override
	public String toString() {
		return name;
	}
}
