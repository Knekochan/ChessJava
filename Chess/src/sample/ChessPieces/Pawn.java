package sample.ChessPieces;

import javafx.util.Pair;
import sample.ChessBoard;

//gyalog
public class Pawn extends ChessPiece{
    //Pair<oszlop,sor>
    private Pair<Integer,Integer> index;
    private Color color;
    private final int type = 1;

    public Pawn() {
        super(new MoveStrategy() {
            @Override
            public void Move(Pair<Integer, Integer> value, Direction direction) {
                // kidolgozás
                //Pawn.this.index
            }
        });

    }

    @Override
    public Pair<Integer,Integer> getIndex() {
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
    public void Move(Pair<Integer, Integer> value, Direction direction) {
        direction = Direction.AHEAD;
        Pair<Integer,Integer> help;
        if (Math.abs(this.index.getValue() - value.getValue()) <= 2 &&
                ChessBoard.getFieldValue(value) == 0) {
            help = this.index;
            this.index = value;
            ChessBoard.changeFieldValue(help,0);
            ChessBoard.changeFieldValue(this.index,this.type);
        }
    }

    private void hitHelp(Pair<Integer,Integer> help){
        ChessBoard.changeFieldValue(help,0);
        ChessBoard.changeFieldValue(this.index,this.type);
    }

    //azt a helyet kéri be ahol leakarja ütni a bábut
    @Override
    public void Hit(Pair<Integer,Integer> i) {
        Pair<Integer,Integer> help;
        //fehér paraszt
        if (this.color == Color.WHITE) {
            if (((ChessBoard.getFieldValue(i) != 6) && (i.getValue() == this.index.getValue() - 1) && (i.getValue() != 0)) &&         //királyt nem ütjük le és mindenképp a paraszt elötti sorban kell lennie és az utolsó sorban már nem üthet
                  (((this.index.getKey() == 0) && (i.getKey() == this.index.getKey() + 1)) ||                  //ha a paraszt a 0. oszlopban van
                   ((this.index.getKey() == 7) && (i.getKey() == this.index.getKey() - 1)) ||                  //ha a paraszt a 7.oszlopban van
                   ((i.getKey() == this.index.getKey() + 1) || (i.getKey() == this.index.getKey() - 1)))) {    //többi

                        //megnézi milyen bábu-t akarunk leütni és azt beteszi a halott bábuk közé, és mivel fehérrel ütünk ezért a feketék közé
                        if (ChessBoard.findPieceIndex(i) != -1) {
                            ChessBoard.setDeadBlackPiece(ChessBoard.findPieceIndex(i));
                        }

                        help = this.index;
                        this.index = i;
                        this.hitHelp(help);
            }
        }
        //fekete paraszt
        else {
            if (((ChessBoard.getFieldValue(i) != 6) && (i.getValue() == this.index.getValue() + 1) && (i.getValue() != 7)) &&         //királyt nem ütjük le és mindenképp a paraszt elötti sorban kell lennie és az utolsó sorban már nem üthet
                 (((this.index.getKey() == 0) && (i.getKey() == this.index.getKey() + 1)) ||                   //ha a paraszt a 0. oszlopban van
                  ((this.index.getKey() == 7) && (i.getKey() == this.index.getKey() - 1)) ||                   //ha a paraszt a 7.oszlopban van
                  ((i.getKey() == this.index.getKey() + 1) || (i.getKey() == this.index.getKey() - 1)))) {     //többi

                        //megnézi milyen bábu-t akarunk leütni és azt beteszi a halott bábuk közé, és mivel feketével ütünk ezért a fehérek közé
                        if (ChessBoard.findPieceIndex(i) != -1) {
                            ChessBoard.setDeadWhitePiece(ChessBoard.findPieceIndex(i));
                        }

                        help = this.index;
                        this.index = i;
                        this.hitHelp(help);
            }
        }
    }

    @Override
    public void Change(int piece) {
        Pair<Integer,Integer> help;
        //a nulladik sorban vagyunk és fehér paraszt
        if (this.index.getValue() == 0) {
            for (int j = 0; j < ChessBoard.getDeadWhitePiece().size(); j++) {
                if (ChessBoard.getDeadWhitePiece().get(j) == piece)
                {
                    ChessBoard.changeFieldValue(this.index,piece);
                    ChessBoard.removeDeadPiece(Color.WHITE, piece);
                }
            }
        }
        //a hetedik sorban vagyunk és fekete paraszt
        else if (this.index.getValue() == 7) {
            for (int j = 0; j < ChessBoard.getDeadBlackPiece().size(); j++) {
                if (ChessBoard.getDeadBlackPiece().get(j) == piece)
                {
                    ChessBoard.changeFieldValue(this.index,piece);
                    ChessBoard.removeDeadPiece(Color.BLACK, piece);
                }
            }
        }
    }
}
