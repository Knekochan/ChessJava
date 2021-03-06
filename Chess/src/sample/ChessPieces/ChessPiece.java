package sample.ChessPieces;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Pair;

public abstract class ChessPiece {
    protected MoveStrategy moveStrategy;

    private Image image;

    protected ChessPiece(String path) {
        this.image = new Image(path);
    }

    public void draw(GraphicsContext gc, int x, int y) {
        // a 0-ák helyére a bábu pozíciója kerül
        gc.drawImage(this.image, x, y);
    }

    //az index jelz, hogy hol vannak éppen a bábuk a pályán
    //Pair<Oszlop,Sor>
    public abstract Pair<Integer,Integer> getIndex();
    public abstract void setIndex(Pair<Integer,Integer> i);
    //fekete vagy fehér
    public abstract Color getColor();
    public abstract int getType();
    //a move-ban lesz megvalósítva egy lépési stratégia
    public boolean CanMoveTo(Pair<Integer,Integer> value) {
        return this.moveStrategy.CanMoveTo(value);
    }
    public void Change(ChessPiece value) {};
}
