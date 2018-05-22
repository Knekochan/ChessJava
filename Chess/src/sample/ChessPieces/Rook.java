package sample.ChessPieces;

import javafx.scene.image.Image;
import javafx.util.Pair;
import sample.ChessBoard;

import java.io.File;

//bástya
public class Rook extends ChessPiece {
    //Pair<oszlop,sor>
    private Pair<Integer, Integer> index;
    private Color color;
    private final int type = 2;

    public Rook(Color color, Pair<Integer,Integer> index) {
        super(color == Color.WHITE ? new File("C:\\Users\\User\\Documents\\IntelliJ IDEA\\Chess\\src\\sample\\PieceImages\\RookWhite.png").toURI().toString() :
                                     new File("C:\\Users\\User\\Documents\\IntelliJ IDEA\\Chess\\src\\sample\\PieceImages\\RookBlack.png").toURI().toString());
        this.color = color;
        this.index = index;
        this.moveStrategy = new MoveStrategy() {
            @Override
            public boolean CanMoveTo(Pair<Integer, Integer> value) {
                boolean help = true;

                //lépés szám jó e
                if (Math.abs(Rook.this.index.getKey() - value.getKey()) == Math.abs(Rook.this.index.getValue() - value.getValue())) {
                    return false;
                }


                //szabad e az út
                if (Rook.this.index.getKey() == value.getKey()) {
                    if (Rook.this.index.getValue() < value.getValue()) {
                        for (int i = Rook.this.index.getValue() + 1; i < value.getValue(); i++) {
                            if (ChessBoard.getInstance().getFieldValue(new Pair<>(Rook.this.index.getKey(), i)) != null) {
                                help = false;
                            }
                        }
                    } else {
                        for (int i = value.getValue() + 1; i < Rook.this.index.getValue(); i++) {
                            if (ChessBoard.getInstance().getFieldValue(new Pair<>(Rook.this.index.getKey(), i)) != null) {
                                help = false;
                            }
                        }
                    }
                } else if (Rook.this.index.getValue() == value.getValue()) {
                    if (Rook.this.index.getKey() < value.getKey()) {
                        for (int i = Rook.this.index.getKey() + 1; i < value.getKey(); i++) {
                            if (ChessBoard.getInstance().getFieldValue(new Pair<>(i, Rook.this.index.getValue())) != null) {
                                help = false;
                            }
                        }
                    } else {
                        for (int i = value.getKey() + 1; i < Rook.this.index.getKey(); i++) {
                            if (ChessBoard.getInstance().getFieldValue(new Pair<>(i, Rook.this.index.getValue())) != null) {
                                help = false;
                            }
                        }
                    }
                }

                //ha ütés akkor mi lesz
                if (help) {
                    //ha ahova lépni akarunk ott bábu van akkor az ütésnek minősül így be kell tenni az ott álló bábut a halott bábuk közé, egyébként meg csak vissza térünk true-val mivel csak át kell rakni az új helyre
                    // && ChessBoard.getInstance().getFieldValue(value) instanceof King
                    if (ChessBoard.getInstance().getFieldValue(value) != null) {
                        if (ChessBoard.getInstance().getFieldValue(value).getColor() != Rook.this.color) {
                            if (Rook.this.color == Color.WHITE) {
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

    @Override
    public void Change(ChessPiece value) {
        super.Change(value);
    }
}
