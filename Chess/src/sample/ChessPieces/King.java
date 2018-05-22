package sample.ChessPieces;

import javafx.util.Pair;
import sample.ChessBoard;

import java.io.File;

//király
public class King extends ChessPiece {
    //Pair<oszlop,sor>
    private Pair<Integer, Integer> index;
    private Color color;
    private final int type = 6;

    public King(Color color, Pair<Integer,Integer> index) {
        super(color == Color.WHITE ? new File("C:\\Users\\User\\Documents\\IntelliJ IDEA\\Chess\\src\\sample\\PieceImages\\KingWhite.png").toURI().toString() :
                new File("C:\\Users\\User\\Documents\\IntelliJ IDEA\\Chess\\src\\sample\\PieceImages\\KingBlack.png").toURI().toString());
        this.color = color;
        this.index = index;
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
                boolean help = false;

                // && ChessBoard.getInstance().getFieldValue(value).getType() != 6

                help = this.kingNextTo(value);

                if (help) {
                    //ha ahova lépni akarunk ott bábu van akkor az ütésnek minősül így be kell tenni az ott álló bábut a halott bábuk közé, egyébként meg csak vissza térünk true-val mivel csak át kell rakni az új helyre
                    if (ChessBoard.getInstance().getFieldValue(value) != null) {
                        if (ChessBoard.getInstance().getFieldValue(value).getColor() != King.this.color) {
                            if (King.this.color == Color.WHITE) {
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
