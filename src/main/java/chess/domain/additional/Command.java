package chess.domain.additional;

import java.util.Arrays;

import static chess.domain.additional.Error.CAN_START_WITH_START_COMMAND;
import static chess.domain.additional.Error.EARLY_START_GAME;
import static chess.domain.additional.Error.WRONG_INPUT;
import static chess.domain.additional.Error.WRONG_MOVE_COMMAND;
import static chess.util.Splitter.splitSize;
import static chess.util.Splitter.splitString;
import static chess.util.Splitter.splitStringLength;

public enum Command {

    START("start"),
    END("end"),
    MOVE("move");

    private static final int COMMAND = 0;
    private static final int CORRECT_MOVE_SIZE = 3;
    private static final int PRE_LOCATION_INFO = 1;
    private static final int MOVE_LOCATION_INFO = 2;
    private static final int MOVE_INFO_LENGTH = 2;
    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";
    private static final String MOVE_COMMAND = "move";

    private final String select;

    Command(final String select) {
        this.select = select;
    }

    public static Command createStartCommand(final String input) {
        validationFirstCommand(input);
        return Command.START;
    }

    public static Command createCommand(final String input) {
        validationCommand(input);

        if (isMoveCommand(input)) {
            validateMoveCommand(input);
            return Command.MOVE;
        }

        return Command.END;
    }

    private static void validationFirstCommand(final String input) {
        if (!isStartCommand(input)) {
            throw new IllegalStateException(CAN_START_WITH_START_COMMAND.getMessage());
        }
    }

    private static void validationCommand(final String input) {
        if (!isCorrectCommand(input)) {
            throw new IllegalStateException(WRONG_INPUT.getMessage());
        }

        if (isStartCommand(input)) {
            throw new IllegalStateException(EARLY_START_GAME.getMessage());
        }
    }

    private static boolean isStartCommand(final String input) {
        return Command.START.select.equals(input);
    }

    private static boolean isCorrectCommand(final String input) {
        return Arrays.stream(Command.values())
            .anyMatch(command -> splitString(input, COMMAND).equals(command.select));
    }

    private static boolean isMoveCommand(final String input) {
        return input.startsWith(Command.MOVE.select);
    }

    private static void validateMoveCommand(final String input) {
        if (splitSize(input) != CORRECT_MOVE_SIZE) {
            throw new IllegalStateException(WRONG_MOVE_COMMAND.getMessage());
        }

        if (splitStringLength(input, PRE_LOCATION_INFO) != MOVE_INFO_LENGTH || splitStringLength(input, MOVE_LOCATION_INFO) != MOVE_INFO_LENGTH) {
            throw new IllegalStateException(WRONG_MOVE_COMMAND.getMessage());
        }
    }

    public boolean isEnd() {
        return this.select.equals(END_COMMAND);
    }

    public boolean isStart() {
        return this.select.equals(START_COMMAND);
    }

    public boolean isMove() {
        return this.select.equals(MOVE_COMMAND);
    }
}
