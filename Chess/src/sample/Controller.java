package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Controller {
    @FXML
    private Canvas canvas;
    private GraphicsContext gc;


    @FXML
    public void initialize() {
        this.gc = this.canvas.getGraphicsContext2D();
        this.draw();
    }

    public void draw() {
        this.gc.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());

        // háttér
        this.gc.setFill(Color.BLACK);
        this.gc.fillRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());

        // rajzolások
        ChessBoard.getInstance().draw(gc);
    }
}
