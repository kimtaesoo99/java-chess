package domain;

import static domain.Direction.*;

public class Knight extends Pieces {

    public Knight(final State state) {
        super(state);
    }

    @Override
    boolean canMove(final char preRank, final char preFile, final Location moveLocation) {
        return checkCanMove(preRank, preFile, moveLocation);
    }

    private boolean checkCanMove(final char preRank, final char preFile, final Location moveLocation) {
        return moveLocation.isSameLocation((char) (preRank + FIRST_DIAGONAL_UP.getRow()), (char) (preFile + FIRST_DIAGONAL_UP.getColumn()))
            || moveLocation.isSameLocation((char) (preRank + FIRST_DIAGONAL_RIGHT.getRow()), (char) (preFile + FIRST_DIAGONAL_RIGHT.getColumn()))
            || moveLocation.isSameLocation((char) (preRank + SECOND_DIAGONAL_RIGHT.getRow()), (char) (preFile + SECOND_DIAGONAL_RIGHT.getColumn()))
            || moveLocation.isSameLocation((char) (preRank + SECOND_DIAGONAL_DOWN.getRow()), (char) (preFile + SECOND_DIAGONAL_DOWN.getColumn()))
            || moveLocation.isSameLocation((char) (preRank + THIRD_DIAGONAL_DOWN.getRow()), (char) (preFile + THIRD_DIAGONAL_DOWN.getColumn()))
            || moveLocation.isSameLocation((char) (preRank + THIRD_DIAGONAL_LEFT.getRow()), (char) (preFile + THIRD_DIAGONAL_LEFT.getColumn()))
            || moveLocation.isSameLocation((char) (preRank + FOURTH_DIAGONAL_LEFT.getRow()), (char) (preFile + FOURTH_DIAGONAL_LEFT.getColumn()))
            || moveLocation.isSameLocation((char) (preRank + FOURTH_DIAGONAL_UP.getRow()), (char) (preFile + FOURTH_DIAGONAL_UP.getColumn()));
    }

    @Override
    boolean isKnight() {
        return true;
    }

    @Override
    boolean isPawn() {
        return false;
    }
}
