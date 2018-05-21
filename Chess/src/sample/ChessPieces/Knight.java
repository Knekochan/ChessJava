package sample.ChessPieces;

import javafx.util.Pair;
import sample.ChessBoard;

import java.io.File;

//huszár
public class Knight extends ChessPiece {
    //Pair<oszlop,sor>
    private Pair<Integer, Integer> index;
    private Color color;
    private final int type = 3;

    public Knight(Color color) {
        super(color == Color.WHITE ? new File("C:\\Users\\User\\Documents\\IntelliJ IDEA\\Chess\\src\\sample\\PieceImages\\KnightWhite.png").toURI().toString() :
                new File("C:\\Users\\User\\Documents\\IntelliJ IDEA\\Chess\\src\\sample\\PieceImages\\KnightBlack.png").toURI().toString());
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
                boolean help = false;

                if (this.knightJump(value)) {
                    help = true;
                }

                if (help) {
                    //ha ahova lépni akarunk ott bábu van akkor az ütésnek minősül így be kell tenni az ott álló bábut a halott bábuk közé, egyébként meg csak vissza térünk true-val mivel csak át kell rakni az új helyre
                    if (ChessBoard.getInstance().getFieldValue(value) != null && ChessBoard.getInstance().getFieldValue(value) instanceof King) {
                        if (ChessBoard.getInstance().getFieldValue(value).getColor() != Knight.this.color) {
                            if (Knight.this.color == Color.WHITE) {
                                ChessBoard.getInstance().getDeadBlackPiece().add(ChessBoard.getInstance().getFieldValue(value));
                            } else {
                                ChessBoard.getInstance().getDeadWhitePiece().add(ChessBoard.getInstance().getFieldValue(value));
                            }
                        } else {
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
