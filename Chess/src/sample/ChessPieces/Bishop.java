package sample.ChessPieces;

import javafx.util.Pair;
import sample.ChessBoard;

import java.io.File;

//futó
public class Bishop extends ChessPiece {
    //Pair<oszlop,sor>
    private Pair<Integer, Integer> index;
    private Color color;
    private final int type = 4;

    public Bishop(Color color) {
        super(color == Color.WHITE ? new File("C:\\Users\\User\\Documents\\IntelliJ IDEA\\Chess\\src\\sample\\PieceImages\\BishopWhite.png").toURI().toString() :
                new File("C:\\Users\\User\\Documents\\IntelliJ IDEA\\Chess\\src\\sample\\PieceImages\\BishopBlack.png").toURI().toString());
        this.color = color;
        this.moveStrategy = new MoveStrategy() {
            private boolean moveSame(Pair<Integer, Integer> from, Pair<Integer, Integer> to) {
                for (int i = from.getKey(); i < to.getKey(); i++) {
                    for (int j = from.getValue(); i < to.getValue(); j++) {
                        if (ChessBoard.getInstance().getFieldValue(new Pair<>(i, j)) != null) {
                            return false;
                        }
                    }
                }
                return true;
            }

            private boolean moveDifferent(Pair<Integer, Integer> from, Pair<Integer, Integer> to) {
                for (int i = from.getKey(); i < to.getKey(); i++) {
                    for (int j = to.getValue(); i < from.getValue(); j++) {
                        if (ChessBoard.getInstance().getFieldValue(new Pair<>(i, j)) != null) {
                            return false;
                        }
                    }
                }
                return true;
            }

            @Override
            public boolean CanMoveTo(Pair<Integer, Integer> value) {
                boolean help = false;

                //megnézi jó irányba lép e vagy sem
                if (Math.abs(Bishop.this.index.getKey() - value.getKey()) !=
                        Math.abs(Bishop.this.index.getValue() - value.getValue())) {
                    return false;
                }

                //abba az irányban szabad e az út
                if (Bishop.this.index.getKey() > value.getKey() &&
                        Bishop.this.index.getValue() > value.getValue()) {
                    help = this.moveSame(value, Bishop.this.index);
                } else if (Bishop.this.index.getKey() < value.getKey() &&
                        Bishop.this.index.getValue() > value.getValue()) {
                    help = this.moveDifferent(Bishop.this.index, value);
                } else if (Bishop.this.index.getKey() > value.getKey() &&
                        Bishop.this.index.getValue() < value.getValue()) {
                    help = this.moveDifferent(value, Bishop.this.index);
                } else if (Bishop.this.index.getKey() < value.getKey() &&
                        Bishop.this.index.getValue() < value.getValue()) {
                    help = this.moveSame(Bishop.this.index, value);
                }

                //ha szabad az út akkor le kell ütni vagy sem
                if (help) {
                    if (ChessBoard.getInstance().getFieldValue(value) != null && ChessBoard.getInstance().getFieldValue(value) instanceof King) {
                        if (ChessBoard.getInstance().getFieldValue(value).getColor() != Bishop.this.color) {
                            if (Bishop.this.color == Color.WHITE) {
                                ChessBoard.getInstance().getDeadBlackPiece().add(ChessBoard.getInstance().getFieldValue(value));
                            } else {
                                ChessBoard.getInstance().getDeadWhitePiece().add(ChessBoard.getInstance().getFieldValue(value));
                            }
                        }
                        else {
                            return false;
                        }
                    }
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
}

