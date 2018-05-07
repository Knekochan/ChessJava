package sample.ChessPieces;

import javafx.util.Pair;

public abstract class ChessPiece {
    //az index jelz, hogy hol vannak éppen a bábuk a pályán
    //Pair<Oszlop,Sor>
    public abstract Pair<Integer,Integer> getIndex();
    public abstract void setIndex(Pair<Integer,Integer> i);
    //fekete vagy fehér
    public abstract Color getColor();
    //a move-ban lesz megvalósítva egy lépési stratégia
    public abstract void Move(Pair<Integer,Integer> i);
    public abstract void Hit(Pair<Integer,Integer> i);
    public abstract void Change(int i, ChessPiece chessPiece);
}
