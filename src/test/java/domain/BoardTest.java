package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.InitBoard;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class BoardTest {

    private Board board;

    @BeforeEach
    void init() {
        InitBoard initBoard = new InitBoard();
        Map<Location, Pieces> boards = initBoard.getInitBoard();
        board = new Board(boards);
    }

    @DisplayName("보드의 현재 상황을 가져올시 64개가 나와야한다.")
    @Test
    void getBoardStatus() {
        //when
        String boardStatus = board.getBoardStatus();

        //then
        assertThat(boardStatus.length()).isEqualTo(64);
    }

    @DisplayName("폰은 처음 2칸을 이동할 수 있다.")
    @Test
    void movePawn() {
        //given
        Location preLocation = Location.from('b', '2');
        Location moveLocation = Location.from('b', '4');

        //when
        board.movePieces(preLocation, moveLocation);

        //then
        String boardStatus = board.getBoardStatus();

        assertAll(
            () -> assertThat(boardStatus.charAt(33)).isEqualTo('p'),
            () -> assertThat(boardStatus.charAt(49)).isEqualTo('.')
        );
    }

    @DisplayName("폰은 대각선에 적이있으면 대각선으로 이동할 수 있다.")
    @Test
    void movePawnWithEnemy() {
        //given
        Location preLocation = Location.from('b', '2');
        Location moveLocation = Location.from('b', '4');
        board.movePieces(preLocation, moveLocation);

        Location preLocationBlack = Location.from('c', '7');
        Location moveLocationBlack = Location.from('c', '5');
        board.movePieces(preLocationBlack, moveLocationBlack);

        Location preLocationWhite = Location.from('b', '4');
        Location moveLocationWhite = Location.from('c', '5');

        //when
        board.movePieces(preLocationWhite, moveLocationWhite);

        //then
        String boardStatus = board.getBoardStatus();

        assertThat(boardStatus.charAt(26)).isEqualTo('p');
    }

    @DisplayName("폰의 이동범위를 벗어난 경우 예외가 발생한다.")
    @Test
    void movePawnException() {
        //given
        Location preLocation = Location.from('b', '2');
        Location moveLocation = Location.from('c', '4');

        //when & then
        assertThatThrownBy(() -> board.movePieces(preLocation, moveLocation))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("나이트는 처음 기물을 넘어서 이동할 수 있다.")
    @Test
    void moveKnight() {
        //given
        Location preLocation = Location.from('b', '1');
        Location moveLocation = Location.from('c', '3');

        //when
        board.movePieces(preLocation, moveLocation);

        //then
        String boardStatus = board.getBoardStatus();
        assertAll(
            () -> assertThat(boardStatus.charAt(42)).isEqualTo('n'),
            () -> assertThat(boardStatus.charAt(57)).isEqualTo('.')
        );
    }

    @DisplayName("나이트의 이동범위를 벗어난 경우 예외가 발생한다.")
    @Test
    void moveKnightException() {
        //given
        Location preLocation = Location.from('b', '1');
        Location moveLocation = Location.from('b', '3');

        //when & then
        assertThatThrownBy(() -> board.movePieces(preLocation, moveLocation))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("룩 앞에 기물이 있으면 이동할 수 없다.")
    @Test
    void moveRockException() {
        //given
        Location preLocation = Location.from('a', '1');
        Location moveLocation = Location.from('a', '3');

        //when & then
        assertThatThrownBy(() -> board.movePieces(preLocation, moveLocation))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("비숍 앞에 기물이 있으면 이동할 수 없다.")
    @Test
    void moveBishopException() {
        //given
        Location preLocation = Location.from('c', '1');
        Location moveLocation = Location.from('e', '3');

        //when & then
        assertThatThrownBy(() -> board.movePieces(preLocation, moveLocation))
            .isInstanceOf(IllegalStateException.class);
    }
}
