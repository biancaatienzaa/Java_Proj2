import javafx.application.Application;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class JavaFXTemplate extends Application {
	static Stage primStage;
	private EventHandler<ActionEvent> myHandler;
	public static GameButton arr[][] = new GameButton[6][7];
	static int turn = 0;
	static int theme = 0;
	static Text playerTurn = new Text();
	static Text info = new Text();
	static Text t = new Text();
	static VBox root = new VBox();
//	static GridPane grid = new GridPane();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primStage = primaryStage;
		primaryStage.setTitle("Connect Four");
		primaryStage.setScene(welcomeScene());
		primaryStage.show();
	}

	// A welcome scene
	public Scene welcomeScene() throws FileNotFoundException {
		// -- TEXT --
		Text t = new Text("CONNECT FOUR");
		t.setStyle("-fx-font-family: 'Courier New'; " +
				"-fx-font-weight: bold; " +
				"-fx-font-size: 40;" +
				"-fx-fill: white;" +
				"-fx-max-height: 200;");

		// -- BUTTON --
		Button b = new Button("START GAME");
		b.setStyle("-fx-font-family: Courier; " +
				"-fx-font-weight: bold;" +
				"-fx-font-size: 22;" +
				"-fx-max-width: 200;" +
				"-fx-max-height: 50;" +
				"-fx-border-color: blue;" +
				"-fx-border-width: 5;" +
				"-fx-border-radius: 50");

		b.setOnAction(e -> {
			primStage.setScene(gamePlayScene());
			primStage.show();
		});

		// -- BACKGROUND --
		FileInputStream input = new FileInputStream("src/main/resources/background.jpg");
		Image image = new Image(input);
		BackgroundImage backgroundimage = new BackgroundImage(image,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		Background background = new Background(backgroundimage);


		// -- VBOX AND SCENE --
		root = new VBox(40, t, b);
		root.setAlignment(Pos.CENTER);
		root.setBackground(background);
		return new Scene(root, 900,600);
	}

	// A gameplay scene
	public Scene gamePlayScene() {
		BorderPane borderPane = new BorderPane();

		// -- MENU --
		MenuBar menu = new MenuBar();
		menu(menu);

		// -- WHO'S TURN AREA --
		playerTurn.setText("Player 1 Turn");
		playerTurn.setStyle("-fx-font-family: 'Courier New';" +
				"-fx-font-size: 20;" +
				"-fx-font-weight: bold");

		// -- INFO --
		info.setStyle("-fx-font-family: 'Courier New';" +
				"-fx-font-size: 17;" +
				"-fx-font-color: #676767;");

		// -- GAME BOARD --
		GridPane grid = new GridPane();
		addGrid(grid);
		grid.setHgap(5);
		grid.setVgap(5);
		grid.setAlignment(Pos.CENTER);

		// -- SCENE GROUP --
		root = new VBox(10, menu, playerTurn, info, grid);
		root.setAlignment(Pos.TOP_CENTER);

		// Group root = new Group(upperBox);
		return new Scene(root, 900,600);
	}

	public static Scene winScene () throws FileNotFoundException {
		// -- BUTTON --
		Button b = new Button("Play again!");
		b.setStyle("-fx-font-family: Courier; " +
				"-fx-font-weight: bold;" +
				"-fx-font-size: 22;" +
				"-fx-max-width: 200;" +
				"-fx-max-height: 50;" +
				"-fx-border-color: blue;" +
				"-fx-border-width: 5;" +
				"-fx-border-radius: 50");
		Button b2 = new Button("Exit");
		b.setStyle("-fx-font-family: Courier; " +
				"-fx-font-weight: bold;" +
				"-fx-font-size: 22;" +
				"-fx-max-width: 200;" +
				"-fx-max-height: 50;" +
				"-fx-border-color: blue;" +
				"-fx-border-width: 5;" +
				"-fx-border-radius: 50");

		b.setOnAction(e -> GameLogic.newGame(arr));
		b2.setOnAction(e -> Platform.exit());

		// -- BACKGROUND --
		FileInputStream input = new FileInputStream("src/main/resources/youwin2.png");
		Image image = new Image(input);
		BackgroundImage backgroundimage = new BackgroundImage(image,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		Background background = new Background(backgroundimage);


		// -- VBOX AND SCENE --
		root = new VBox(40, t, b);
		root.setAlignment(Pos.CENTER);
		root.setBackground(background);
		return new Scene(root, 900,600);
	}

	// A void function to initialize the top menu of the game and
	// all its menu items and their event handlers
	public void menu(MenuBar menu) {
		Menu gamePlay = new Menu("Game Play");
		Menu themes = new Menu("Themes");
		Menu options = new Menu("Options");

		menu.getMenus().addAll(gamePlay, themes, options);
		menu.setStyle("-fx-font-family: 'Courier New';" +
				"-fx-font-size: 14;");

		// -- MENU ITEMS --
		// Reverse Move - Clicking “reverse move” several times will undo the previous N moves
		MenuItem reverse = new MenuItem("Reverse Move");
		reverse.setOnAction(e -> {
			GameLogic.undoMove(arr);
		});
		gamePlay.getItems().add(reverse);

		// Themes menu item, TA ask how to
		MenuItem original = new MenuItem("Original Theme");
		original.setOnAction(e -> {
			try {
				theme = 0;
				themeClass.changeOrig(arr, root);
			} catch (FileNotFoundException fileNotFoundException) {
				fileNotFoundException.printStackTrace();
			}
		});
		MenuItem pacMan = new MenuItem("PacMan Theme");
		pacMan.setOnAction(e -> {
			try {
				theme = 1;
				themeClass.changePacman(arr, root);
			} catch (FileNotFoundException fileNotFoundException) {
				fileNotFoundException.printStackTrace();
			}
		});
		MenuItem japan = new MenuItem("Japan Theme");
		japan.setOnAction(e -> {
			try {
				theme = 2;
				themeClass.changeJapan(arr, root);
			} catch (FileNotFoundException fileNotFoundException) {
				fileNotFoundException.printStackTrace();
			}
		});
		MenuItem naruto = new MenuItem("Naruto Theme");
		naruto.setOnAction(e -> {
			try {
				theme = 3;
				themeClass.changeNaruto(arr, root);
			} catch (FileNotFoundException fileNotFoundException) {
				fileNotFoundException.printStackTrace();
			}
		});
		themes.getItems().addAll(original, pacMan, japan, naruto);

		// Options menu items
		MenuItem how = new MenuItem("How to play"); // will display some text on how to play the game.
		Alert a = new Alert(Alert.AlertType.INFORMATION);
		how.setOnAction(e -> {
			a.setContentText("The two players then alternate turns dropping one of their discs at a time into an unfilled column, until the second player, with red discs, achieves a diagonal four in a row, and wins the game. If the board fills up before either player achieves four in a row, then the game is a draw.");
			a.show();
		});
		MenuItem newGame = new MenuItem("New Game");
		newGame.setOnAction(e -> GameLogic.newGame(arr));
		MenuItem exit = new MenuItem("Exit"); // exit and terminate the program
		exit.setOnAction(e -> Platform.exit());
		options.getItems().addAll(how, newGame, exit);
	}

	// A void function to initialize the game board with GridPane
	// uses for loops to create a 2D array of buttons and uses
	// MyButton custom class
	public void addGrid (GridPane grid) {
		for (int j = 0; j < 7; j++) {
			for (int i = 0; i < 6; i++) {
				GameButton b1 = new GameButton(i, j);
				b1.setMinSize(40, 40);
				b1.setStyle("-fx-background-color: lightgrey;");
				myHandler = e -> {
					GameButton button = (GameButton) e.getSource();
					int r = button.Row;
					int c = button.Col;
					System.out.println(r + " r = row");
					System.out.println(c + " c = col");
					try {
						GameLogic.makeMove(arr, r, c);
					} catch (FileNotFoundException fileNotFoundException) {
						fileNotFoundException.printStackTrace();
					}
				};
				b1.setOnAction(myHandler);
				arr[i][j] = b1;
				grid.add(b1, j, i);
			}
		}
	}
}

