package chess.domain.pieces;

import chess.domain.board.Location;
import chess.domain.board.State;

public class Rock extends Pieces {

    public Rock(final State state) {
        super(state);
    }

    @Override
    public boolean canMove(final char preRank, final char preFile, final Location moveLocation) {
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
