package sample.ChessPieces;

import javafx.scene.image.Image;
import javafx.util.Pair;
import sample.ChessBoard;

//bástya
public class Rook extends ChessPiece {
    //Pair<oszlop,sor>
    private Pair<Integer, Integer> index;
    private Color color;
    private final int type = 2;

    public Rook(Color color) {
        super(color == Color.WHITE ? "vmi" : "vmi");
        this.color = color;
        this.moveStrategy = new MoveStrategy() {
            @Override
            public boolean CanMoveTo(Pair<Integer, Integer> value) {
                if ((Math.abs(Rook.this.index.getKey() - value.getKey()) <= 7 || Math.abs(Rook.this.index.getValue() - value.getValue()) <= 7) &&
                        ChessBoard.getInstance().getFieldValue(value) == null) {
                    if (Rook.this.index.getKey() == value.getKey()) {
                        if (Rook.this.index.getValue() < value.getValue()) {
                            for (int i = Rook.this.index.getValue() + 1; i < value.getValue(); i++) {
                                if (ChessBoard.getInstance().getFieldValue(new Pair<>(Rook.this.index.getKey(), i)) != null) {
                                    return false;
                                }
                            }
                            return true;
                        } else {
                            for (int i = value.getValue(); i < Rook.this.index.getValue(); i++) {
                                if (ChessBoard.getInstance().getFieldValue(new Pair<>(Rook.this.index.getKey(), i)) != null) {
                                    return false;
                                }
                            }
                            return true;
                        }
                    } else if (Rook.this.index.getValue() == value.getValue()) {
                        if (Rook.this.index.getKey() < value.getKey()) {
                            for (int i = Rook.this.index.getKey() + 1; i < value.getKey(); i++) {
                                if (ChessBoard.getInstance().getFieldValue(new Pair<>(i, Rook.this.index.getValue())) != null) {
                                    return false;
                                }
                            }
                            return true;
                        } else {
                            for (int i = value.getKey(); i < Rook.this.index.getKey(); i++) {
                                if (ChessBoard.getInstance().getFieldValue(new Pair<>(i, Rook.this.index.getValue())) != null) {
                                    return false;
                                }
                            }
                            return true;
                        }
                    }
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

    @Override
    public void Change(ChessPiece value) {
        super.Change(value);
    }
}
