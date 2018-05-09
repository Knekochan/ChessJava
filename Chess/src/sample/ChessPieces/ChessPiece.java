package sample.ChessPieces;

import javafx.util.Pair;

public abstract class ChessPiece {
    protected MoveStrategy moveStrategy;

    protected ChessPiece(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    //az index jelz, hogy hol vannak éppen a bábuk a pályán
    //Pair<Oszlop,Sor>
    public abstract Pair<Integer,Integer> getIndex();
    public abstract void setIndex(Pair<Integer,Integer> i);
    //fekete vagy fehér
    public abstract Color getColor();
    //a move-ban lesz megvalósítva egy lépési stratégia
    public void Move(Pair<Integer,Integer> value, Direction direction) {
        this.moveStrategy.Move(value, direction);
    }
    public abstract void Hit(Pair<Integer,Integer> value);
    public abstract void Change(int value);
}
