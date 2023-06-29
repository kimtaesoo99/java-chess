package domain;

public class Bishop extends Pieces {

    public Bishop(final State state) {
        super(state);
    }

    @Override
    boolean canMove(final char preRank, final char preFile, final Location moveLocation) {
        char moveRank = moveLocation.getRank();
        char moveFile = moveLocation.getFile();

        return Math.abs(preRank - moveRank) == Math.abs(preFile - moveFile);
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
