package sample.ChessPieces;

import javafx.util.Pair;
import sample.ChessBoard;

//futó
public class Bishop extends ChessPiece {
    //Pair<oszlop,sor>
    private Pair<Integer, Integer> index;
    private Color color;
    private final int type = 4;

    public Bishop(Color color) {
        super(color == Color.WHITE ? "fehér kép" : "fekete_rhkép");
        this.color = color;
        this.moveStrategy = new MoveStrategy() {
            private boolean moveSame(Pair<Integer,Integer> from, Pair<Integer,Integer> to) {
                for (int i  = from.getKey(); i < to.getKey(); i++) {
                    for (int j = from.getValue(); i < to.getValue(); j++) {
                        if (ChessBoard.getInstance().getFieldValue(new Pair<>(i,j)) != null) {
                            return false;
                        }
                    }
                }
                return true;
            }

            private boolean moveDifferent(Pair<Integer,Integer> from, Pair<Integer,Integer> to) {
                for (int i  = from.getKey(); i < to.getKey(); i++) {
                    for (int j = to.getValue(); i < from.getValue(); j++) {
                        if (ChessBoard.getInstance().getFieldValue(new Pair<>(i,j)) != null) {
                            return false;
                        }
                    }
                }
                return true;
            }

            @Override
            public boolean CanMoveTo(Pair<Integer, Integer> value) {
                if (ChessBoard.getInstance().getFieldValue(value) != null &&
                   (Math.abs(Bishop.this.index.getKey() - value.getKey()) !=
                    Math.abs(Bishop.this.index.getValue() - value.getValue()))) {
                    return false;
                }

                if (Bishop.this.index.getKey() > value.getKey() &&
                        Bishop.this.index.getValue() > value.getValue()) {
                    return this.moveSame(value,Bishop.this.index);
                }
                else if (Bishop.this.index.getKey() < value.getKey() &&
                        Bishop.this.index.getValue() > value.getValue()) {
                    return this.moveDifferent(Bishop.this.index,value);
                }
                else if (Bishop.this.index.getKey() > value.getKey() &&
                        Bishop.this.index.getValue() < value.getValue()) {
                    return this.moveDifferent(value,Bishop.this.index);
                }
                else if (Bishop.this.index.getKey() < value.getKey() &&
                        Bishop.this.index.getValue() < value.getValue()){
                    return this.moveSame(Bishop.this.index,value);
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
