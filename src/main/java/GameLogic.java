import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Stack;

public class GameLogic extends JavaFXTemplate {
    public static Stack<GameButton> aStack = new Stack<>();

    public static void makeMove (GameButton[][] arr, int row, int col) throws FileNotFoundException {
        if (row == 5) {
            checkMove(arr, row, col);
        }
        if (row < 5) {
            if (arr[row+1][col].check){
                checkMove(arr, row, col);
            } else {
                checkInvalid(row, col);
            }
        }
        System.out.println("outside if here");
    }

    public static void checkMove(GameButton arr[][], int row, int col) throws FileNotFoundException {
        changeColor(arr, row, col);
        if (checkWin(arr, arr[row][col].ButtonID)) {
            if (turn == 0) {
//                playerTurn.setText("Player 2 won!");
//                info.setText("You can exit or play a new game.");
//                disableButtons(arr);
                primStage.setScene(winScene());
            } else if (turn == 1) {
                playerTurn.setText("Player 1 won!");
                info.setText("You can exit or play a new game.");
                disableButtons(arr);
            }
        }
        if (draw(arr)) {
            System.out.println("draw");
        }
    }

    public static void checkInvalid(int row, int col) {
        if (turn == 0) {
            info.setText(String.format("Player 1 moved to %d,%d. This is NOT a valid move. Player 1 pick again.", row, col));
        }
        info.setText(String.format("Player 2 moved to %d,%d. This is NOT a valid move. Player 2 pick again.", row, col));

    }

    public static void undoMove (GameButton arr[][]) {
        // Check if stack is empty
        if (aStack.empty()) {
            return;
        }
        GameButton b = aStack.peek();
        int x = b.Row;
        int y = b.Col;

        if (turn == 0) {
//            arr[x][y].setStyle("-fx-background-color: lightgrey;");
            changeButtonUndo(arr, x, y);
            arr[x][y].ButtonID = 0;
            arr[x][y].check = false;
            arr[x][y].setDisable(false);
            aStack.pop();
            turn = 1;
            playerTurn.setText("Player 2 Turn");
            info.setText(String.format("Player 1 moved to %d,%d. This is a valid move.", x, y));
        } else if (turn == 1) {
            changeButtonUndo(arr, x, y);
//            arr[x][y].setStyle("-fx-background-color: lightgrey;");
            arr[x][y].ButtonID = 0;
            arr[x][y].check = false;
            arr[x][y].setDisable(false);
            aStack.pop();
            turn = 0;
            playerTurn.setText("Player 1 Turn");
            info.setText(String.format("Player 2 moved to %d,%d. This is a valid move.", x, y));
        }
    }

    public static void changeButtonUndo (GameButton arr[][], int x, int y) {
        if (theme == 0) {
            arr[x][y].setStyle("-fx-background-color: lightgrey;");
        } else if (theme == 1 || theme == 2) {
            arr[x][y].setStyle("-fx-background-color: white;");
        } else if (theme == 3) {
            arr[x][y].setGraphic(null);
            arr[x][y].setStyle("-fx-background-color: orange;");
        }
    }

    public static void changeColor (GameButton arr[][], int x, int y) throws FileNotFoundException {
        arr[x][y].setDisable(false);
        if (turn == 0) {
            changeButton(arr, x, y);
//            arr[x][y].setStyle("-fx-background-color: yellow;");
            arr[x][y].ButtonID = 1;
            arr[x][y].check = true;
            arr[x][y].setDisable(true);
            aStack.push(arr[x][y]);
            playerTurn.setText("Player 2 Turn");
            info.setText(String.format("Player 1 moved to %d,%d. This is a valid move.", x, y));
            turn = 1;
        } else if (turn == 1) {
            changeButton(arr, x, y);
//            arr[x][y].setStyle("-fx-background-color: red;");
            arr[x][y].ButtonID = 2;
            arr[x][y].check = true;
            arr[x][y].setDisable(true);
            aStack.push(arr[x][y]);
            turn = 0;
            playerTurn.setText("Player 1 Turn");
            info.setText(String.format("Player 2 moved to %d,%d. This is a valid move.", x, y));
        } else {
            arr[x][y].setStyle("-fx-background-color: lightgrey;");
        }
    }

