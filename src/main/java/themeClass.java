import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class themeClass extends JavaFXTemplate {

    public static void changeOrig(GameButton arr[][], VBox root) throws FileNotFoundException {
        playerTurn.setStyle("-fx-font-family: 'Courier New';" +
                "-fx-font-size: 20;" +
                "-fx-font-weight: bold");
        info.setStyle("-fx-font-family: 'Courier New';" +
                "-fx-font-size: 17;" +
                "-fx-font-color: #676767;");
        root.setStyle("-fx-background-color: #FFFFFF;");
        for(int row = 0; row < arr.length; row++){
            for(int col = 0; col < arr[0].length; col++) {
                if(!arr[row][col].check) {
                    arr[row][col].setStyle("-fx-background-color: lightgrey;");
                }
                if (arr[row][col].ButtonID == 1) {
                    arr[row][col].setStyle("-fx-background-color: yellow;");
                } else if (arr[row][col].ButtonID == 2) {
                    arr[row][col].setStyle("-fx-background-color: red;");
                }
                arr[row][col].setGraphic(null);
            }
        }
    }

    public static void changeJapan(GameButton arr[][], VBox root) throws FileNotFoundException {
        FileInputStream input = new FileInputStream("src/main/resources/japan.jpg");
        Image image = new Image(input);
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundimage);
        root.setBackground(background);
        for(int row = 0; row < arr.length; row++){
            for(int col = 0; col < arr[0].length; col++) {
                if(!arr[row][col].check) {
                    arr[row][col].setStyle("-fx-background-color: white;");
                }
                if (arr[row][col].ButtonID == 1) {
                    arr[row][col].setStyle("-fx-background-color: lightpink;");
                } else if (arr[row][col].ButtonID == 2) {
                    arr[row][col].setStyle("-fx-background-color: lightskyblue;");
                }
                arr[row][col].setGraphic(null);
            }
        }
    }

    public static void changeNaruto(GameButton arr[][], VBox root) throws FileNotFoundException {
        FileInputStream input = new FileInputStream("src/main/resources/naruto.jpg");
        Image image = new Image(input);
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundimage);
        root.setBackground(background);
        for(int row = 0; row < arr.length; row++){
            for(int col = 0; col < arr[0].length; col++) {
                if(!arr[row][col].check) {
                    arr[row][col].setStyle("-fx-background-color: orange;");
                }
                if (arr[row][col].ButtonID == 1) {
                    arr[row][col].setStyle("-fx-background-color: yellow;");
                    FileInputStream input2 = new FileInputStream("src/main/resources/naruto_icon.png");
                    Image pic = new Image(input2);
                    ImageView v = new ImageView(pic);
                    v.setFitHeight(20);
                    v.setFitWidth(20);
                    v.setPreserveRatio(true);
                    arr[row][col].setGraphic(v);
                } else if (arr[row][col].ButtonID == 2) {
                    arr[row][col].setStyle("-fx-background-color: blue;");
                    FileInputStream input3 = new FileInputStream("src/main/resources/sasuke.png");
                    Image pic = new Image(input3);
                    ImageView v = new ImageView(pic);
                    v.setFitHeight(20);
                    v.setFitWidth(20);
                    v.setPreserveRatio(true);
                    arr[row][col].setGraphic(v);
                }
            }
        }
    }

    public static void changePacman(GameButton arr[][], VBox root) throws FileNotFoundException {
        playerTurn.setStyle("-fx-fill: white");
        FileInputStream input = new FileInputStream("src/main/resources/pacman.png");
        Image image = new Image(input);
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundimage);
        root.setBackground(background);
        for(int row = 0; row < arr.length; row++){
            for(int col = 0; col < arr[0].length; col++) {
                if(!arr[row][col].check) {
                    arr[row][col].setStyle("-fx-background-color: white;");
                }
                if (arr[row][col].ButtonID == 1) {
                    arr[row][col].setStyle("-fx-background-color: plum;");
                } else if (arr[row][col].ButtonID == 2) {
                    arr[row][col].setStyle("-fx-background-color: blue;");
                }
                arr[row][col].setGraphic(null);
            }
        }
    }

}
