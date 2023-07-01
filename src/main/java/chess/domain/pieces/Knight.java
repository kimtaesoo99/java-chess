package chess.domain.pieces;

import chess.domain.board.Location;
import chess.domain.board.State;

import static chess.domain.additional.Direction.FIRST_DIAGONAL_RIGHT;
import static chess.domain.additional.Direction.FIRST_DIAGONAL_UP;
import static chess.domain.additional.Direction.FOURTH_DIAGONAL_LEFT;
import static chess.domain.additional.Direction.FOURTH_DIAGONAL_UP;
import static chess.domain.additional.Direction.SECOND_DIAGONAL_DOWN;
import static chess.domain.additional.Direction.SECOND_DIAGONAL_RIGHT;
import static chess.domain.additional.Direction.THIRD_DIAGONAL_DOWN;
import static chess.domain.additional.Direction.THIRD_DIAGONAL_LEFT;

public class Knight extends Pieces {

    public Knight(final State state) {
        super(state);
    }

    @Override
    public boolean canMove(final char preRank, final char preFile, final Location moveLocation) {
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
    public boolean isKnight() {
        return true;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
