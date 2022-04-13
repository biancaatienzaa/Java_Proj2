import static org.junit.jupiter.api.Assertions.*;

import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;

class MyTest {
	public static GameLogic gameLogic;

	public static class NonApp extends Application {
		@Override
		public void start(Stage primaryStage) throws Exception {
			// do nothing here since we just need to start the JavaFX toolkit
		}
	}

	@BeforeEach
	public void initJFX() throws Exception {
		Thread t = new Thread("JavaFX Application Thread") {
			public void run() {
				Application.launch(NonApp.class);
			}
		};
		t.setDaemon(true);
		t.start();
	}

	// MAKE MOVE TESTS
	@Test
	void test1() throws FileNotFoundException {
		JavaFXTemplate javaFXTemplate = new JavaFXTemplate();
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 7; j++){
				javaFXTemplate.arr[i][j] = new GameButton(i, j);
			}
		}
		gameLogic = new GameLogic();
		gameLogic.changeColor(javaFXTemplate.arr, 1, 2);
		assertEquals(true, javaFXTemplate.arr[1][2].check);
	}

	@Test
	void test2() throws FileNotFoundException {
		JavaFXTemplate javaFXTemplate = new JavaFXTemplate();
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 7; j++){
				javaFXTemplate.arr[i][j] = new GameButton(i, j);
			}
		}
		gameLogic = new GameLogic();
		gameLogic.makeMove(javaFXTemplate.arr, 3, 3);
		assertEquals(0, gameLogic.turn);
		gameLogic.makeMove(javaFXTemplate.arr, 2, 2);
		assertEquals(0, gameLogic.turn);
		gameLogic.makeMove(javaFXTemplate.arr, 4, 4);
		assertEquals(0, gameLogic.turn);
	}

	@Test
	void test3() throws FileNotFoundException {
		JavaFXTemplate javaFXTemplate = new JavaFXTemplate();
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 7; j++){
				javaFXTemplate.arr[i][j] = new GameButton(i, j);
			}
		}
		gameLogic = new GameLogic();
		gameLogic.changeColor(javaFXTemplate.arr, 5, 1);
		assertEquals(1, javaFXTemplate.arr[5][1].ButtonID);
	}

	@Test
	void test5() {
		gameLogic = new GameLogic();
		int player = 1;
		for (int j = 0; j < 7; j++) {
			for (int i = 0; i < 6; i++) {
				gameLogic.arr[i][j] = new GameButton(i, j);
			}
		}
		if (gameLogic.arr[5][0].ButtonID == player && gameLogic.arr[4][0].ButtonID == player && gameLogic.arr[3][0].ButtonID == player && gameLogic.arr[2][0].ButtonID == player) {
			assertEquals(true, gameLogic.checkWin(gameLogic.arr, player));
		}
	}

	@Test
	void test6() {
		gameLogic = new GameLogic();
		int player = 1;
		for (int j = 0; j < 7; j++) {
			for (int i = 0; i < 6; i++) {
				gameLogic.arr[i][j] = new GameButton(i, j);
			}
		}
		if (gameLogic.arr[0][1].ButtonID == player && gameLogic.arr[0][2].ButtonID == player && gameLogic.arr[0][3].ButtonID == player && gameLogic.arr[0][4].ButtonID == player) {
			assertEquals(true, gameLogic.checkWin(gameLogic.arr, player));
		}


	}

	@Test
	void test7() {
		gameLogic = new GameLogic();
		int player = 1;
		for (int j = 0; j < 7; j++) {
			for (int i = 0; i < 6; i++) {
				gameLogic.arr[i][j] = new GameButton(i, j);
			}
		}
		if (gameLogic.arr[5][1].ButtonID == player && gameLogic.arr[4][2].ButtonID == player && gameLogic.arr[3][3].ButtonID == player && gameLogic.arr[2][4].ButtonID == player) {
			assertEquals(true, gameLogic.checkWin(gameLogic.arr, player));
		}
	}

	@Test
	void test8() {
		gameLogic = new GameLogic();
		int player = 1;
		for (int j = 0; j < 7; j++) {
			for (int i = 0; i < 6; i++) {
				gameLogic.arr[i][j] = new GameButton(i, j);
			}
		}
		if (gameLogic.arr[4][5].ButtonID == player && gameLogic.arr[3][4].ButtonID == player && gameLogic.arr[2][3].ButtonID == player && gameLogic.arr[1][2].ButtonID == player) {
			assertEquals(true, gameLogic.checkWin(gameLogic.arr, player));
		}

	}

	@Test
	void test9() {
		gameLogic = new GameLogic();
		for (int j = 0; j < 7; j++) {
			for (int i = 0; i < 6; i++) {
				if(gameLogic.arr[i][j].check) {
					assertEquals(true, gameLogic.draw(gameLogic.arr));
				}
			}
		}
	}

	@Test
	void test10() throws FileNotFoundException {
		JavaFXTemplate javaFXTemplate = new JavaFXTemplate();
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 7; j++){
				javaFXTemplate.arr[i][j] = new GameButton(i, j);
			}
		}
		gameLogic = new GameLogic();
		gameLogic.makeMove(javaFXTemplate.arr, 2, 3);
		gameLogic.makeMove(javaFXTemplate.arr, 1, 2);
		gameLogic.makeMove(javaFXTemplate.arr, 3, 4);
		gameLogic.makeMove(javaFXTemplate.arr, 5, 0);
		gameLogic.makeMove(javaFXTemplate.arr, 4, 3);
		gameLogic.newGame(javaFXTemplate.arr);
		for (int j = 0; j < 7; j++) {
			for (int i = 0; i < 6; i++) {
				assertFalse(gameLogic.arr[i][j].check);
				gameLogic.arr[i][j].ButtonID = 0;
				gameLogic.arr[i][j].setStyle("-fx-background-color: lightgrey;");
				gameLogic.arr[i][j].setDisable(false);
			}
		}
		int turn = 0;
		assertEquals(gameLogic.aStack.size(), 0);
	}

	@Test
	void test11() throws FileNotFoundException {
		JavaFXTemplate javaFXTemplate = new JavaFXTemplate();
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 7; j++){
				javaFXTemplate.arr[i][j] = new GameButton(i, j);
			}
		}
		gameLogic = new GameLogic();
		gameLogic.makeMove(javaFXTemplate.arr, 1, 3);
		gameLogic.makeMove(javaFXTemplate.arr, 3, 5);
		gameLogic.undoMove(javaFXTemplate.arr);
		assertEquals(0, javaFXTemplate.arr[3][5].ButtonID);
	}

	@Test
	void test12() throws FileNotFoundException {
		JavaFXTemplate javaFXTemplate = new JavaFXTemplate();
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 7; j++){
				javaFXTemplate.arr[i][j] = new GameButton(i, j);
			}
		}
		gameLogic = new GameLogic();
		gameLogic.makeMove(javaFXTemplate.arr, 3, 5);
		gameLogic.undoMove(javaFXTemplate.arr);
		assertEquals(false, javaFXTemplate.arr[3][5].check);
	}

}







