package sample.ChessPieces;

import javafx.util.Pair;

//futó
public class Bishop extends ChessPiece{
    //Pair<oszlop,sor>
    private Pair<Integer, Integer> index;
    private Color color;
    private final int type = 2;

    public Bishop(Color color) {
        super(color == Color.WHITE ? "fehér_kép" : "fekete_kép");
        this.color = color;
        this.moveStrategy = new MoveStrategy() {
            @Override
            public boolean CanMoveTo(Pair<Integer, Integer> value) {
                return super.CanMoveTo(value);
            }
        };
    }

    @Override
    public Pair<Integer, Integer> getIndex() {
        return null;
    }

    @Override
    public void setIndex(Pair<Integer, Integer> i) {

    }

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public void Hit(Pair<Integer, Integer> value) {

    }
}
