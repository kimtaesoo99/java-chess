package domain;

import java.util.Arrays;

import static domain.Error.WRONG_INPUT;

public enum Command {

    START("start"),
    END("end"),
    MOVE("move");

    private final String select;

    Command(final String select) {
        this.select = select;
    }

    public static Command from(final String command) {
        return Arrays.stream(Command.values())
            .filter(status -> command.startsWith(status.select))
            .findAny().orElseThrow(() -> new IllegalStateException(WRONG_INPUT.getMessage()));
    }

    public static boolean isCorrectCommand(final String command) {
        return Arrays.stream(Command.values())
            .anyMatch(status -> command.startsWith(status.select));
    }

    public static boolean isStartCommand(final String command) {
        return Command.START.select.equals(command);
    }

    public boolean isEnd() {
        return this.select.equals("end");
    }

    public boolean isStart() {
        return this.select.equals("start");
    }

    public boolean isMove() {
        return this.select.equals("move");
    }
}
