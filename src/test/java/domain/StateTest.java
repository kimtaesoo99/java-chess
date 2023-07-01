package domain;

import chess.domain.board.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class StateTest {

    @ParameterizedTest
    @DisplayName("기물에 해당하는 이름을 반환한다.")
    @CsvSource({"BLACK_PAWN, P", "BLACK_BISHOP, B", "BLACK_KNIGHT, N", "BLACK_ROCK, R", "BLACK_QUEEN, Q",
        "BLACK_KING, K", "WHITE_PAWN, p", "WHITE_BISHOP, b", "WHITE_KNIGHT, n", "WHITE_ROCK, r",
        "WHITE_QUEEN, q", "WHITE_KING, k"})
    public void testGetName(final State piece, final String expected) {
        //when
        String actual = piece.getName();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
