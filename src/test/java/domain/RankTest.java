package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RankTest {

    @ParameterizedTest
    @DisplayName("매개변수로 들어온 문자에 해당하는 rank를 반환한다.")
    @CsvSource({"A, a", "B, b", "C, c", "D, d", "E, e",
        "F, f", "G, g", "H, h"})
    void getRank(Rank expected, char index) {
        //when
        Rank actual = Rank.getRank(index);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("enum 타입에 맞는 이름을 반환한다.")
    @CsvSource({"A, a", "B, b", "C, c", "D, d", "E, e",
        "F, f", "G, g", "H, h"})
    void getName(Rank rank, char expected) {
        //when
        char actual = rank.getName();

        //then
        assertThat(actual == expected).isTrue();
    }

    @DisplayName("해당 Rank의 이름이 매개변수로 들어온 이름과 같다면 ture를 반환")
    @Test
    void isSameNameTrue() {
        //given
        char a = 'a';

        //when
        boolean isSame = Rank.A.isSameRank(a);

        //then
        assertThat(isSame).isTrue();
    }

    @DisplayName("해당 Rank의 이름이 매개변수로 들어온 이름과 다르면 false를 반환")
    @Test
    void isSameNameFalse() {
        //given
        char b = 'b';

        //when
        boolean isSame = Rank.A.isSameRank(b);

        //then
        assertThat(isSame).isFalse();
    }

    @DisplayName("잘못된 rank이름으로 생성하려하면 예외가 발생한다.")
    @Test
    void validateWrongName() {
        //given
        char name = 'i';

        //when & then
        assertThatThrownBy(() -> Rank.getRank(name))
            .isInstanceOf(IllegalStateException.class);
    }
}
