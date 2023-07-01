package chess.domain.board;

public enum State {

    BLACK_PAWN("P", "black"),
    BLACK_BISHOP("B", "black"),
    BLACK_KNIGHT("N", "black"),
    BLACK_ROCK("R", "black"),
    BLACK_QUEEN("Q", "black"),
    BLACK_KING("K", "black"),
    WHITE_PAWN("p", "white"),
    WHITE_BISHOP("b", "white"),
    WHITE_KNIGHT("n", "white"),
    WHITE_ROCK("r", "white"),
    WHITE_QUEEN("q", "white"),
    WHITE_KING("k", "white"),
    BLACK_TEAM("","black"),
    WHITE_TEAM("","white");

    private static final String BLACK = "black";

    private final String name;
    private final String color;

    State(final String name, final String color) {
        this.name = name;
        this.color = color;
    }

    public boolean isBlack() {
        return color.equals(BLACK);
    }

    public boolean isSameColor(final State turn) {
        return this.color.equals(turn.color);
    }

    public State changeTurn() {
        if (this.equals(BLACK_TEAM)){
            return WHITE_TEAM;
        }
        return BLACK_TEAM;
    }

    public String getName() {
        return name;
    }
}
