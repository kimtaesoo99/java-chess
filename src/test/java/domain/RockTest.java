package domain;

import chess.domain.board.Location;
import chess.domain.board.State;
import chess.domain.pieces.Pieces;
import chess.domain.pieces.Rock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RockTest {

    private Pieces pieces;

    @BeforeEach
    void init() {
        pieces = new Rock(State.BLACK_ROCK);
    }

    @ParameterizedTest
    @DisplayName("비숍이 이동할수 있는지 확인한다.")
    @CsvSource({"c, 4, true", "e, 3, true", "a, 3, true", "a, 1, false", "c, 5, true"})
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
        assertThat(isKnight).isFalse();
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
        assertThat(name).isEqualTo("R");
    }

}
