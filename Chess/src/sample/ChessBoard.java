package sample;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Pair;
import sample.ChessPieces.*;

import java.util.*;

//tábla
public class ChessBoard {
    private static ChessBoard chessBoard = null;

    private ChessPiece[][] board;
    private ArrayList<ChessPiece> deadWhitePiece;
    private ArrayList<ChessPiece> deadBlackPiece;

    private ChessBoard() {
        this.board = new ChessPiece[8][8];
        this.deadWhitePiece = new ArrayList<ChessPiece>();
        this.deadBlackPiece = new ArrayList<ChessPiece>();

        //tábla egyik oldala
        this.board[0][0] = new Rook(); //bástya
        this.board[0][1] = new Knight(); //huszár
        this.board[0][2] = new Bishop(); //futó
        this.board[0][3] = new Queen(); //királynő
        this.board[0][4] = new King(); //király
        this.board[0][5] = new Bishop(); //futó
        this.board[0][6] = new Knight(); //huszár
        this.board[0][7] = new Rook(); //bástya

        //tábla másik oldala
        this.board[7][0] = new Rook(); //bástya
        this.board[7][1] = new Knight(); //huszár
        this.board[7][2] = new Bishop(); //futó
        this.board[7][3] = new Queen(); //királynő
        this.board[7][4] = new King(); //király
        this.board[7][5] = new Bishop(); //futó
        this.board[7][6] = new Knight(); //huszár
        this.board[7][7] = new Rook(); //bástya

        //parasztok
        for (int i = 0; i < 8; i++) {
            this.board[1][i] = new Pawn(Color.BLACK);
            this.board[6][i] = new Pawn(Color.WHITE);
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
    public static ChessBoard getInstance()
    {
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

    //bábuk léptetése
    public void movePiece(ChessPiece piece, Pair<Integer,Integer> value) {
        if (piece.CanMoveTo(piece.getIndex())) {
            Pair<Integer, Integer> help;
            help = piece.getIndex();
            piece.setIndex(value);
            this.changeFieldValue(help, null);
            this.changeFieldValue(piece.getIndex(), piece);
        }
    }

    public void draw(GraphicsContext gc) {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                this.board[i][j].draw(gc);
            }
        }
    }

    //a halott bábuk listájából törli a vissza került bábut
    //akkor használjuk amikor az egyik paraszt a pálya tulsó végére ér és lehetőség van a bábu cserére
    public void removeDeadPiece(Color color, ChessPiece value)
    {
        int i = 0;
        if (color == color.WHITE) {
            while(deadWhitePiece.get(i) != value &&  i < deadWhitePiece.size()) {
                i++;
            }
            if (i < deadWhitePiece.size()) {
                deadWhitePiece.remove(i);
            }
        }
        else {
            while(deadBlackPiece.get(i) != value &&  i < deadBlackPiece.size()) {
                i++;
            }
            if (i < deadBlackPiece.size()) {
                deadBlackPiece.remove(i);
            }
        }

    }

    //vissza adja az adott táblahelyen található értéket
    //vagyis megmondja, hogy a tábla adott helyén milyen bábu van
    public ChessPiece getFieldValue(Pair<Integer,Integer> value) {
        return board[value.getKey()][value.getValue()];
    }

    //bábut cserél
    //a tábla adott helyén lévő értéket átírja
    public void changeFieldValue(Pair<Integer,Integer> piece, ChessPiece i){
        board[piece.getKey()][piece.getValue()] = i;
    }

    //ezt egyelőre nem használom

    public ChessPiece findPieceIndex(Pair<Integer,Integer> value) {
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board[1].length; j++) {
                if (i == value.getKey() && j == value.getValue()) {
                    return board[i][j];
                }
            }
        }
        return null;
    }

}
