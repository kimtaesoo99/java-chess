package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DirectionTest {

    @DisplayName("로우와 컬럼을 통해 방향을 만든다.")
    @Test
    void from() {
        //given
        int row = 0;
        int column = 1;

        //when
        Direction direction = Direction.from(row, column);

        //then
        assertThat(direction).isEqualTo(Direction.UP);
    }

    @DisplayName("로우와 컬럼을 통해 방향을 만들때 잘못된 값이면 예외가 발생한다,")
    @Test
    void validateWrongRowOrColumnException() {
        //given
        int row = 0;
        int column = 4;

        //when & then
        assertThatThrownBy(() ->  Direction.from(row, column))
            .isInstanceOf(IllegalStateException.class);
    }

    @ParameterizedTest
    @DisplayName("차이를 통해 양수, 0, 음수를 반환한다.")
    @CsvSource({"3, 4, 1", "d, a, -1", "c, c, 0", "1, 5, 1", "b, e, 1"})
    void getSubtractDirection(final char pre, final char move, final int expected) {
        //when
        int actual = Direction.getSubtractDirection(pre, move);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("해당 방향이 위또는 아래이라면 true를 반환한다.")
    @Test
    void isUpOrDownTrue() {
        //given
        Direction down = Direction.DOWN;

        //when
        boolean result = down.isUpOrDown();

        //then
        assertThat(result).isTrue();
    }

    @DisplayName("해당 방향이 위또는 아래가 아니라면 false를 반환한다.")
    @Test
    void isUpOrDownFalse() {
        //given
        Direction firstDiagonal = Direction.FIRST_DIAGONAL;

        //when
        boolean result = firstDiagonal.isUpOrDown();

        //then
        assertThat(result).isFalse();
    }

    @DisplayName("해당 방향의 로우를 반환한다.")
    @Test
    void getRow() {
        //given
        Direction fourthDiagonalUp = Direction.FOURTH_DIAGONAL_UP;

        //when
        int row = fourthDiagonalUp.getRow();

        //then
        assertThat(row).isEqualTo(-1);
    }

    @DisplayName("해당 방향의 컬럼을 반환한다.")
    @Test
    void getColumn() {
        //given
        Direction fourthDiagonalUp = Direction.FOURTH_DIAGONAL_UP;

        //when
        int row = fourthDiagonalUp.getColumn();

        //then
        assertThat(row).isEqualTo(2);
    }
}
