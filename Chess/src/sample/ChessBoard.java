package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Pair;
import jdk.nashorn.api.tree.WhileLoopTree;
import sample.ChessPieces.*;

import java.io.File;
import java.util.*;

//tábla
public class ChessBoard {
    private static ChessBoard chessBoard = null;

    private Image background;

    private ChessPiece[][] board;
    private ArrayList<ChessPiece> deadWhitePiece;
    private ArrayList<ChessPiece> deadBlackPiece;

    private Color actColor;

    private ChessBoard() {
        this.board = new ChessPiece[8][8];
        this.deadWhitePiece = new ArrayList<ChessPiece>();
        this.deadBlackPiece = new ArrayList<ChessPiece>();

        this.actColor = Color.WHITE;

        File f = new File("C:\\Users\\User\\Documents\\IntelliJ IDEA\\Chess\\src\\sample\\PieceImages\\ChessAll.PNG");
        this.background = new Image(f.toURI().toString());

        //fehér
        this.board[0][0] = new Rook(Color.WHITE, new Pair<>(0,0)); //bástya
        this.board[0][1] = new Knight(Color.WHITE, new Pair<>(0,1)); //huszár
        this.board[0][2] = new Bishop(Color.WHITE, new Pair<>(0,2)); //futó
        this.board[0][3] = new Queen(Color.WHITE, new Pair<>(0,3)); //királynő
        this.board[0][4] = new King(Color.WHITE, new Pair<>(0,4)); //király
        this.board[0][5] = new Bishop(Color.WHITE, new Pair<>(0,5)); //futó
        this.board[0][6] = new Knight(Color.WHITE, new Pair<>(0,6)); //huszár
        this.board[0][7] = new Rook(Color.WHITE,new Pair<>(0,7)); //bástya

        //fehér
        this.board[7][0] = new Rook(Color.BLACK, new Pair<>(7,0)); //bástya
        this.board[7][1] = new Knight(Color.BLACK, new Pair<>(7,1)); //huszár
        this.board[7][2] = new Bishop(Color.BLACK,new Pair<>(7,2)); //futó
        this.board[7][3] = new Queen(Color.BLACK, new Pair<>(7,3)); //királynő
        this.board[7][4] = new King(Color.BLACK,new Pair<>(7,4)); //király
        this.board[7][5] = new Bishop(Color.BLACK,new Pair<>(7,5)); //futó
        this.board[7][6] = new Knight(Color.BLACK, new Pair<>(7,6)); //huszár
        this.board[7][7] = new Rook(Color.BLACK, new Pair<>(7,7)); //bástya

        //parasztok
        for (int i = 0; i < 8; i++) {
            this.board[1][i] = new Pawn(Color.WHITE, new Pair<>(1,i));
            this.board[6][i] = new Pawn(Color.BLACK, new Pair<>(6,i));
        }

        //többi mező
        for (int i = 0; i < 8; i++) {
            this.board[2][i] = null;
            this.board[3][i] = null;
            this.board[4][i] = null;
            this.board[5][i] = null;
        }
    }

    //Instance = példány
    public static ChessBoard getInstance() {
        if (chessBoard == null) {
            chessBoard = new ChessBoard();
        }
        return chessBoard;
    }

    public ChessPiece[][] getBoard() {
        return this.board;
    }

    public ArrayList<ChessPiece> getDeadWhitePiece() {
        return this.deadWhitePiece;
    }

    public void setDeadWhitePiece(ChessPiece value) {
        this.deadWhitePiece.add(value);
    }

    public ArrayList<ChessPiece> getDeadBlackPiece() {
        return this.deadBlackPiece;
    }

    public void setDeadBlackPiece(ChessPiece value) {
        this.deadBlackPiece.add(value);
    }

    //bábut cserél
    //a tábla adott helyén lévő értéket átírja
    public void changeFieldValue(Pair<Integer, Integer> piece, ChessPiece i) {
        board[piece.getKey()][piece.getValue()] = i;
    }

    //bábuk léptetése
    public void movePiece(ChessPiece piece, Pair<Integer, Integer> value) {
        if (piece.getColor() == actColor && piece.CanMoveTo(value)) {
            Pair<Integer, Integer> help;
            help = piece.getIndex();
            piece.setIndex(value);
            this.changeFieldValue(help, null);
            this.changeFieldValue(piece.getIndex(), piece);

            if (actColor == Color.WHITE) {
                actColor = Color.BLACK;
            }
            else {
                actColor = Color.WHITE;
            }
        }
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(this.background, 0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.board[i][j] != null) {
                    //this.board[i][j].draw(gc,this.board[i][j].getIndex().getKey() + (i * 61), this.board[i][j].getIndex().getValue() + (j * 61));
                    this.board[i][j].draw(gc, i * 71, j * 71);
                }
            }
        }
    }

    //a halott bábuk listájából törli a vissza került bábut
    //akkor használjuk amikor az egyik paraszt a pálya tulsó végére ér és lehetőség van a bábu cserére
    public void removeDeadPiece(Color color, ChessPiece value) {
        int i = 0;
        if (color == color.WHITE) {
            while (deadWhitePiece.get(i) != value && i < deadWhitePiece.size()) {
                i++;
            }
            if (i < deadWhitePiece.size()) {
                deadWhitePiece.remove(i);
            }
        } else {
            while (deadBlackPiece.get(i) != value && i < deadBlackPiece.size()) {
                i++;
            }
            if (i < deadBlackPiece.size()) {
                deadBlackPiece.remove(i);
            }
        }

    }

    //vissza adja az adott táblahelyen található értéket
    //vagyis megmondja, hogy a tábla adott helyén milyen bábu van
    public ChessPiece getFieldValue(Pair<Integer, Integer> value) {
        return board[value.getKey()][value.getValue()];
    }

    //sakk bábunak az indexét adja vissza (amire az egérrel rá kattintottunk)
    public Pair<Integer,Integer> getChessPieceAt(int x, int y) {
        int i;
        int j;
         switch (x / 70) {
             case 0: i = 0; break;
             case 1: i = 1; break;
             case 2: i = 2; break;
             case 3: i = 3; break;
             case 4: i = 4; break;
             case 5: i = 5; break;
             case 6: i = 6; break;
             case 7: i = 7; break;
             default: i = 0; break;
         }

        switch (y / 70) {
            case 0: j = 0; break;
            case 1: j = 1; break;
            case 2: j = 2; break;
            case 3: j = 3; break;
            case 4: j = 4; break;
            case 5: j = 5; break;
            case 6: j = 6; break;
            case 7: j = 7; break;
            default: j = 0; break;
        }

        return new Pair<>(i,j);
    }

}
