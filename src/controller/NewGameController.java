package controller;

import application.mainMenu;
import application.mapGen;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Pacman;
import controller.User;

public class NewGameController {
	
	private Stage stage;
	private User user;
	
	public NewGameController(Stage stage, User user) {
		this.user=user;
		this.stage=stage;
		addClickListener();
	}
	
	public void addClickListener() {

		mapGen.root.setOnKeyPressed(event -> {

			if (event.getCode() == KeyCode.SPACE) {
				mainMenu mM = new mainMenu(stage);
				mM.setUser(user);
				stage.setScene(mM.mMScene);
			}
		});
	}
}
