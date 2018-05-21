package sample.ChessPieces;

import javafx.util.Pair;
import sample.ChessBoard;

import java.io.File;

//gyalog
public class Pawn extends ChessPiece {
    //Pair<oszlop,sor>
    private Pair<Integer, Integer> index;
    private Color color;
    private final int type = 1;

    public Pawn(Color color) {
        // paraméterben a kép elérését kell megadni
        super(color == Color.WHITE ? new File("C:\\Users\\User\\Documents\\IntelliJ IDEA\\Chess\\src\\sample\\PieceImages\\PawnWhite.png").toURI().toString() :
                new File("C:\\Users\\User\\Documents\\IntelliJ IDEA\\Chess\\src\\sample\\PieceImages\\PawnBlack.png").toURI().toString());
        this.color = color;
        this.moveStrategy = new MoveStrategy() {

            private boolean pawnHit(Pair<Integer, Integer> value) {
                //fehér paraszt
                if (ChessBoard.getInstance().getFieldValue(value).getColor() != Pawn.this.color) {
                    if (Pawn.this.color == Color.WHITE) {
                        if (((ChessBoard.getInstance().getFieldValue(value) instanceof King) && (value.getValue() == Pawn.this.index.getValue() - 1) && (value.getValue() != 0)) &&         //királyt nem ütjük le és mindenképp a paraszt elötti sorban kell lennie és az utolsó sorban már nem üthet
                                (((Pawn.this.index.getKey() == 0) && (value.getKey() == Pawn.this.index.getKey() + 1)) ||                  //ha a paraszt a 0. oszlopban van
                                        ((Pawn.this.index.getKey() == 7) && (value.getKey() == Pawn.this.index.getKey() - 1)) ||                  //ha a paraszt a 7.oszlopban van
                                        ((value.getKey() == Pawn.this.index.getKey() + 1) || (value.getKey() == Pawn.this.index.getKey() - 1)))) {    //többi

                            //megnézi milyen bábu-t akarunk leütni és azt beteszi a halott bábuk közé, és mivel fehérrel ütünk ezért a feketék közé
                            if (ChessBoard.getInstance().getFieldValue(value) != null) {
                                ChessBoard.getInstance().setDeadBlackPiece(ChessBoard.getInstance().getFieldValue(value));
                                return true;
                            }
                        }
                    }
                    //fekete paraszt
                    else {
                        if (((ChessBoard.getInstance().getFieldValue(value) instanceof King) && (value.getValue() == Pawn.this.index.getValue() + 1) && (value.getValue() != 7)) &&         //királyt nem ütjük le és mindenképp a paraszt elötti sorban kell lennie és az utolsó sorban már nem üthet
                                (((Pawn.this.index.getKey() == 0) && (value.getKey() == Pawn.this.index.getKey() + 1)) ||                   //ha a paraszt a 0. oszlopban van
                                        ((Pawn.this.index.getKey() == 7) && (value.getKey() == Pawn.this.index.getKey() - 1)) ||                   //ha a paraszt a 7.oszlopban van
                                        ((value.getKey() == Pawn.this.index.getKey() + 1) || (value.getKey() == Pawn.this.index.getKey() - 1)))) {     //többi

                            //megnézi milyen bábu-t akarunk leütni és azt beteszi a halott bábuk közé, és mivel feketével ütünk ezért a fehérek közé
                            if (ChessBoard.getInstance().getFieldValue(value) != null) {
                                ChessBoard.getInstance().setDeadWhitePiece(ChessBoard.getInstance().getFieldValue(value));
                                return true;
                            }
                        }
                    }
                }
                return false;
            }

            @Override
            public boolean CanMoveTo(Pair<Integer, Integer> value) {
                //lépés vagy ütés
                if (ChessBoard.getInstance().getFieldValue(value) == null) {
                    if (Pawn.this.color == Color.WHITE &&
                            Pawn.this.index.getValue() > value.getValue() &&
                            ((Pawn.this.index.getValue() == 6 && Math.abs(Pawn.this.index.getValue() - value.getValue()) <= 2) ||
                                    Math.abs(Pawn.this.index.getValue() - value.getValue()) <= 1)) {
                        return true;
                    } else if (Pawn.this.color == Color.BLACK &&
                            Pawn.this.index.getValue() < value.getValue() &&
                            ((Pawn.this.index.getValue() == 1 && Math.abs(Pawn.this.index.getValue() - value.getValue()) <= 2) ||
                                    Math.abs(Pawn.this.index.getValue() - value.getValue()) <= 1)) {
                        return true;
                    }
                } else {
                    return this.pawnHit(value);
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
    public void Change(ChessPiece piece) {
        Pair<Integer, Integer> help;
        //a nulladik sorban vagyunk és fehér paraszt
        if (this.index.getValue() == 0) {
            for (int j = 0; j < ChessBoard.getInstance().getDeadWhitePiece().size(); j++) {

                if (ChessBoard.getInstance().getDeadWhitePiece().get(j) == piece) {
                    ChessBoard.getInstance().changeFieldValue(this.index, piece);
                    ChessBoard.getInstance().removeDeadPiece(Color.WHITE, piece);
                }

            }
        }
        //a hetedik sorban vagyunk és fekete paraszt
        else if (this.index.getValue() == 7) {
            for (int j = 0; j < ChessBoard.getInstance().getDeadBlackPiece().size(); j++) {

                if (ChessBoard.getInstance().getDeadBlackPiece().get(j) == piece) {
                    ChessBoard.getInstance().changeFieldValue(this.index, piece);
                    ChessBoard.getInstance().removeDeadPiece(Color.BLACK, piece);
                }

            }
        }
    }
}
