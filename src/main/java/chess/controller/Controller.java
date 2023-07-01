package chess.controller;

import chess.domain.additional.Command;
import chess.domain.board.Board;
import chess.domain.board.Location;
import chess.domain.board.State;
import chess.dto.BoardStatusResponse;
import chess.service.InitBoard;
import chess.util.Splitter;
import chess.view.InputView;
import chess.view.OutputView;

public class Controller {

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
        Command command = getStartCommand();
        initBoard();
        OutputView.printBoardStatus(BoardStatusResponse.from(board));
        State turn = State.WHITE_TEAM;

        while (!command.isEnd()) {
            command = getCommand(turn);
            turn = turn.changeTurn();
        }
    }

    private Command getStartCommand() {
        try {
            String input = InputView.readCommand();
            return Command.createStartCommand(input);
        } catch (IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getStartCommand();
        }
    }

    private void initBoard() {
        board = new Board(new InitBoard().getInitBoard());
    }

    private Command getCommand(final State turn) {
        try {
            String input = InputView.readCommand();
            Command command = Command.createCommand(input);
            checkMoveCommand(command, input, turn);
            return command;
        } catch (IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getCommand(turn);
        }
    }

    private void checkMoveCommand(final Command command, final String input, final State turn) {
        if (command.isMove()) {
            findMoveLocation(input, turn);
        }
    }

    private void findMoveLocation(final String input, final State turn) {
        String preLocation = Splitter.splitString(input, PRE_INDEX);
        String moveLocation = Splitter.splitString(input, MOVE_INDEX);

        movePieces(preLocation, moveLocation, turn);
    }

    private void movePieces(final String preLocation, final String moveLocation, final State turn) {
        Location pre = Location.from(preLocation.charAt(RANK_INDEX), preLocation.charAt(FILE_INDEX));
        Location move = Location.from(moveLocation.charAt(RANK_INDEX), moveLocation.charAt(FILE_INDEX));

        board.movePieces(pre, move, turn);

        OutputView.printBoardStatus(BoardStatusResponse.from(board));
    }
}
