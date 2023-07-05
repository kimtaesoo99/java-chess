package chess.domain.pieces;

import chess.domain.board.Location;
import chess.domain.board.State;

import static chess.domain.additional.Direction.DOUBLE_DOWN;
import static chess.domain.additional.Direction.DOUBLE_UP;
import static chess.domain.additional.Direction.DOWN;
import static chess.domain.additional.Direction.FIRST_DIAGONAL;
import static chess.domain.additional.Direction.FOURTH_DIAGONAL;
import static chess.domain.additional.Direction.SECOND_DIAGONAL;
import static chess.domain.additional.Direction.THIRD_DIAGONAL;
import static chess.domain.additional.Direction.UP;

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
        if (preFile == INIT_BLACK_PAWN_FILE) {
            return checkBlackFirstMove(preRank, preFile, moveLocation) || checkBlackMoveOneStep(preRank, preFile, moveLocation);
        }

        return checkBlackMoveOneStep(preRank, preFile, moveLocation);
    }

    private boolean checkBlackFirstMove(final char preRank, final char preFile, final Location moveLocation) {
        return moveLocation.isSameLocation((char) (preRank + DOUBLE_DOWN.getRow()), (char) (preFile + DOUBLE_DOWN.getColumn()));
    }

    private boolean checkBlackMoveOneStep(final char preRank, final char preFile, final Location moveLocation) {
        return moveLocation.isSameLocation((char) (preRank + DOWN.getRow()), (char) (preFile + DOWN.getColumn()))
            || moveLocation.isSameLocation((char) (preRank + SECOND_DIAGONAL.getRow()), (char) (preFile + SECOND_DIAGONAL.getColumn()))
            || moveLocation.isSameLocation((char) (preRank + THIRD_DIAGONAL.getRow()), (char) (preFile + THIRD_DIAGONAL.getColumn()));
    }

    private boolean checkWhiteMove(final char preRank, final char preFile, final Location moveLocation) {
        if (preFile == INIT_WHITE_PAWN_FILE) {
            return checkWhiteFirstMove(preRank, preFile, moveLocation) || checkWhiteMoveOneStep(preRank, preFile, moveLocation);
        }

        return checkWhiteMoveOneStep(preRank, preFile, moveLocation);
    }

    private boolean checkWhiteFirstMove(final char preRank, final char preFile, final Location moveLocation) {
        return moveLocation.isSameLocation((char) (preRank + DOUBLE_UP.getRow()), (char) (preFile + DOUBLE_UP.getColumn()));
    }

    private boolean checkWhiteMoveOneStep(final char preRank, final char preFile, final Location moveLocation) {
        return moveLocation.isSameLocation((char) (preRank + UP.getRow()), (char) (preFile + UP.getColumn()))
            || moveLocation.isSameLocation((char) (preRank + FIRST_DIAGONAL.getRow()), (char) (preFile + FIRST_DIAGONAL.getColumn()))
            || moveLocation.isSameLocation((char) (preRank + FOURTH_DIAGONAL.getRow()), (char) (preFile + FOURTH_DIAGONAL.getColumn()));
    }

    @Override
    public boolean isKnight() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
