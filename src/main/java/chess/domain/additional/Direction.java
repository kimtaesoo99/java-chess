package chess.domain.additional;

import java.util.Arrays;

import static chess.domain.additional.Error.WRONG_ROW_OR_COLUMN;

public enum Direction {

    UP(0, 1),
    DOWN(0, -1),
    RIGHT(1, 0),
    LEFT(-1, 0),
    FIRST_DIAGONAL(1, 1),
    SECOND_DIAGONAL(1, -1),
    THIRD_DIAGONAL(-1, -1),
    FOURTH_DIAGONAL(-1, 1),
    DOUBLE_UP(0,2),
    DOUBLE_DOWN(0,-2),
    FIRST_DIAGONAL_UP(1,2),
    FIRST_DIAGONAL_RIGHT(2,1),
    SECOND_DIAGONAL_RIGHT(2,-1),
    SECOND_DIAGONAL_DOWN(1,-2),
    THIRD_DIAGONAL_DOWN(-1,-2),
    THIRD_DIAGONAL_LEFT(-2,-1),
    FOURTH_DIAGONAL_LEFT(-2,1),
    FOURTH_DIAGONAL_UP(-1,2);

    private static final int ZERO = 0;

    private final int row;
    private final int column;

    Direction(final int row, final int column) {
        this.row = row;
        this.column = column;
    }

    private static boolean isSameDirection(final int row, final int column, final Direction direction) {
        return direction.row == row && direction.column == column;
    }

    public static Direction from(final int row, final int column) {
        return Arrays.stream(Direction.values())
            .filter(direction -> isSameDirection(row, column, direction))
            .findAny()
            .orElseThrow(() -> new IllegalStateException(WRONG_ROW_OR_COLUMN.getMessage()));
    }

    public static int getSubtractDirection(final char pre, final char move) {
        int gap = move - pre;
        return Integer.compare(gap, ZERO);
    }

    public boolean isUpOrDown() {
        return this.equals(UP) || this.equals(DOWN);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
