package sample.ChessPieces;

import javafx.util.Pair;

//huszár
public class Knight extends ChessPiece{
    //Pair<oszlop,sor>
    private Pair<Integer, Integer> index;
    private Color color;
    private final int type = 3;

    public Knight(Color color) {
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
        return this.index;
    }

    @Override
    public void setIndex(Pair<Integer, Integer> i) {
        this.index = i;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public void Hit(Pair<Integer, Integer> value) {

    }
}
