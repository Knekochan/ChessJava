package sample.ChessPieces;

import javafx.util.Pair;
import sample.ChessBoard;

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
            private boolean knightJump(Pair<Integer, Integer> value) {
                if ((Knight.this.index.getKey() + 1 == value.getKey() && Knight.this.index.getValue() - 2 == value.getValue()) ||
                (Knight.this.index.getKey() + 2 == value.getKey() && Knight.this.index.getValue() - 1 == value.getValue()) ||
                (Knight.this.index.getKey() + 2 == value.getKey() && Knight.this.index.getValue() + 1 == value.getValue()) ||
                (Knight.this.index.getKey() + 1 == value.getKey() && Knight.this.index.getValue() + 2 == value.getValue()) ||
                (Knight.this.index.getKey() - 1 == value.getKey() && Knight.this.index.getValue() + 2 == value.getValue()) ||
                (Knight.this.index.getKey() - 2 == value.getKey() && Knight.this.index.getValue() + 1 == value.getValue()) ||
                (Knight.this.index.getKey() - 2 == value.getKey() && Knight.this.index.getValue() - 1 == value.getValue()) ||
                (Knight.this.index.getKey() - 1 == value.getKey() && Knight.this.index.getValue() - 2 == value.getValue())) {
                    return true;
                }
                return false;
            }

            @Override
            public boolean CanMoveTo(Pair<Integer, Integer> value) {
                if (ChessBoard.getInstance().getFieldValue(value) != null) {
                    return false;
                }

                if (this.knightJump(value)) {
                    return true;
                }

                return false;
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
