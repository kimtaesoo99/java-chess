package domain;

import chess.domain.board.File;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FileTest {

    @ParameterizedTest
    @DisplayName("매개변수로 들어온 숫자에 해당하는 file을 반환한다.")
    @CsvSource({"ONE, 1", "TWO, 2", "THREE, 3", "FOUR, 4", "FIVE, 5",
        "SIX, 6", "SEVEN, 7", "EIGHT, 8"})
    void getFile(final File expected, final char index) {
        //when
        File actual = File.getFile(index);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("enum 타입에 맞는 이름을 반환한다.")
    @CsvSource({"ONE, 1", "TWO, 2", "THREE, 3", "FOUR, 4", "FIVE, 5",
        "SIX, 6", "SEVEN, 7", "EIGHT, 8"})
    void getName2(final File file, final char expected) {
        //when
        char actual = file.getName();

        //then
        assertThat(actual == expected).isTrue();
    }

    @DisplayName("해당 File의 이름이 매개변수로 들어온 이름과 같다면 ture를 반환")
    @Test
    void isSameNameTrue() {
        //given
        char six = '6';

        //when
        boolean isSame = File.SIX.isSameFile(six);

        //then
        assertThat(isSame).isTrue();
    }

    @DisplayName("해당 File의 이름이 매개변수로 들어온 이름과 다르면 false를 반환")
    @Test
    void isSameNameFalse() {
        //given
        char four = '4';

        //when
        boolean isSame = File.SIX.isSameFile(four);

        //then
        assertThat(isSame).isFalse();
    }

    @DisplayName("잘못된 file이름으로 생성하려하면 예외가 발생한다.")
    @Test
    void validateWrongName() {
        //given
        char name = '9';

        //when & then
        assertThatThrownBy(() -> File.getFile(name))
            .isInstanceOf(IllegalStateException.class);
    }
}
