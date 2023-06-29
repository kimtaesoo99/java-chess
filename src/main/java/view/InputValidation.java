package view;

import domain.Command;

import static domain.Error.*;

public class InputValidation {

    private static final String SEPARATOR = " ";

    private static final int PRE_LOCATION = 1;
    private static final int MOVE_LOCATION = 2;
    private static final int CORRECT_MOVE_INFO_SIZE = 2;
    private static final int SPLIT_SIZE = 3;

    public void validateFirstCommand(final String command) {
        validateCorrectCommand(command);
        validateStartCommand(command);
    }

    public void validateCommand(final String command) {
        validateCorrectCommand(command);
        if (Command.isStartCommand(command)) {
            validateProgressingCommand(command);
        }
        validateMoveCommand(command);
    }

    private void validateCorrectCommand(final String command) {
        if (!Command.isCorrectCommand(command)) {
            throw new IllegalStateException(WRONG_INPUT.getMessage());
        }
    }

    private void validateStartCommand(final String command) {
        if (!Command.isStartCommand(command)) {
            throw new IllegalStateException(CAN_START_WITH_START_COMMAND.getMessage());
        }
    }

    private void validateProgressingCommand(final String command) {
        if (Command.isStartCommand(command)) {
            throw new IllegalStateException(EARLY_START_GAME.getMessage());
        }
    }

    private void validateMoveCommand(final String command) {
        String[] moveLocation = command.split(SEPARATOR);
        if (moveLocation.length != SPLIT_SIZE) {
            throw new IllegalStateException(WRONG_MOVE_COMMAND.getMessage());
        }

        if (moveLocation[PRE_LOCATION].length() != CORRECT_MOVE_INFO_SIZE || moveLocation[MOVE_LOCATION].length() != CORRECT_MOVE_INFO_SIZE) {
            throw new IllegalStateException(WRONG_MOVE_COMMAND.getMessage());
        }
    }
}
