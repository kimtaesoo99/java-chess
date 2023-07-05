package chess.domain.board;

import java.util.Arrays;

import static chess.domain.additional.Error.WRONG_FILE_NAME;

public enum File {

    ONE('1'),
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8');

    private final char name;

    File(final char name) {
        this.name = name;
    }

    public boolean isSameFile(final char file) {
        return this.name == file;
    }

    public static File getFile(final char name) {
        return Arrays.stream(File.values())
            .filter(file -> file.name == (name))
            .findAny().orElseThrow(() -> new IllegalStateException(WRONG_FILE_NAME.getMessage()));
    }

    public char getName() {
        return name;
    }
}
