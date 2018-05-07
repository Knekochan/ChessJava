package sample.ChessPieces;

import javafx.util.Pair;
import sample.ChessBoard;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;

//gyalog
public class Pawn extends ChessPiece{
    //Pair<oszlop,sor>
    private Pair<Integer,Integer> index;
    private Color color;
    private final int type = 1;

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
    public void Move(Pair<Integer, Integer> i) {
        if (Math.abs(this.index.getValue() - i.getValue()) <= 2 &&
                ChessBoard.getFieldValue(i) == 0) {
            this.index = i;
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
                        help = this.index;
                        this.index = i;
                        this.hitHelp(help);
            }
        }
    }

    @Override
    public void Change(int i, ChessPiece chessPiece) {

    }
}
