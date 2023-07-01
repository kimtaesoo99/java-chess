package chess.view;

import chess.dto.BoardStatusResponse;

public class OutputView {

    private static final String START_GAME = "> 체스 게임을 시작합니다.";
    private static final String START_COMMAND = "> 게임 시작 : start";
    private static final String END_COMMAND = "> 게임 종료 : end";
    private static final String MOVE_COMMAND = "> 게임 이동 : move source 위치 target 위치 - 예. move b2 b3";

    public static void printStart() {
        System.out.println(START_GAME);
        System.out.println(START_COMMAND);
        System.out.println(END_COMMAND);
        System.out.println(MOVE_COMMAND);
    }

    public static void printBoardStatus(final BoardStatusResponse boardStatusResponse) {
        System.out.println(boardStatusResponse.getBoardStatus());
    }

    public static void printErrorMessage(final String errorMessage) {
        System.out.println(errorMessage);
    }
}
