package domain;

import chess.domain.board.Board;
import chess.domain.board.Location;
import chess.domain.board.State;
import chess.domain.pieces.Pieces;
import chess.service.InitBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BoardTest {

    private Board board;

    @BeforeEach
    void init() {
        InitBoard initBoard = new InitBoard();
        Map<Location, Pieces> boards = initBoard.getInitBoard();
        board = new Board(boards);
    }

    @DisplayName("보드의 현재 상황을 가져올시 32개가 초기화되어있다.")
    @Test
    void getBoardStatus() {
        //when
        Map<Location, Pieces> boardStatus = board.getBoard();

        //then
        assertThat(boardStatus.size()).isEqualTo(32);
    }

    @DisplayName("해당 위치에 기물이 없다면 예외가 발생한다.")
    @Test
    void hasNotPiecesException() {
        //given
        Location preLocation = Location.from('c', '3');
        Location moveLocation = Location.from('c', '4');
        State state = State.WHITE_TEAM;
        String expectedMessage = "해당 위치에는 기물이 없습니다.";

        //when
        Exception exception = assertThrows(IllegalStateException.class, () -> board.movePieces(preLocation, moveLocation, state));

        //then
        String actualMessage = exception.getMessage();
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @DisplayName("폰은 처음 2칸을 이동할 수 있다.")
    @Test
    void movePawn() {
        //given
        Location preLocation = Location.from('b', '2');
        Location moveLocation = Location.from('b', '4');
        State state = State.WHITE_TEAM;

        //when
        board.movePieces(preLocation, moveLocation, state);

        //then
        Map<Location, Pieces> status = board.getBoard();

        assertAll(
            () -> assertThat(status.get(preLocation)).isNull(),
            () -> assertThat(status.get(moveLocation).getName()).isEqualTo("p")
        );
    }

    @DisplayName("폰은 대각선에 적이있으면 대각선으로 이동할 수 있다.")
    @Test
    void movePawnWithEnemy() {
        //given
        Location preLocation = Location.from('b', '2');
        Location moveLocation = Location.from('b', '4');
        State state = State.WHITE_TEAM;
        board.movePieces(preLocation, moveLocation, state);

        Location preLocationBlack = Location.from('c', '7');
        Location moveLocationBlack = Location.from('c', '5');
        State state2 = State.BLACK_TEAM;
        board.movePieces(preLocationBlack, moveLocationBlack, state2);

        Location preLocationWhite = Location.from('b', '4');
        Location moveLocationWhite = Location.from('c', '5');
        State state3 = State.WHITE_TEAM;

        //when
        board.movePieces(preLocationWhite, moveLocationWhite, state3);

        //then
        Map<Location, Pieces> status = board.getBoard();

        assertThat(status.get(moveLocationWhite).getName()).isEqualTo("p");
    }

    @DisplayName("폰은 대각선에 적이없을때 대각선으로 이동하면 예외가 발생한다.")
    @Test
    void movePawnWithEnemyException() {
        //given
        Location preLocation = Location.from('b', '2');
        Location moveLocation = Location.from('c', '3');
        State state = State.WHITE_TEAM;
        String expectedMessage = "폰이 대각선으로 움직이려면 적군이 있어야합니다.";

        //when
        Exception exception = assertThrows(IllegalStateException.class, () -> board.movePieces(preLocation, moveLocation, state));

        //then
        String actualMessage = exception.getMessage();
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @DisplayName("이동하려는 위치에는 같은 색깔의 기물이 있으면 예외가 발생.")
    @Test
    void moveStayException() {
        //given
        Location preLocation = Location.from('c', '2');
        Location moveLocation = Location.from('c', '3');
        State state = State.WHITE_TEAM;
        String expectedMessage = "이동하려는 위치에는 같은 색깔의 기물이 있습니다.";

        Location preLocation2 = Location.from('b', '1');
        Location moveLocation2 = Location.from('c', '3');
        State state2 = State.WHITE_TEAM;

        //when
        board.movePieces(preLocation, moveLocation, state);

        //when
        Exception exception = assertThrows(IllegalStateException.class, () -> board.movePieces(preLocation2, moveLocation2, state2));

        //then
        String actualMessage = exception.getMessage();
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @DisplayName("폰의 이동범위를 벗어난 경우 예외가 발생한다.")
    @Test
    void movePawnException() {
        //given
        Location preLocation = Location.from('b', '2');
        Location moveLocation = Location.from('c', '4');
        State state = State.WHITE_TEAM;

        String expectedMessage = "기물이 움직일 수 있는 범위가 아닙니다.";

        //when
        Exception exception = assertThrows(IllegalStateException.class, () -> board.movePieces(preLocation, moveLocation, state));

        //then
        String actualMessage = exception.getMessage();
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @DisplayName("나이트는 처음 기물을 넘어서 이동할 수 있다.")
    @Test
    void moveKnight() {
        //given
        Location preLocation = Location.from('b', '1');
        Location moveLocation = Location.from('c', '3');
        State state = State.WHITE_TEAM;

        //when
        board.movePieces(preLocation, moveLocation, state);

        //then
        Map<Location, Pieces> status = board.getBoard();
        assertAll(
            () -> assertThat(status.get(moveLocation).getName()).isEqualTo("n"),
            () -> assertThat(status.get(preLocation)).isNull()
        );
    }

    @DisplayName("나이트의 이동범위를 벗어난 경우 예외가 발생한다.")
    @Test
    void moveKnightException() {
        //given
        Location preLocation = Location.from('b', '1');
        Location moveLocation = Location.from('b', '3');
        State state = State.WHITE_TEAM;
        String expectedMessage = "기물이 움직일 수 있는 범위가 아닙니다.";

        //when
        Exception exception = assertThrows(IllegalStateException.class, () -> board.movePieces(preLocation, moveLocation, state));

        //then
        String actualMessage = exception.getMessage();
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @DisplayName("룩 앞에 기물이 있으면 이동할 수 없다.")
    @Test
    void moveRockException() {
        //given
        Location preLocation = Location.from('a', '1');
        Location moveLocation = Location.from('a', '3');
        State state = State.WHITE_TEAM;
        String expectedMessage = "동선에 기물이 있어서 움직일 수 없습니다.";

        //when
        Exception exception = assertThrows(IllegalStateException.class, () -> board.movePieces(preLocation, moveLocation, state));

        //then
        String actualMessage = exception.getMessage();
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @DisplayName("비숍 앞에 기물이 있으면 이동할 수 없다.")
    @Test
    void moveBishopException() {
        //given
        Location preLocation = Location.from('c', '1');
        Location moveLocation = Location.from('e', '3');
        State state = State.WHITE_TEAM;
        String expectedMessage = "동선에 기물이 있어서 움직일 수 없습니다.";

        //when
        Exception exception = assertThrows(IllegalStateException.class, () -> board.movePieces(preLocation, moveLocation, state));

        //then
        String actualMessage = exception.getMessage();
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }
}
