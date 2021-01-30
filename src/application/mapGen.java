package application;

import java.util.ArrayList;

import controller.NewGameController;
import controller.PacmanControl;
import controller.User;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Cell;
import model.Constant;
import model.Game;
import model.Ghost;
import model.Grid;
import model.Pacman;
import model.Position;
import model.calculateHighScore;
import model.fruitsAndObstacles;

public class mapGen extends Application {

	public static Pane root;
	private static Grid grid;
	static Timeline timeline;
	private Scene scene;
	private Stage stage;
	private Game game;
	private User user;
	private calculateHighScore calHS;
	private fruitsAndObstacles fAO;
	
	public mapGen(User user, Stage primaryStage) {
		stage=primaryStage;
		this.user=user;
		calHS = new calculateHighScore();
		fAO = new fruitsAndObstacles();
		start(stage);
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		//Initialise ScreenDimentions
		primaryStage.setMaximized(true);
		initScreenDimentions();
		
		//Generate Map
		generateMap(stage).show();
		
		startTimeline();
		
	}
	
	public void initScreenDimentions(){
		Screen screen = Screen.getPrimary();
		Rectangle2D visualBounds = screen.getVisualBounds();
		Constant.screenX = visualBounds.getMinX();
		Constant.screenY = visualBounds.getMinY();
		Constant.screenWidth = visualBounds.getWidth();
		Constant.screenHeight = visualBounds.getHeight();
	}

	public Stage generateMap(Stage stage) {

		game = new Game(this);
		game.getGhost();
		game.getPacman();

		this.stage = stage;
		root = new Pane();
		root.setStyle("-fx-background-color: black");
		Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
		scene = new Scene(root, Constant.screenWidth, Constant.screenHeight);

		fAO.initObstacles();
		fAO.initFruits();
		
		new PacmanControl(game.getPacman(), this);

		// Create Grid
		grid = new Grid();

		for (int i = 0; i < Constant.COLUMN_CELL_COUNT; i++) {

			for (int j = 0; j < Constant.ROW_CELL_COUNT; j++) {

				Position position = new Position(i, j);
				// Random random = new Random();
				int type = Constant.OBJECT;

				// Check if not boundary
				if (i != Constant.COLUMN_CELL_COUNT - 1 && j != Constant.ROW_CELL_COUNT - 1 && i != 0 && j != 0) {
					if (i == 1 && j == 1)
						type = Constant.EMPTY;
					else if (fAO.isFruit(position))
						type = Constant.FRUIT;
					else if (fAO.isObstacle(position))
						type = Constant.OBJECT;
					else
						type = Constant.COIN;
				}

				// type = random.nextInt(2 - 0 + 1) + 0;
				Cell cell = new Cell(position, type);
				grid.addCell(cell);

				root.getChildren().add(cell.getNode());
			}

		}
		stage.setScene(scene);

		return stage;

	}

	public void redrawMap() {

		initScreenDimentions();

		root.getChildren().clear();

		for (int i = 0; i < Constant.ROW_CELL_COUNT; i++)
			for (int j = 0; j < Constant.COLUMN_CELL_COUNT; j++)
				root.getChildren().add(grid.getCell(i, j).getNode());

		root.getChildren().add(game.getPacman().getNode());

		root.getChildren().add(game.getGhost().getNode());

		Position scorePosition = new Position(1, Constant.COLUMN_CELL_COUNT - 2);
		Text text = new Text(scorePosition.x - 30, scorePosition.y - 10, "SCORE : " + game.getPacman().getScore());
		text.setFill(Color.RED);
		text.setFont(Font.font("IMPACT", Constant.screenWidth / 35));

		root.getChildren().add(text);

		root.requestFocus();

	}

	public void gameEnded() {

		new NewGameController(stage, user);
		
		timeline.stop();
		
		calHS.addScore(user.getName(), game.getPacman().getScore());

		Text text = new Text(" GAME OVER! \n YOUR SCORE \n\t    " + game.getPacman().getScore());
		text.setFill(Color.RED);
		text.setFont(Font.font("IMPACT", Constant.screenWidth / 20));
		text.layoutXProperty().bind(scene.widthProperty().divide(2).subtract(Constant.screenWidth / 8.7));
		text.layoutYProperty().bind(scene.heightProperty().divide(2).subtract(Constant.screenHeight / 9.7));

		Text replayText = new Text("Press Space to go to Main Menu");
		replayText.setFill(Color.RED);
		replayText.setFont(Font.font("IMPACT", Constant.screenWidth / 40));
		replayText.layoutXProperty().bind(scene.widthProperty().divide(2).subtract(Constant.screenWidth / 8.7));
		replayText.layoutYProperty().bind(scene.heightProperty().divide(1.2));

		Rectangle rectangle = new Rectangle(Constant.screenWidth / 3, Constant.screenHeight / 2.5);
		rectangle.layoutXProperty().bind(scene.widthProperty().divide(2).subtract(rectangle.getWidth() / 2));
		rectangle.layoutYProperty().bind(scene.heightProperty().divide(2).subtract(rectangle.getHeight() / 2));
		rectangle.setFill(Color.GRAY);
		rectangle.setStroke(Color.RED);
		root.getChildren().add(rectangle);
		root.getChildren().add(text);
		root.getChildren().add(replayText);

	}

	public void startTimeline() {

		timeline = new Timeline(new KeyFrame(Duration.millis(250), event -> {
			game.getGhost().moveGhost();
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();

	}
}