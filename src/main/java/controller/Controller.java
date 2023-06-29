package controller;

import domain.Board;
import domain.Command;
import domain.Location;
import dto.BoardStatusResponse;
import service.InitBoard;
import view.InputView;
import view.OutputView;

public class Controller {

    private static final String SEPARATOR = " ";
    private static final int PRE_INDEX = 1;
    private static final int MOVE_INDEX = 2;
    private static final int RANK_INDEX = 0;
    private static final int FILE_INDEX = 1;

    private Board board;

    public void start() {
        OutputView.printStart();
        startGame();
    }

    private void startGame() {
        String input = InputView.readFirstCommand();
        Command command = Command.from(input);

        while (!command.isEnd()) {
            checkCommand(command, input);
            input = InputView.readCommand();
            command = Command.from(input);
        }
    }

    private void checkCommand(final Command command, final String input) {
        if (command.isStart()) {
            initBoard();
        }

        if (command.isMove()) {
            findMoveLocation(input);
        }

        OutputView.printBoardStatus(BoardStatusResponse.from(board));
    }

    private void initBoard() {
        board = new Board(new InitBoard().getInitBoard());
    }

    private void findMoveLocation(final String input) {
        String[] inputInfo = input.split(SEPARATOR);
        String preLocation = inputInfo[PRE_INDEX];
        String moveLocation = inputInfo[MOVE_INDEX];

        movePieces(preLocation, moveLocation);
    }

    private void movePieces(final String preLocation, final String moveLocation) {
        try {
            Location pre = Location.from(preLocation.charAt(RANK_INDEX), preLocation.charAt(FILE_INDEX));
            Location move = Location.from(moveLocation.charAt(RANK_INDEX), moveLocation.charAt(FILE_INDEX));
            board.movePieces(pre, move);
        } catch (IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }
}
