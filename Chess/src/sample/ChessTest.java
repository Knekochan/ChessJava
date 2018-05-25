package sample;

import javafx.util.Pair;
import org.junit.Test;
import sample.ChessPieces.ChessPiece;
import sample.ChessPieces.Color;
import sample.ChessPieces.Pawn;

import static org.junit.Assert.assertEquals;

public class ChessTest {
    @Test
    public void pawnTestFalse() {
        ChessPiece pawn1 = new Pawn(Color.WHITE, new Pair<>(1, 6));
        assertEquals(false, ChessBoard.getInstance().movePiece(pawn1, new Pair<>(1, 1)));
    }

    @Test
    public void pawnTestTrue() {
        ChessPiece pawn1 = new Pawn(Color.BLACK, new Pair<>(4, 4));
        assertEquals(true, ChessBoard.getInstance().movePiece(pawn1, new Pair<>(3, 4)));
    }
}
