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

    public Queen(Color color, Pair<Integer,Integer> index) {
        super(color == Color.WHITE ? new File("C:\\Users\\User\\Documents\\IntelliJ IDEA\\Chess\\src\\sample\\PieceImages\\QueenWhite.png").toURI().toString() :
                new File("C:\\Users\\User\\Documents\\IntelliJ IDEA\\Chess\\src\\sample\\PieceImages\\QueenBlack.png").toURI().toString());
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

                if (ChessBoard.getInstance().getFieldValue(to) != null && ChessBoard.getInstance().getFieldValue(to).getColor() == Queen.this.color) {
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

                if (ChessBoard.getInstance().getFieldValue(from) != null && ChessBoard.getInstance().getFieldValue(from).getColor() == Queen.this.color) {
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

                if (ChessBoard.getInstance().getFieldValue(to) != null && ChessBoard.getInstance().getFieldValue(to).getColor() == Queen.this.color) {
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

                if (ChessBoard.getInstance().getFieldValue(from) != null && ChessBoard.getInstance().getFieldValue(from).getColor() == Queen.this.color) {
                    return false;
                }

                return true;
            }

            private boolean moveLine(Pair<Integer, Integer> from, Pair<Integer, Integer> to) {
                if (from.getKey() == to.getKey()) {
                    if (from.getValue() < to.getValue()) {
                        for (int i = from.getValue() + 1; i < to.getValue(); i++) {
                            if (ChessBoard.getInstance().getFieldValue(new Pair<>(from.getKey(), i)) != null) {
                                return false;
                            }
                        }
                    } else {
                        for (int i = to.getValue() + 1; i < from.getValue(); i++) {
                            if (ChessBoard.getInstance().getFieldValue(new Pair<>(from.getKey(), i)) != null) {
                                return false;
                            }
                        }
                    }
                } else if (from.getValue() == to.getValue()) {
                    if (from.getKey() < to.getKey()) {
                        for (int i = from.getKey() + 1; i < to.getKey(); i++) {
                            if (ChessBoard.getInstance().getFieldValue(new Pair<>(i, from.getValue())) != null) {
                                return false;
                            }
                        }
                    } else {
                        for (int i = to.getKey() + 1; i < from.getKey(); i++) {
                            if (ChessBoard.getInstance().getFieldValue(new Pair<>(i, from.getValue())) != null) {
                                return false;
                            }
                        }
                    }
                }
                return true;

            }

            @Override
            public boolean CanMoveTo(Pair<Integer, Integer> value) {
                boolean help = false; // segéd változó

                //megnézi jó irányba lép e vagy sem
                /*
                if (((Math.abs(Queen.this.index.getKey() - value.getKey()) != Math.abs(Queen.this.index.getValue() - value.getValue())) ||
                        Queen.this.index.getKey() != value.getKey() || Queen.this.index.getValue() != value.getKey())) {
                    return false;
                }
                */

                //abba az irányban szabad e az út
                //jobb felső
                if (Queen.this.index.getKey() < value.getKey() && Queen.this.index.getValue() > value.getValue()) {
                    help = this.moveRightUp(Queen.this.index, value);
                }
                //jobb also
                else if (Queen.this.index.getKey() < value.getKey() && Queen.this.index.getValue() < value.getValue()) {
                    help = this.moveRightDown(Queen.this.index, value);
                }
                //bal alsó
                else if (Queen.this.index.getKey() > value.getKey() && Queen.this.index.getValue() < value.getValue()) {
                    help = this.moveLeftDown(value, Queen.this.index);
                }
                //bal felső
                else if (Queen.this.index.getKey() > value.getKey() && Queen.this.index.getValue() > value.getValue()) {
                    help = this.moveLeftUp(value, Queen.this.index);
                }
                //jobbra egy vonalon
                else if (Queen.this.index.getKey() < value.getKey() && Queen.this.index.getValue() == value.getValue()) {
                    help = this.moveLine(Queen.this.index, value);
                }
                //fölfelé
                else if (Queen.this.index.getKey() == value.getKey() && Queen.this.index.getValue() < value.getValue()) {
                    help = this.moveLine(Queen.this.index, value);
                }
                //balra
                else if (Queen.this.index.getKey() > value.getKey() && Queen.this.index.getValue() == value.getValue()) {
                    help = this.moveLine(value, Queen.this.index);
                }
                //lefelé
                else if (Queen.this.index.getKey() == value.getKey() && Queen.this.index.getValue() > value.getValue()) {
                    help = this.moveLine(value, Queen.this.index);
                }

                //ha szabad az út akkor le kell ütni vagy sem
                if (help) {
                    //ha ahova lépni akarunk ott bábu van akkor az ütésnek minősül így be kell tenni az ott álló bábut a halott bábuk közé, egyébként meg csak vissza térünk true-val mivel csak át kell rakni az új helyre
                    if (ChessBoard.getInstance().getFieldValue(value) != null) {
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

    @Override
    public int getType() {
        return this.type;
    }
}
