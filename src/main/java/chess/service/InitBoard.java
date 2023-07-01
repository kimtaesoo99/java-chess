package chess.service;

import chess.domain.board.Location;
import chess.domain.board.State;
import chess.domain.pieces.Bishop;
import chess.domain.pieces.King;
import chess.domain.pieces.Knight;
import chess.domain.pieces.Pawn;
import chess.domain.pieces.Pieces;
import chess.domain.pieces.Queen;
import chess.domain.pieces.Rock;

import java.util.HashMap;
import java.util.Map;

public class InitBoard {

    private static final int RANK_INIT = 0;
    private static final int RANK_MAX = 8;
    private static final char BLACK_PAWN_FILE = '7';
    private static final char WHILE_PAWN_FILE = '2';
    private static final char BLACK_FILE = '8';
    private static final char WHILE_FILE = '1';
    private static final char LOWER_CASE_A = 'a';
    private static final char LOWER_CASE_B = 'b';
    private static final char LOWER_CASE_C = 'c';
    private static final char LOWER_CASE_D = 'd';
    private static final char LOWER_CASE_E = 'e';
    private static final char LOWER_CASE_F = 'f';
    private static final char LOWER_CASE_G = 'g';
    private static final char LOWER_CASE_H = 'h';

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
            board.put(Location.from((char) (LOWER_CASE_A + rank), BLACK_PAWN_FILE), new Pawn(State.BLACK_PAWN));
            board.put(Location.from((char) (LOWER_CASE_A + rank), WHILE_PAWN_FILE), new Pawn(State.WHITE_PAWN));
        }
    }

    private void initBishop(final Map<Location, Pieces> board) {
        board.put(Location.from(LOWER_CASE_C, BLACK_FILE), new Bishop(State.BLACK_BISHOP));
        board.put(Location.from(LOWER_CASE_F, BLACK_FILE), new Bishop(State.BLACK_BISHOP));
        board.put(Location.from(LOWER_CASE_C, WHILE_FILE), new Bishop(State.WHITE_BISHOP));
        board.put(Location.from(LOWER_CASE_F, WHILE_FILE), new Bishop(State.WHITE_BISHOP));
    }

    private void initKnight(final Map<Location, Pieces> board) {
        board.put(Location.from(LOWER_CASE_B, BLACK_FILE), new Knight(State.BLACK_KNIGHT));
        board.put(Location.from(LOWER_CASE_G, BLACK_FILE), new Knight(State.BLACK_KNIGHT));
        board.put(Location.from(LOWER_CASE_B, WHILE_FILE), new Knight(State.WHITE_KNIGHT));
        board.put(Location.from(LOWER_CASE_G, WHILE_FILE), new Knight(State.WHITE_KNIGHT));
    }

    private void initRock(final Map<Location, Pieces> board) {
        board.put(Location.from(LOWER_CASE_A, BLACK_FILE), new Rock(State.BLACK_ROCK));
        board.put(Location.from(LOWER_CASE_H, BLACK_FILE), new Rock(State.BLACK_ROCK));
        board.put(Location.from(LOWER_CASE_A, WHILE_FILE), new Rock(State.WHITE_ROCK));
        board.put(Location.from(LOWER_CASE_H, WHILE_FILE), new Rock(State.WHITE_ROCK));
    }

    private void initQueen(final Map<Location, Pieces> board) {
        board.put(Location.from(LOWER_CASE_D, BLACK_FILE), new Queen(State.BLACK_QUEEN));
        board.put(Location.from(LOWER_CASE_D, WHILE_FILE), new Queen(State.WHITE_QUEEN));
    }

    private void initKing(final Map<Location, Pieces> board) {
        board.put(Location.from(LOWER_CASE_E, BLACK_FILE), new King(State.BLACK_KING));
        board.put(Location.from(LOWER_CASE_E, WHILE_FILE), new King(State.WHITE_KING));
    }
}
