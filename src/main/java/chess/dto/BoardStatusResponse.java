package chess.dto;

import chess.domain.board.Board;
import chess.domain.board.Location;
import chess.domain.pieces.Pieces;

import java.util.Map;

public class BoardStatusResponse {

    private static final int FILE_MAX = 8;
    private static final int FILE_INIT = 1;
    private static final int RANK_MAX = 7;
    private static final int RANK_INIT = 0;
    private static final String EMPTY = ".";
    private static final char FIRST_RANK = 'a';
    private static final char FIRST_FILE = '0';
    private static final String NEXT_LINE = "\n";

    private final String boardStatus;

    private BoardStatusResponse(final String boardStatus) {
        this.boardStatus = boardStatus;
    }

    public static BoardStatusResponse from(final Board board) {
        String boardStatus = getBoardStatus(board.getBoard());

        return new BoardStatusResponse(boardStatus);
    }

    private static String getBoardStatus(final Map<Location, Pieces> board) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int file = FILE_MAX; file >= FILE_INIT; file--) {
            findBoardStatus(file, stringBuilder, board);
            stringBuilder.append(NEXT_LINE);
        }
        return stringBuilder.toString();
    }

    private static void findBoardStatus(final int file, final StringBuilder stringBuilder, final Map<Location, Pieces> board) {
        for (int rank = RANK_INIT; rank <= RANK_MAX; rank++) {
            Location location = Location.from((char) (FIRST_RANK + rank), (char) (FIRST_FILE + file));
            addBoardStatus(location, stringBuilder, board);
        }
    }

    private static void addBoardStatus(final Location location, final StringBuilder stringBuilder, final Map<Location, Pieces> board) {
        if (board.containsKey(location)) {
            stringBuilder.append(board.get(location).getName());
            return;
        }
        stringBuilder.append(EMPTY);
    }

    public String getBoardStatus() {
        return boardStatus;
    }
}
