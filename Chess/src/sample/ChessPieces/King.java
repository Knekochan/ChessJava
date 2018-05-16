package sample.ChessPieces;

import javafx.util.Pair;
import sample.ChessBoard;

//király
public class King extends ChessPiece {
    //Pair<oszlop,sor>
    private Pair<Integer, Integer> index;
    private Color color;
    private final int type = 6;

    public King(Color color) {
        super(color == Color.WHITE ? "fehér_kép" : "fekete_kép");
        this.color = color;
        this.moveStrategy = new MoveStrategy() {
            private boolean kingNextTo(Pair<Integer, Integer> value) {
                if ((King.this.index.getKey() + 1 == value.getKey() && King.this.index.getValue() == value.getValue()) ||
                        (King.this.index.getKey() - 1 == value.getKey() && King.this.index.getValue() == value.getValue()) ||
                        (King.this.index.getValue() + 1 == value.getValue() && King.this.index.getKey() == value.getKey()) ||
                        (King.this.index.getValue() - 1 == value.getValue() && King.this.index.getKey() == value.getKey()) ||
                        (King.this.index.getKey() + 1 == value.getKey() && King.this.index.getValue() - 1 == value.getValue()) ||
                        (King.this.index.getKey() - 1 == value.getKey() && King.this.index.getValue() - 1 == value.getValue()) ||
                        (King.this.index.getKey() - 1 == value.getKey() && King.this.index.getValue() + 1 == value.getValue()) ||
                        (King.this.index.getKey() + 1 == value.getKey() && King.this.index.getValue() + 1 == value.getValue())) {
                    return true;
                }
                return false;
            }

            @Override
            public boolean CanMoveTo(Pair<Integer, Integer> value) {
                if (ChessBoard.getInstance().getFieldValue(value) != null){
                    return false;
                }

                if (this.kingNextTo(value)) {
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
