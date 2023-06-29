package domain;

import static domain.Direction.*;

public class Pawn extends Pieces {

    private static final char INIT_BLACK_PAWN_FILE = '7';
    private static final char INIT_WHITE_PAWN_FILE = '2';

    public Pawn(final State state) {
        super(state);
    }

    @Override
    public boolean canMove(final char preRank, final char preFile, final Location moveLocation) {
        if (isBlack()) {
            return checkBlackMove(preRank, preFile, moveLocation);
        }

        return checkWhiteMove(preRank, preFile, moveLocation);
    }

    private boolean checkBlackMove(final char preRank, final char preFile, final Location moveLocation) {
        if (preFile == INIT_BLACK_PAWN_FILE && moveLocation.isSameLocation((char) (preRank + DOUBLE_DOWN.getRow()), (char) (preFile + DOUBLE_DOWN.getColumn()))) {
            return true;
        }
        return checkBlackMoveOneStep(preRank, preFile, moveLocation);
    }

    private boolean checkBlackMoveOneStep(final char preRank, final char preFile, final Location moveLocation) {
        return moveLocation.isSameLocation((char) (preRank + DOWN.getRow()), (char) (preFile + DOWN.getColumn()))
            || moveLocation.isSameLocation((char) (preRank + SECOND_DIAGONAL.getRow()), (char) (preFile + SECOND_DIAGONAL.getColumn()))
            || moveLocation.isSameLocation((char) (preRank + THIRD_DIAGONAL.getRow()), (char) (preFile + THIRD_DIAGONAL.getColumn()));
    }

    private boolean checkWhiteMove(final char preRank, final char preFile, final Location moveLocation) {
        if (preFile == INIT_WHITE_PAWN_FILE && moveLocation.isSameLocation((char) (preRank + DOUBLE_UP.getRow()), (char) (preFile + DOUBLE_UP.getColumn()))) {
            return true;
        }
        return checkWhiteMoveOneStep(preRank, preFile, moveLocation);
    }

    private boolean checkWhiteMoveOneStep(final char preRank, final char preFile, final Location moveLocation) {
        return moveLocation.isSameLocation((char) (preRank + UP.getRow()), (char) (preFile + UP.getColumn()))
            || moveLocation.isSameLocation((char) (preRank + FIRST_DIAGONAL.getRow()), (char) (preFile + FIRST_DIAGONAL.getColumn()))
            || moveLocation.isSameLocation((char) (preRank + FOURTH_DIAGONAL.getRow()), (char) (preFile + FOURTH_DIAGONAL.getColumn()));
    }

    @Override
    boolean isKnight() {
        return false;
    }

    @Override
    boolean isPawn() {
        return true;
    }
}
