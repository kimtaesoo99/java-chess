package dto;

import domain.Board;

public class BoardStatusResponse {

    private final String boardStatus;

    private BoardStatusResponse(final String boardStatus) {
        this.boardStatus = boardStatus;
    }

    public static BoardStatusResponse from(final Board board) {
        return new BoardStatusResponse(board.getBoardStatus());
    }

    public String getBoardStatus() {
        return boardStatus;
    }
}
