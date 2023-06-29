package service;

import domain.Bishop;
import domain.King;
import domain.Knight;
import domain.Location;
import domain.Pawn;
import domain.Pieces;
import domain.Queen;
import domain.Rock;
import domain.State;

import java.util.HashMap;
import java.util.Map;

public class InitBoard {

    private static final int RANK_INIT = 0;
    private static final int RANK_MAX = 8;
    private static final char BLACK_PAWN_FILE = '7';
    private static final char WHILE_PAWN_FILE = '2';
    private static final char BLACK_FILE = '8';
    private static final char WHILE_FILE = '1';
    private static final char A = 'a';
    private static final char B = 'b';
    private static final char C = 'c';
    private static final char D = 'd';
    private static final char E = 'e';
    private static final char F = 'f';
    private static final char G = 'g';
    private static final char H = 'h';

    public Map<Location, Pieces> getInitBoard() {
        Map<Location, Pieces> board = new HashMap<>();
        initPawn(board);
        initBishop(board);
        initKnight(board);
        initRock(board);
        initQueen(board);
        initKing(board);

        return board;
    }

    private void initPawn(final Map<Location, Pieces> board) {
        for (int rank = RANK_INIT; rank < RANK_MAX; rank++) {
            board.put(Location.from((char) (A + rank), BLACK_PAWN_FILE), new Pawn(State.BLACK_PAWN));
            board.put(Location.from((char) (A + rank), WHILE_PAWN_FILE), new Pawn(State.WHITE_PAWN));
        }
    }

    private void initBishop(final Map<Location, Pieces> board) {
        board.put(Location.from(C, BLACK_FILE), new Bishop(State.BLACK_BISHOP));
        board.put(Location.from(F, BLACK_FILE), new Bishop(State.BLACK_BISHOP));
        board.put(Location.from(C, WHILE_FILE), new Bishop(State.WHITE_BISHOP));
        board.put(Location.from(F, WHILE_FILE), new Bishop(State.WHITE_BISHOP));
    }

    private void initKnight(final Map<Location, Pieces> board) {
        board.put(Location.from(B, BLACK_FILE), new Knight(State.BLACK_KNIGHT));
        board.put(Location.from(G, BLACK_FILE), new Knight(State.BLACK_KNIGHT));
        board.put(Location.from(B, WHILE_FILE), new Knight(State.WHITE_KNIGHT));
        board.put(Location.from(G, WHILE_FILE), new Knight(State.WHITE_KNIGHT));
    }

    private void initRock(final Map<Location, Pieces> board) {
        board.put(Location.from(A, BLACK_FILE), new Rock(State.BLACK_ROCK));
        board.put(Location.from(H, BLACK_FILE), new Rock(State.BLACK_ROCK));
        board.put(Location.from(A, WHILE_FILE), new Rock(State.WHITE_ROCK));
        board.put(Location.from(H, WHILE_FILE), new Rock(State.WHITE_ROCK));
    }

    private void initQueen(final Map<Location, Pieces> board) {
        board.put(Location.from(D, BLACK_FILE), new Queen(State.BLACK_QUEEN));
        board.put(Location.from(D, WHILE_FILE), new Queen(State.WHITE_QUEEN));
    }

    private void initKing(final Map<Location, Pieces> board) {
        board.put(Location.from(E, BLACK_FILE), new King(State.BLACK_KING));
        board.put(Location.from(E, WHILE_FILE), new King(State.WHITE_KING));
    }
}
