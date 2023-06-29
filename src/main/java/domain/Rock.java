package domain;

public class Rock extends Pieces {

    public Rock(final State state) {
        super(state);
    }

    @Override
    boolean canMove(final char preRank, final char preFile, final Location moveLocation) {
        return moveLocation.isSameRank(preRank) || moveLocation.isSameFile(preFile);
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
