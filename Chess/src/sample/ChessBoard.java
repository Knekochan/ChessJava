package sample;
import javafx.util.Pair;

//tábla
public final class ChessBoard {
    private static int[][] board;

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

    public static int getFieldValue(Pair<Integer,Integer> i) {
        return board[i.getKey()][i.getValue()];
    }

    public static void changeFieldValue(Pair<Integer,Integer> piece, int i){
        board[piece.getKey()][piece.getValue()] = i;
    }


}
