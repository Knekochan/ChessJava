package sample;
import javafx.util.Pair;
import sample.ChessPieces.Color;

import java.util.List;

//tábla
public final class ChessBoard {
    private static int[][] board;
    private static List<Integer> deadWhitePiece;
    private static List<Integer> deadBlackPiece;

    public static void generateBoard() {
        board = new int[8][8];
        //tábla egyik oldala
        board[0][0] = 2; //bástya
        board[0][1] = 3; //huszár
        board[0][2] = 4; //futó
        board[0][3] = 5; //királynő
        board[0][4] = 6; //király
        board[0][5] = 4; //futó
        board[0][6] = 3; //huszár
        board[0][7] = 2; //bástya

        //tábla másik oldala
        board[7][0] = 2; //bástya
        board[7][1] = 3; //huszár
        board[7][2] = 4; //futó
        board[7][3] = 5; //királynő
        board[7][4] = 6; //király
        board[7][5] = 4; //futó
        board[7][6] = 3; //huszár
        board[7][7] = 2; //bástya

        //parasztok
        for (int i = 0; i < 8; i++) {
            board[1][i] = 1;
            board[6][i] = 1;
        }

        //többi mező
        for (int i = 0; i < 8; i++) {
            board[2][i] = 0;
            board[3][i] = 0;
            board[4][i] = 0;
            board[5][i] = 0;
        }
    }

    public static int[][] getBoard() {
        return board;
    }

    public static List<Integer> getDeadWhitePiece() {
        return deadWhitePiece;
    }

    public static void setDeadWhitePiece(int value) {
        deadWhitePiece.add(value);
    }

    public static List<Integer> getDeadBlackPiece() {
        return deadBlackPiece;
    }

    public static void setDeadBlackPiece(int value) {
        deadBlackPiece.add(value);
    }

    //a halott bábuk listájából törli a vissza került bábut
    //akkor használjuk amikor az egyik paraszt a pálya tulsó végére ér és lehetőség van a bábu cserére
    public static void removeDeadPiece(Color color, int value)
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
    public static int getFieldValue(Pair<Integer,Integer> value) {
        return board[value.getKey()][value.getValue()];
    }

    //bábut cserél
    //a tábla adott helyén lévő értéket átírja
    public static void changeFieldValue(Pair<Integer,Integer> piece, int i){
        board[piece.getKey()][piece.getValue()] = i;
    }

    //ezt egyelőre nem használom
    public static int findPieceIndex(Pair<Integer,Integer> value) {
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board[1].length; j++) {
                if (i == value.getKey() && j == value.getValue()) {
                    return board[i][j];
                }
            }
        }
        return -1;
    }
}
