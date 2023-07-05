package chess.domain.pieces;

import chess.domain.board.Location;
import chess.domain.board.State;

public class Bishop extends Pieces {

    public Bishop(final State state) {
        super(state);
    }

    @Override
    public boolean canMove(final char preRank, final char preFile, final Location moveLocation) {
        char moveRank = moveLocation.getRank();
        char moveFile = moveLocation.getFile();

        return Math.abs(preRank - moveRank) == Math.abs(preFile - moveFile);
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
