package sample.ChessPieces;

import javafx.util.Pair;
import sample.ChessBoard;

//bástya
public class Rook extends ChessPiece{
    //Pair<oszlop,sor>
    private Pair<Integer,Integer> index;
    private Color color;
    private final int type = 2;

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
    public void Move(Pair<Integer, Integer> value, Direction direction) {
        int distance;
        int i = 1;
        Pair<Integer, Integer> help;
        //mert ha olyan helyre kattint ahol bábuvan akkor az már nem lépésnek számít hanem ütésnek
        if (ChessBoard.getFieldValue(value) == 0) {
            if (this.color == Color.WHITE) {
                switch (direction) {
                    //előre megyünk
                    case AHEAD:
                        if (this.index.getValue() != 0) {
                            distance = Math.abs(value.getKey() - this.index.getKey());
                            while (i <= distance && ChessBoard.getFieldValue(new Pair<Integer, Integer>(this.index.getKey(), this.index.getValue() + i)) == 0) {
                                i++;
                            }
                            if (i > distance) {
                                help = this.index;
                                this.index = value;
                                ChessBoard.changeFieldValue(help, 0);
                                ChessBoard.changeFieldValue(this.index, this.type);
                            }
                        }
                        break;
                    //hátra megyünk
                    case BACK:
                        distance = Math.abs(value.getKey() - this.index.getKey());
                        while (i <= distance && ChessBoard.getFieldValue(new Pair<Integer, Integer>(this.index.getKey(), this.index.getValue() - i)) == 0) {
                            i++;
                        }
                        if (i > distance) {
                            help = this.index;
                            this.index = value;
                            ChessBoard.changeFieldValue(help, 0);
                            ChessBoard.changeFieldValue(this.index, this.type);
                        }
                        break;
                    //balra
                    case LEFT:
                        distance = Math.abs(value.getKey() - this.index.getKey());
                        while (i <= distance && ChessBoard.getFieldValue(new Pair<Integer, Integer>(this.index.getKey(), this.index.getValue() + i)) == 0) {
                            i++;
                        }
                        if (i > distance) {
                            help = this.index;
                            this.index = value;
                            ChessBoard.changeFieldValue(help, 0);
                            ChessBoard.changeFieldValue(this.index, this.type);
                        }
                        break;
                    //jobbra
                    case RIGHT:
                        break;
                    default:
                        break;
                }
            }
            else {

            }
        }
        else {
            this.Hit(value);
        }
    }


    @Override
    public void Hit(Pair<Integer, Integer> i) {

    }

    @Override
    public void Change(int value) {

    }
}
