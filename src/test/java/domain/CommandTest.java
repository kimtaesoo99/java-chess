package domain;

import chess.domain.additional.Command;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandTest {

    @DisplayName("입력값을 통해 start Command를 만든다")
    @Test
    void createStartCommand() {
        //given
        String input = "start";

        //when2
        Command command = Command.createStartCommand(input);

        //then
        assertThat(command).isEqualTo(Command.START);
    }

    @DisplayName("입력값을 통해 end Command를 만든다")
    @Test
    void createEndCommand() {
        //given
        String input = "end";

        //when2
        Command command = Command.createCommand(input);

        //then
        assertThat(command).isEqualTo(Command.END);
    }

    @DisplayName("입력값을 통해 move Command를 만든다")
    @Test
    void createMoveCommand() {
        //given
        String input = "move a2 d3";

        //when2
        Command command = Command.createCommand(input);

        //then
        assertThat(command).isEqualTo(Command.MOVE);
    }

    @DisplayName("게임이 진행중일때 start를 입력하면 예외가 발생한다.")
    @Test
    void createCommandException() {
        //given
        String input = "start";

        //when & then
        assertThatThrownBy(() -> Command.createCommand(input))
            .isInstanceOf(IllegalStateException.class);
    }

    @ParameterizedTest
    @DisplayName("잘못된 이동정보를 주면 예외가 발생한다.")
    @CsvSource({"moveasd", "movesd sad", "move vc s", "move"})
    void createCommandException(final String input) {
        //when & then
        assertThatThrownBy(() -> Command.createCommand(input))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("입력값을 통해 Command를 만들때 잘못된 값이면 예외가 발생한다.")
    @Test
    void validateWrongInputException() {
        //given
        String input = "error";

        //when & then
        assertThatThrownBy(() -> Command.createStartCommand(input))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("해당 Command가 start인지 반환한다.")
    @Test
    void isStart() {
        //given
        Command command = Command.START;

        //when
        boolean isStart = command.isStart();

        //then
        assertThat(isStart).isTrue();
    }

    @DisplayName("해당 Command가 end인지 반환한다.")
    @Test
    void isEnd() {
        //given
        Command command = Command.END;

        //when
        boolean isEnd = command.isEnd();

        //then
        assertThat(isEnd).isTrue();
    }

    @DisplayName("해당 Command가 move인지 반환한다.")
    @Test
    void isMove() {
        //given
        Command command = Command.MOVE;

        //when
        boolean isMove = command.isMove();

        //then
        assertThat(isMove).isTrue();
    }
}
