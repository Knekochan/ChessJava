package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javafx.scene.input.MouseEvent;
import javafx.util.Pair;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    private Canvas canvas;
    private GraphicsContext gc;

    private ArrayList<Pair<Integer, Integer>> indexes;

    @FXML
    public void initialize() {
        this.gc = this.canvas.getGraphicsContext2D();

        this.indexes = new ArrayList<>();
        ArrayList<Pair<String, Integer>> scores = Database.getInstance().getScores();
        for(Pair<String, Integer> p : scores) {
            System.out.println(p.getKey() + " " + p.getValue());
        }

        this.runTests();

        this.draw();

    }

    private void runTests() {
        Result result = JUnitCore.runClasses(ChessTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }

    public void canvasClick(MouseEvent event) {
        System.out.println(event.getX() + " " + event.getY());
        this.indexes.add(ChessBoard.getInstance().getChessPieceAt((int) event.getX(), (int) event.getY()));

        if (this.indexes.size() > 1) {
            if (ChessBoard.getInstance().getFieldValue(this.indexes.get(0)) != null) {
                ChessBoard.getInstance().movePiece(ChessBoard.getInstance().getFieldValue(this.indexes.get(0)), this.indexes.get(1));
            }
            this.indexes.clear();
        }

        this.draw();
    }

    public void draw() {
        this.gc.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());

        // háttér
        this.gc.setFill(Color.BLACK);
        this.gc.fillRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
        //this.gc.setFill(Color.WHITE);
        //this.gc.fillRect(0, 0, 100, 100);
        // rajzolások
        ChessBoard.getInstance().draw(gc);
    }
}
