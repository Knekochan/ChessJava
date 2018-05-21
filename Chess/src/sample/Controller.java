package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javafx.scene.input.MouseEvent;
import javafx.util.Pair;
import sample.ChessPieces.Bishop;

public class Controller {
    @FXML
    private Canvas canvas;
    private GraphicsContext gc;

    @FXML
    public void initialize() {
        this.gc = this.canvas.getGraphicsContext2D();
        //this.canvas.addEventFilter(MouseEvent.MOUSE_PRESSED,new MouseEvent());
        this.draw();

        this.canvas.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                /*
                Pair<Integer,Integer> selectedPiece = ChessBoard.getInstance().getChessPieceAt((int)event.getX(),(int)event.getY());
                Pair<Integer,Integer> movePlace = ChessBoard.getInstance().getChessPieceAt((int)event.getX(),(int)event.getY());

                ChessBoard.getInstance().movePiece(ChessBoard.getInstance().getFieldValue(selectedPiece),movePlace);
                */
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
