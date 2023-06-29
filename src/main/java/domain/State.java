package domain;

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
    WHITE_KING("k", "white");

    private final String name;
    private final String color;

    State(final String name, final String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public boolean isBlack() {
        return color.equals("black");
    }
}
