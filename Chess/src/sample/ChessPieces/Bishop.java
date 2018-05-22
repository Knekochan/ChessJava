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

    public Bishop(Color color, Pair<Integer,Integer> index) {
        super(color == Color.WHITE ? new File("C:\\Users\\User\\Documents\\IntelliJ IDEA\\Chess\\src\\sample\\PieceImages\\BishopWhite.png").toURI().toString() :
                new File("C:\\Users\\User\\Documents\\IntelliJ IDEA\\Chess\\src\\sample\\PieceImages\\BishopBlack.png").toURI().toString());
        this.color = color;
        this.index = index;
        this.moveStrategy = new MoveStrategy() {
            private boolean moveRightDown(Pair<Integer, Integer> from, Pair<Integer, Integer> to) {
                int i = from.getKey() + 1;
                int j = from.getValue() + 1;
                while (i < to.getKey() && j < to.getValue()) {
                    if (ChessBoard.getInstance().getFieldValue(new Pair<>(i, j)) != null) {
                        return false;
                    }
                    i++;
                    j++;
                }

                if (ChessBoard.getInstance().getFieldValue(to) != null && ChessBoard.getInstance().getFieldValue(to).getColor() == Bishop.this.color) {
                    return false;
                }

                return true;
            }

            private boolean moveLeftUp(Pair<Integer, Integer> from, Pair<Integer, Integer> to) {
                int i = from.getKey() + 1;
                int j = from.getValue() + 1;
                while (i < to.getKey() && j < to.getValue()) {
                    if (ChessBoard.getInstance().getFieldValue(new Pair<>(i, j)) != null) {
                        return false;
                    }
                    i++;
                    j++;
                }

                if (ChessBoard.getInstance().getFieldValue(from) != null && ChessBoard.getInstance().getFieldValue(from).getColor() == Bishop.this.color) {
                    return false;
                }

                return true;
            }

            private boolean moveRightUp(Pair<Integer, Integer> from, Pair<Integer, Integer> to) {
                int i = from.getKey() + 1;
                int j = from.getValue() - 1;
                while (i < to.getKey() && j > to.getValue()) {
                    if (ChessBoard.getInstance().getFieldValue(new Pair<>(i, j)) != null) {
                        return false;
                    }
                    i++;
                    j--;
                }

                if (ChessBoard.getInstance().getFieldValue(to) != null && ChessBoard.getInstance().getFieldValue(to).getColor() == Bishop.this.color) {
                    return false;
                }

                return true;
            }

            private boolean moveLeftDown(Pair<Integer, Integer> from, Pair<Integer, Integer> to) {
                int i = from.getKey () + 1;
                int j = from.getValue() - 1;
                while (i < to.getKey() && j > to.getValue()) {
                    if (ChessBoard.getInstance().getFieldValue(new Pair<>(i, j)) != null) {
                        return false;
                    }
                    i++;
                    j--;
                }

                if (ChessBoard.getInstance().getFieldValue(from) != null && ChessBoard.getInstance().getFieldValue(from).getColor() == Bishop.this.color) {
                    return false;
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
                    help = this.moveLeftUp(value, Bishop.this.index);
                } else if (Bishop.this.index.getKey() < value.getKey() &&
                        Bishop.this.index.getValue() > value.getValue()) {
                    help = this.moveRightUp(Bishop.this.index, value);
                } else if (Bishop.this.index.getKey() > value.getKey() &&
                        Bishop.this.index.getValue() < value.getValue()) {
                    help = this.moveLeftDown(value, Bishop.this.index);
                } else if (Bishop.this.index.getKey() < value.getKey() &&
                        Bishop.this.index.getValue() < value.getValue()) {
                    help = this.moveRightDown(Bishop.this.index, value);
                }

                //ha szabad az út akkor le kell ütni vagy sem
                if (help) {
                    if (ChessBoard.getInstance().getFieldValue(value) != null) {
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

    @Override
    public int getType() {
        return this.type;
    }
}

