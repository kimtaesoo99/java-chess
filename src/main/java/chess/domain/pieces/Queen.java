package chess.domain.pieces;

import chess.domain.board.Location;
import chess.domain.board.State;

public class Queen extends Pieces {

    public Queen(final State state) {
        super(state);
    }

    @Override
    public boolean canMove(final char preRank, final char preFile, final Location moveLocation) {
        char moveRank = moveLocation.getRank();
        char moveFile = moveLocation.getFile();

        int distanceRank = preRank - moveRank;
        int distanceFile = preFile - moveFile;

        return canMoveDiagonal(distanceRank, distanceFile) || canMoveStraight(preRank, preFile, moveLocation);
    }

    private boolean canMoveDiagonal(final int distanceRank, final int distanceFile) {
        return Math.abs(distanceRank) == Math.abs(distanceFile);
    }

    private boolean canMoveStraight(final char preRank, final char preFile, final Location moveLocation) {
        return moveLocation.isSameRank(preRank) || moveLocation.isSameFile(preFile);
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
