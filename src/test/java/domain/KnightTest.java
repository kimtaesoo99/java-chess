package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {

    private Pieces pieces;

    @BeforeEach
    void init() {
        pieces = new Knight(State.BLACK_KNIGHT);
    }

    @ParameterizedTest
    @DisplayName("비숍이 이동할수 있는지 확인한다.")
    @CsvSource({"a, 2, true", "d, 1, true", "c, 4, false", "f, 1, false", "b, 5, true"})
    void canMove(final char moveRank, final char moveFile, final boolean canMove) {
        //given
        char rank = 'c';
        char file = '3';
        Location moveLocation = Location.from(moveRank, moveFile);

        //when & then
        assertThat(pieces.canMove(rank, file, moveLocation)).isEqualTo(canMove);
    }

    @DisplayName("해당 기물이 나이트인지 반환한다.")
    @Test
    void isKnight() {
        //when
        boolean isKnight = pieces.isKnight();

        //then
        assertThat(isKnight).isTrue();
    }

    @DisplayName("해당 기물이 폰인지 반환한다.")
    @Test
    void isPawn() {
        //when
        boolean isPawn = pieces.isPawn();

        //then
        assertThat(isPawn).isFalse();
    }

    @DisplayName("해당 기물이 검정색인지 반환한다.")
    @Test
    void isBlack() {
        //when
        boolean isBlack = pieces.isBlack();

        //then
        assertThat(isBlack).isTrue();
    }

    @DisplayName("해당 기물의 이름을 반환한다.")
    @Test
    void getName() {
        //when
        String name = pieces.getName();

        //then
        assertThat(name).isEqualTo("N");
    }
}
