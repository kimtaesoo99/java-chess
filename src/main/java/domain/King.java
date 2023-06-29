package domain;

import static domain.Direction.*;

public class King extends Pieces {

    public King(final State state) {
        super(state);
    }

    @Override
    boolean canMove(final char preRank, final char preFile, final Location moveLocation) {
        return checkCanMove(preRank, preFile, moveLocation);
    }

    private boolean checkCanMove(final char preRank, final char preFile, final Location moveLocation) {
        return moveLocation.isSameLocation((char) (preRank + RIGHT.getRow()), (char) (preFile + RIGHT.getColumn()))
            || moveLocation.isSameLocation((char) (preRank + LEFT.getRow()), (char) (preFile + LEFT.getColumn()))
            || moveLocation.isSameLocation((char) (preRank + UP.getRow()), (char) (preFile + UP.getColumn()))
            || moveLocation.isSameLocation((char) (preRank + DOWN.getRow()), (char) (preFile + DOWN.getColumn()))
            || moveLocation.isSameLocation((char) (preRank + FIRST_DIAGONAL.getRow()), (char) (preFile + FIRST_DIAGONAL.getColumn()))
            || moveLocation.isSameLocation((char) (preRank + SECOND_DIAGONAL.getRow()), (char) (preFile + SECOND_DIAGONAL.getColumn()))
            || moveLocation.isSameLocation((char) (preRank + THIRD_DIAGONAL.getRow()), (char) (preFile + THIRD_DIAGONAL.getColumn()))
            || moveLocation.isSameLocation((char) (preRank + FOURTH_DIAGONAL.getRow()), (char) (preFile + FOURTH_DIAGONAL.getColumn()));
    }

    @Override
    boolean isKnight() {
        return false;
    }

    @Override
    boolean isPawn() {
        return false;
    }
}
