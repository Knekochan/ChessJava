package sample.ChessPieces;

import javafx.util.Pair;
import sample.ChessBoard;

import java.io.File;

//királynő
public class Queen extends ChessPiece {
    //Pair<oszlop,sor>
    private Pair<Integer, Integer> index;
    private Color color;
    private final int type = 5;

    public Queen(Color color) {
        super(color == Color.WHITE ? new File("C:\\Users\\User\\Documents\\IntelliJ IDEA\\Chess\\src\\sample\\PieceImages\\QueenWhite.png").toURI().toString() :
                new File("C:\\Users\\User\\Documents\\IntelliJ IDEA\\Chess\\src\\sample\\PieceImages\\QueenBlack.png").toURI().toString());
        this.color = color;
        this.moveStrategy = new MoveStrategy() {
            private boolean queenDiagonalDifferent(Pair<Integer, Integer> from, Pair<Integer, Integer> to) {
                for (int i = from.getKey(); i < to.getKey(); i++) {
                    for (int j = to.getValue(); i < from.getValue(); j++) {
                        if (ChessBoard.getInstance().getFieldValue(new Pair<>(i, j)) != null) {
                            return false;
                        }
                    }
                }
                return true;
            }

            private boolean queenDiagonalSame(Pair<Integer, Integer> from, Pair<Integer, Integer> to) {
                for (int i = from.getKey(); i < to.getKey(); i++) {
                    for (int j = from.getValue(); i < to.getValue(); j++) {
                        if (ChessBoard.getInstance().getFieldValue(new Pair<>(i, j)) != null) {
                            return false;
                        }
                    }
                }
                return true;
            }

            private boolean queenLineKey(Pair<Integer, Integer> from, Pair<Integer, Integer> to) {
                for (int i = from.getKey(); i < to.getKey(); i++) {
                    if (ChessBoard.getInstance().getFieldValue(new Pair<>(i, from.getValue())) != null) {
                        return false;
                    }
                }
                return true;
            }

            private boolean queenLineValue(Pair<Integer, Integer> from, Pair<Integer, Integer> to) {
                for (int i = from.getValue(); i < to.getValue(); i++) {
                    if (ChessBoard.getInstance().getFieldValue(new Pair<>(i, from.getKey())) != null) {
                        return false;
                    }
                }
                return true;
            }

            @Override
            public boolean CanMoveTo(Pair<Integer, Integer> value) {
                boolean help = false; // segéd változó

                //megnézi jó irányba lép e vagy sem
                if (((Math.abs(Queen.this.index.getKey() - value.getKey()) != Math.abs(Queen.this.index.getValue() - value.getValue())) ||
                        Queen.this.index.getKey() != value.getKey() || Queen.this.index.getValue() != value.getKey())) {
                    return false;
                }

                //abba az irányban szabad e az út
                if (Queen.this.index.getKey() < value.getKey() && Queen.this.index.getValue() > value.getValue()) {
                    help = this.queenDiagonalDifferent(Queen.this.index, value);
                } else if (Queen.this.index.getKey() < value.getKey() && Queen.this.index.getValue() < value.getValue()) {
                    help = this.queenDiagonalSame(Queen.this.index, value);
                } else if (Queen.this.index.getKey() > value.getKey() && Queen.this.index.getValue() < value.getValue()) {
                    help = this.queenDiagonalDifferent(value, Queen.this.index);
                } else if (Queen.this.index.getKey() > value.getKey() && Queen.this.index.getValue() > value.getValue()) {
                    help = this.queenDiagonalSame(value, Queen.this.index);
                } else if (Queen.this.index.getKey() < value.getKey() && Queen.this.index.getValue() == value.getValue()) {
                    help = this.queenLineKey(Queen.this.index, value);
                } else if (Queen.this.index.getKey() == value.getKey() && Queen.this.index.getValue() < value.getValue()) {
                    help = this.queenLineValue(Queen.this.index, value);
                } else if (Queen.this.index.getKey() > value.getKey() && Queen.this.index.getValue() == value.getValue()) {
                    help = this.queenLineKey(value, Queen.this.index);
                } else if (Queen.this.index.getKey() == value.getKey() && Queen.this.index.getValue() > value.getValue()) {
                    help = this.queenLineValue(value, Queen.this.index);
                }

                //ha szabad az út akkor le kell ütni vagy sem
                if (help) {
                    //ha ahova lépni akarunk ott bábu van akkor az ütésnek minősül így be kell tenni az ott álló bábut a halott bábuk közé, egyébként meg csak vissza térünk true-val mivel csak át kell rakni az új helyre
                    if (ChessBoard.getInstance().getFieldValue(value) != null && ChessBoard.getInstance().getFieldValue(value) instanceof King) {
                        if (ChessBoard.getInstance().getFieldValue(value).getColor() != Queen.this.color) {
                            if (Queen.this.color == Color.WHITE) {
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
