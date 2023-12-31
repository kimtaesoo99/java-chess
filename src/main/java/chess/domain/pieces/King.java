package chess.domain.pieces;

import chess.domain.board.Location;
import chess.domain.board.State;

import static chess.domain.additional.Direction.DOWN;
import static chess.domain.additional.Direction.FIRST_DIAGONAL;
import static chess.domain.additional.Direction.FOURTH_DIAGONAL;
import static chess.domain.additional.Direction.LEFT;
import static chess.domain.additional.Direction.RIGHT;
import static chess.domain.additional.Direction.SECOND_DIAGONAL;
import static chess.domain.additional.Direction.THIRD_DIAGONAL;
import static chess.domain.additional.Direction.UP;

public class King extends Pieces {

    public King(final State state) {
        super(state);
    }

    @Override
    public boolean canMove(final char preRank, final char preFile, final Location moveLocation) {
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
    public boolean isKnight() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
