package application;
	
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.stage.Stage;
import model.calculateHighScore;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;


public class highScore extends Application{
	
	private NavigableMap<String,Integer> scoreMap;
	private calculateHighScore calHS = new calculateHighScore();
	
	protected Scene HSscene;
	private Stage primStage;
	
	public highScore(Stage primaryStage) {
		start(primaryStage);
	}
	@Override
	public void start(Stage primaryStage) {
		primStage = primaryStage;
		scoreMap = calHS.scoreMap();
		
		String[] scoreNames = new String[5];
		String[] scoreScores = new String[5];
		
		int i=0;
		for(Entry scoreMaps : scoreMap.entrySet()) {
			scoreNames[i] = scoreMaps.getKey().toString();
			scoreScores[i] = scoreMaps.getValue().toString();
			i++;
			if(i==5)
				break;
		}
		
		Label highScoreLbl = new Label("High Scores"); //Creating Labels
		highScoreLbl.setFont(new Font("Arial", 20));
		
		Label nameLbl1 = new Label(scoreNames[0]);
		Label nameLbl2 = new Label(scoreNames[1]);
		Label nameLbl3 = new Label(scoreNames[2]);
		Label nameLbl4 = new Label(scoreNames[3]);
		Label nameLbl5 = new Label(scoreNames[4]);
		
		Label scoreLbl1 = new Label(scoreScores[0]);
		Label scoreLbl2 = new Label(scoreScores[1]);
		Label scoreLbl3 = new Label(scoreScores[2]);
		Label scoreLbl4 = new Label(scoreScores[3]);
		Label scoreLbl5 = new Label(scoreScores[4]);
		
		Button mainMenuBtn = new Button("Main Menu"); //creating Buttons
		
		
		//===================================================================
		// Grid Pane
		//===================================================================
		
		GridPane root = new GridPane(); //Creating and applying settings to grid pane
		root.setVgap(10);
		root.setPadding(new Insets(10));
		root.setAlignment(Pos.CENTER);
		
		//using Static methods for setting node constraints
												  //    0  1
		GridPane.setConstraints(highScoreLbl, 0,0); // 0 |00|10|
		GridPane.setConstraints(nameLbl1, 0,1);
		GridPane.setConstraints(scoreLbl1, 1,1);
		GridPane.setConstraints(nameLbl2, 0,2);
		GridPane.setConstraints(scoreLbl2, 1,2);
		GridPane.setConstraints(nameLbl3, 0,3);
		GridPane.setConstraints(scoreLbl3, 1,3);
		GridPane.setConstraints(nameLbl4, 0,4);
		GridPane.setConstraints(scoreLbl4, 1,4);
		GridPane.setConstraints(nameLbl5, 0,5);
		GridPane.setConstraints(scoreLbl5, 1,5);
		GridPane.setConstraints(mainMenuBtn, 0, 6, 1 , 1,HPos.RIGHT, VPos.CENTER);
		
	//Adding nodes to root
		root.getChildren().addAll(highScoreLbl, nameLbl1, scoreLbl1,nameLbl2, 
				scoreLbl2,nameLbl3, scoreLbl3,nameLbl4, scoreLbl4,nameLbl5, scoreLbl5,mainMenuBtn); 
				
		//===================================================================
		// Event handlers
		//===================================================================
			mainMenuBtn.setOnAction(this::mainMenuAction);
					
		//===================================================================
		// Drawing Scene
		//===================================================================		
				
		HSscene = new Scene(root, 200,300);
		primStage.setScene(HSscene);
		primStage.setTitle("High Scores");
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primStage.show();
		
	}
	
	private void mainMenuAction(ActionEvent evt)
	{
		mainMenu mM = new mainMenu(primStage);
		primStage.setScene(mM.mMScene);
	}
}
