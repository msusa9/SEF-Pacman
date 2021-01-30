package application;

import controller.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class mainMenu extends Application{
	
	private Stage primStage;
	public Scene mMScene;
	private User user;
	
	public mainMenu(Stage primaryStage) {
		start(primaryStage);
	}
	
	@Override
	public void start(Stage primaryStage){
		primStage = primaryStage;
		primStage.setTitle("PAC-MAN: SEF Edition");
		Label title = new Label("Welcome to PAC-MAN: SEF Edition!");
		primaryStage.getIcons().add(new Image("./resources/pac 6.png"));
		Button playBtn = new Button("Play");
		Button highScoreBtn = new Button("Highscore");
		Button logoutBtn = new Button("Logout");
		
		GridPane root = new GridPane(); //alignment and grid settings
		root.setVgap(20);
		root.setPadding(new Insets(10));
		root.setAlignment(Pos.CENTER);
		
		GridPane.setConstraints(title, 0, 0, 1, 1, HPos.CENTER, VPos.TOP);//position for elements
		GridPane.setConstraints(playBtn, 0, 2, 1, 1, HPos.CENTER, VPos.CENTER);
		GridPane.setConstraints(highScoreBtn, 0, 3, 1, 1,HPos.CENTER, VPos.CENTER);
		GridPane.setConstraints(logoutBtn, 0, 4, 1 , 1,HPos.CENTER, VPos.CENTER);
		
		root.getChildren().addAll(title, playBtn, highScoreBtn, logoutBtn);//add elements to root
			
		playBtn.setOnAction(this::playAction);//event handlers
		highScoreBtn.setOnAction(this::highScoreAction);
		logoutBtn.setOnAction(this::logoutAction);
			
		mMScene = new Scene(root, 500,300);//display
		primStage.setScene(mMScene);
		primStage.show();
		
	}
	private void playAction(ActionEvent evt)
	{
		mapGen mG = new mapGen(user,primStage);
	}
	private void highScoreAction(ActionEvent evt)
	{
		highScore HS = new highScore(primStage);
		primStage.setScene(HS.HSscene);
	}
	private void logoutAction(ActionEvent evt)
	{
		
		primStage.close();
		changeScene(evt);
	    
	}
	
	public void setUser(User user) {
		this.user=user;
	}

	public void  changeScene(ActionEvent event)
	{
		Stage primaryStage = new Stage();
		try {
			Parent ParentTable = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
			Scene TableView = new Scene(ParentTable);
			Stage TableViewWindow = (Stage) (((Node) event.getSource()).getScene().getWindow());
			
			TableViewWindow.setScene(TableView);
			TableViewWindow.show();
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
}

