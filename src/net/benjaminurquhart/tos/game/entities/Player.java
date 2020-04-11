package net.benjaminurquhart.tos.game.entities;

import java.util.HashSet;
import java.util.Set;

import net.benjaminurquhart.tos.game.PlayerTag;

public class Player {
	
	private static final Killer[] NO_KILLERS = new Killer[0];
	
	private Set<PlayerTag> tags;

	private boolean alive;
	private int position;
	private String name;
	private Role role;
	
	private Killer[] killers;
	
	private Player target;
	
	public Player(String name, int position) {
		this.position = position;
		this.name = name;
		
		this.killers = NO_KILLERS;
		this.alive = true;
		
		this.tags = new HashSet<>();
	}
	
	public Set<PlayerTag> getTags() {
		return tags;
	}
	public Killer[] getKillers() {
		return killers;
	}
	public Player getTarget() {
		return target;
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
	
	public void setTarget(Player target) {
		this.target = target;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	public void removeTag(PlayerTag tag) {
		this.tags.remove(tag);
	}
	public void addTag(PlayerTag tag) {
		this.tags.add(tag);
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
