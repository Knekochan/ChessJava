package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javafx.scene.input.MouseEvent;
import javafx.util.Pair;
import sample.ChessPieces.Bishop;

import java.util.ArrayList;

public class Controller {
    @FXML
    private Canvas canvas;
    private GraphicsContext gc;

    private ArrayList<Pair<Integer, Integer>> indexs;

    @FXML
    public void initialize() {
        this.gc = this.canvas.getGraphicsContext2D();

        this.indexs = new ArrayList<>();

        this.draw();

        this.canvas.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Controller.this.indexs.add(ChessBoard.getInstance().getChessPieceAt((int) event.getX(), (int) event.getY()));

                if (Controller.this.indexs.size() > 1) {
                    if (ChessBoard.getInstance().getFieldValue(Controller.this.indexs.get(0)) != null) {
                        ChessBoard.getInstance().movePiece(ChessBoard.getInstance().getFieldValue(Controller.this.indexs.get(0)), Controller.this.indexs.get(1));
                    }
                    Controller.this.indexs.clear();
                }

                Controller.this.draw();
            }
        });

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
