package domain;

public abstract class Pieces {

    private final State state;

    public Pieces(final State state) {
        this.state = state;
    }

    abstract boolean canMove(final char preRank, final char preFile, final Location moveLocation);

    public boolean isBlack() {
        return state.isBlack();
    }

    public String getName() {
        return state.getName();
    }

    abstract boolean isKnight();

    abstract boolean isPawn();
}
