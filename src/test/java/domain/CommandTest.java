package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandTest {

    @DisplayName("입력값을 통해 Command를 만든다")
    @Test
    void createCommand() {
        //given
        String input = "start";

        //when
        Command command = Command.from(input);

        //then
        assertThat(command).isEqualTo(Command.START);
    }

    @DisplayName("입력값을 통해 Command를 만들때 잘못된 값이면 예외가 발생한다.")
    @Test
    void validateWrongInputException() {
        //given
        String input = "error";

        //when & then
        assertThatThrownBy(() -> Command.from(input))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("입력값을 통해 올바른 Command라면 true를 반환한다")
    @Test
    void isCorrectCommandTrue() {
        //given
        String input = "end";

        //when
        boolean isCorrect = Command.isCorrectCommand(input);

        //then
        assertThat(isCorrect).isTrue();
    }

    @DisplayName("입력값을 통해 올바른 Command가 아니면 false를 반환한다")
    @Test
    void isCorrectCommandFalse() {
        //given
        String input = "error";

        //when
        boolean isCorrect = Command.isCorrectCommand(input);

        //then
        assertThat(isCorrect).isFalse();
    }

    @DisplayName("해당 input이 start인지 반환한다")
    @Test
    void isStartCommand() {
        //given
        String input = "start";

        //when
        boolean isStart = Command.isStartCommand(input);

        //then
        assertThat(isStart).isTrue();
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
