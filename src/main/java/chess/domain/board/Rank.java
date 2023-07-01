package chess.domain.board;

import java.util.Arrays;

import static chess.domain.additional.Error.WRONG_RANK_NAME;

public enum Rank {

    A('a'),
    B('b'),
    C('c'),
    D('d'),
    E('e'),
    F('f'),
    G('g'),
    H('h');

    private final char name;

    Rank(final char name) {
        this.name = name;
    }

    public boolean isSameRank(final char name) {
        return this.name == name;
    }

    public static Rank getRank(final char name) {
        return Arrays.stream(Rank.values())
            .filter(rank -> rank.name == (name))
            .findAny().orElseThrow(() -> new IllegalStateException(WRONG_RANK_NAME.getMessage()));
    }

    public char getName() {
        return name;
    }
}
