package model;

import application.mapGen;

public class Game {

	public Pacman pacman;
	
	public Ghost ghost;

	public Game(mapGen mG) {
		pacman = new Pacman();
		ghost = new Ghost(pacman, mG);
	}
	
	public Pacman getPacman() {
		return pacman;
	}
	
	public Ghost getGhost() {
		return ghost;
	}
	
}