    public static void changeButton (GameButton arr[][], int x, int y) throws FileNotFoundException {
        if (theme == 0) {
            if (turn == 0) {
                arr[x][y].setStyle("-fx-background-color: yellow;");
            } else {
                arr[x][y].setStyle("-fx-background-color: red;");
            }
        } else if (theme == 1) {
            if (turn == 0) {
                arr[x][y].setStyle("-fx-background-color: plum;");
            } else {
                arr[x][y].setStyle("-fx-background-color: blue;");
            }
        } else if (theme == 2) {
            if (turn == 0) {
                arr[x][y].setStyle("-fx-background-color: lightpink;");
            } else {
                arr[x][y].setStyle("-fx-background-color: lightskyblue;");
            }
        } else if (theme == 3) {
            if (turn == 0) {
                arr[x][y].setStyle("-fx-background-color: yellow;");
                FileInputStream input = new FileInputStream("src/main/resources/naruto_icon.png");
                Image pic = new Image(input);
                ImageView v = new ImageView(pic);
                v.setFitHeight(20);
                v.setFitWidth(20);
                v.setPreserveRatio(true);
                arr[x][y].setGraphic(v);
            } else {
                arr[x][y].setStyle("-fx-background-color: blue;");
                FileInputStream input = new FileInputStream("src/main/resources/sasuke.png");
                Image pic = new Image(input);
                ImageView v = new ImageView(pic);
                v.setFitHeight(20);
                v.setFitWidth(20);
                v.setPreserveRatio(true);
                arr[x][y].setGraphic(v);
            }
        }
    }

    public static boolean checkWin(GameButton arr[][], int player) {
        //check for 4 vertical
        System.out.println(player + " player");
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[0].length - 3; col++){
                if (arr[row][col].ButtonID == player   &&
                        arr[row][col+1].ButtonID == player &&
                        arr[row][col+2].ButtonID == player &&
                        arr[row][col+3].ButtonID == player){
                    System.out.println(row + " " + arr[row][col].ButtonID);
                    System.out.println(row + " " + arr[row][col+1].ButtonID);
                    System.out.println(row + " " + arr[row][col+2].ButtonID);
                    System.out.println(row + " " + arr[row][col+3].ButtonID);
                    return true;
                }
            }
        }

        //check for 4 row
        for(int row = 0; row < arr.length - 3; row++){
            for(int col = 0; col < arr[0].length; col++){
                if (arr[row][col].ButtonID == player   &&
                        arr[row+1][col].ButtonID == player &&
                        arr[row+2][col].ButtonID == player &&
                        arr[row+3][col].ButtonID == player){
                    return true;
                }
            }
        }

        //check downward diagonal
        for(int row = 3; row < arr.length; row++){
            for(int col = 0; col < arr[0].length - 3; col++){
                if (arr[row][col].ButtonID == player   &&
                        arr[row-1][col+1].ButtonID == player &&
                        arr[row-2][col+2].ButtonID == player &&
                        arr[row-3][col+3].ButtonID == player){
                    return true;
                }
            }
        }

        //check upward diagonal
        for(int row = 0; row < arr.length - 3; row++){
            for(int col = 0; col < arr[0].length - 3; col++){
                if (arr[row][col].ButtonID == player   &&
                        arr[row+1][col+1].ButtonID == player &&
                        arr[row+2][col+2].ButtonID == player &&
                        arr[row+3][col+3].ButtonID == player){
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean draw (GameButton arr[][]) {
        for(int row = 0; row < arr.length; row++){
            for(int col = 0; col < arr[0].length; col++) {
                if (!arr[row][col].check) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void newGame (GameButton arr[][]) {
        for(int row = 0; row < arr.length; row++){
            for(int col = 0; col < arr[0].length; col++) {
                arr[row][col].check = false;
                arr[row][col].ButtonID = 0;
                arr[row][col].setStyle("-fx-background-color: lightgrey;");
                arr[row][col].setDisable(false);
            }
        }
        turn = 0;
        aStack.clear();
        playerTurn.setText("Player 1 Turn");
        info.setText("");
    }

    public static void disableButtons (GameButton arr[][]) {
        for(int row = 0; row < arr.length; row++){
            for(int col = 0; col < arr[0].length; col++) {
                arr[row][col].setDisable(true);
            }
        }
    }


}
