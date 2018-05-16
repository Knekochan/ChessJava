package sample.ChessPieces;

import javafx.util.Pair;
import sample.ChessBoard;

//királynő
public class Queen extends ChessPiece{
    //Pair<oszlop,sor>
    private Pair<Integer, Integer> index;
    private Color color;
    private final int type = 5;

    public Queen(Color color) {
        super(color == Color.WHITE ? "fehér_kép" : "fekete_kép");
        this.color = color;
        this.moveStrategy = new MoveStrategy() {
            private boolean queenDiagonalDifferent(Pair<Integer, Integer> from, Pair<Integer, Integer> to) {
                for (int i  = from.getKey(); i < to.getKey(); i++) {
                    for (int j = from.getValue(); i < to.getValue(); j++) {
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
                        ((Math.abs(Queen.this.index.getKey() - value.getKey()) != Math.abs(Queen.this.index.getValue() - value.getValue())) ||
                          Queen.this.index.getKey() != value.getKey() || Queen.this.index.getValue() != value.getKey())) {
                    return false;
                }

                if (Queen.this.index.getKey() < value.getKey() && Queen.this.index.getValue() > value.getValue()) {
                    //this.queenDiagonalDifferent()
                }
                else if (Queen.this.index.getKey() < value.getKey() && Queen.this.index.getValue() < value.getValue()) {

                }
                else if (Queen.this.index.getKey() > value.getKey() && Queen.this.index.getValue() < value.getValue()) {

                }
                else if (Queen.this.index.getKey() > value.getKey() && Queen.this.index.getValue() > value.getValue()) {

                }
                else if (Queen.this.index.getKey() < value.getKey() && Queen.this.index.getValue() == value.getValue()) {

                }
                else if (Queen.this.index.getKey() == value.getKey() && Queen.this.index.getValue() < value.getValue()) {

                }
                else if (Queen.this.index.getKey() > value.getKey() && Queen.this.index.getValue() == value.getValue()) {

                }
                else if (Queen.this.index.getKey() == value.getKey() && Queen.this.index.getValue() > value.getValue()) {

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
