package service;

import chess.domain.board.Location;
import chess.domain.pieces.Pieces;
import chess.service.InitBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class InitBoardTest {

    @DisplayName("보드를 초기화할경우 기물이 32개가 설정된다.")
    @Test
    void getInitBoard() {
        //given
        InitBoard initBoard = new InitBoard();

        //when
        Map<Location, Pieces> board = initBoard.getInitBoard();

        //then
        assertThat(board.size()).isEqualTo(32);
    }
}
