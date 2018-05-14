package sample.ChessPieces;

import javafx.util.Pair;
import sample.ChessBoard;

//gyalog
public class Pawn extends ChessPiece {
    //Pair<oszlop,sor>
    private Pair<Integer, Integer> index;
    private Color color;
    private final int type = 1;

    public Pawn(Color color) {
        // paraméterben a kép elérését kell megadni
        super(color == Color.WHITE ? "fehér_kép" : "fekete_kép");
        this.color = color;
        this.moveStrategy = new MoveStrategy() {
            @Override
            public boolean CanMoveTo(Pair<Integer, Integer> value) {
                Pair<Integer, Integer> help;
                if (Pawn.this.color == Color.WHITE &&
                        Pawn.this.index.getValue() > value.getValue() &&
                        ChessBoard.getInstance().getFieldValue(value) == null &&
                        ((Pawn.this.index.getValue() == 6 && Math.abs(Pawn.this.index.getValue() - value.getValue()) <= 2) ||
                          Math.abs(Pawn.this.index.getValue() - value.getValue()) <= 1)) {
                    return true;
                }
                else if (Pawn.this.color == Color.BLACK &&
                        Pawn.this.index.getValue() < value.getValue() &&
                        ChessBoard.getInstance().getFieldValue(value) == null &&
                        ((Pawn.this.index.getValue() == 1 && Math.abs(Pawn.this.index.getValue() - value.getValue()) <= 2) ||
                                Math.abs(Pawn.this.index.getValue() - value.getValue()) <= 1)){
                    return true;
                }

                return  false;
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

    private void hitHelp(Pair<Integer, Integer> help) {
        ChessBoard.getInstance().changeFieldValue(help, null);
        ChessBoard.getInstance().changeFieldValue(this.index, this);
    }

    //azt a helyet kéri be ahol leakarja ütni a bábut
    @Override
    public void Hit(Pair<Integer, Integer> i) {
        Pair<Integer, Integer> help;
        //fehér paraszt
        if (this.color == Color.WHITE) {
            if (((ChessBoard.getInstance().getFieldValue(i) instanceof King) && (i.getValue() == this.index.getValue() - 1) && (i.getValue() != 0)) &&         //királyt nem ütjük le és mindenképp a paraszt elötti sorban kell lennie és az utolsó sorban már nem üthet
                    (((this.index.getKey() == 0) && (i.getKey() == this.index.getKey() + 1)) ||                  //ha a paraszt a 0. oszlopban van
                            ((this.index.getKey() == 7) && (i.getKey() == this.index.getKey() - 1)) ||                  //ha a paraszt a 7.oszlopban van
                            ((i.getKey() == this.index.getKey() + 1) || (i.getKey() == this.index.getKey() - 1)))) {    //többi

                //megnézi milyen bábu-t akarunk leütni és azt beteszi a halott bábuk közé, és mivel fehérrel ütünk ezért a feketék közé
                if (ChessBoard.getInstance().findPieceIndex(i) != null) {
                    ChessBoard.getInstance().setDeadBlackPiece(ChessBoard.getInstance().findPieceIndex(i));
                    help = this.index;
                    this.index = i;
                    this.hitHelp(help);
                }
            }
        }
        //fekete paraszt
        else {
            if (((ChessBoard.getInstance().getFieldValue(i) instanceof King) && (i.getValue() == this.index.getValue() + 1) && (i.getValue() != 7)) &&         //királyt nem ütjük le és mindenképp a paraszt elötti sorban kell lennie és az utolsó sorban már nem üthet
                    (((this.index.getKey() == 0) && (i.getKey() == this.index.getKey() + 1)) ||                   //ha a paraszt a 0. oszlopban van
                            ((this.index.getKey() == 7) && (i.getKey() == this.index.getKey() - 1)) ||                   //ha a paraszt a 7.oszlopban van
                            ((i.getKey() == this.index.getKey() + 1) || (i.getKey() == this.index.getKey() - 1)))) {     //többi

                //megnézi milyen bábu-t akarunk leütni és azt beteszi a halott bábuk közé, és mivel feketével ütünk ezért a fehérek közé
                if (ChessBoard.getInstance().findPieceIndex(i) != null) {
                    ChessBoard.getInstance().setDeadWhitePiece(ChessBoard.getInstance().findPieceIndex(i));
                    help = this.index;
                    this.index = i;
                    this.hitHelp(help);
                }
            }
        }
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
