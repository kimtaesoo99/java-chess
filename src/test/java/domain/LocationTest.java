package domain;

import chess.domain.board.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LocationTest {

    private Location location;

    @BeforeEach
    void setUp() {
        location = Location.from('c', '4');
    }

    @DisplayName("location의 rank명을 반환한다.")
    @Test
    void getRank() {
        //when
        char rank = location.getRank();

        //then
        assertThat(rank == 'c').isTrue();
    }

    @DisplayName("location의 file명을 반환한다.")
    @Test
    void getFile() {
        //when
        char file = location.getFile();

        //then
        assertThat(file == '4').isTrue();
    }

    @DisplayName("location의 위치가 같다면 ture를 반환한다")
    @Test
    void isSameLocationTrue() {
        //given
        Location expected = Location.from('c', '4');

        //when
        boolean isSame = location.equals(expected);

        //then
        assertThat(isSame).isTrue();
    }

    @DisplayName("location의 위치가 다르다면 false를 반환한다")
    @Test
    void isSameLocationFalse() {
        //given
        Location expected = Location.from('c', '5');

        //when
        boolean isSame = location.equals(expected);

        //then
        assertThat(isSame).isFalse();
    }
}
