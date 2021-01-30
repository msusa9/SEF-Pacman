package controller;

import application.mainMenu;
import application.mapGen;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import model.Pacman;

public class PacmanControl {
	
	private Pacman pacman;
	private mapGen mG;
	
	public PacmanControl(Pacman pacman, mapGen mG) {
		this.pacman = pacman;
		this.mG = mG;
		addClickListener();
	}
	
	public void addClickListener() {

		mapGen.root.setOnKeyPressed(event -> {

			if (event.getCode() == KeyCode.UP)
				pacman.Up();
			else if (event.getCode() == KeyCode.DOWN)
				pacman.Down();
			else if (event.getCode() == KeyCode.LEFT)
				pacman.Left();
			else if (event.getCode() == KeyCode.RIGHT)
				pacman.Right();
			else if (event.getCode() == KeyCode.K)
				pacman.Teleport();
			mG.redrawMap();
		});
	}
}
