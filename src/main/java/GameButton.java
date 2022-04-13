import javafx.scene.control.Button;

public class GameButton extends Button {
    public int ButtonID; // player
    public int Row;
    public int Col;
    public boolean check;

    GameButton(int Row, int Col) {
        this.Row = Row;
        this.Col = Col;
        this.check = false;
        this.ButtonID = 0;
    }
}
