package chess.domain.pieces;

import chess.domain.board.Location;
import chess.domain.board.State;

public abstract class Pieces {

    private final State state;

    public Pieces(final State state) {
        this.state = state;
    }

    public abstract boolean canMove(final char preRank, final char preFile, final Location moveLocation);

    public boolean isBlack() {
        return state.isBlack();
    }

    public abstract boolean isKnight();

    public abstract boolean isPawn();

    public boolean isSameColor(final Pieces pieces) {
        return this.isBlack() == pieces.isBlack();
    }

    public boolean isSameColor(final State color) {
        return state.isSameColor(color);
    }

    public String getName() {
        return state.getName();
    }
}
