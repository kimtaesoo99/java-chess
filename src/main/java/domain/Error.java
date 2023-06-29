package domain;

public enum Error {

    WRONG_INPUT("잘못된 입력입니다."),
    HAS_NOT_PIECES("해당 위치에는 기물이 없습니다."),
    MOVE_LOCATION_IS_SAME_COLOR("이동하려는 위치에는 같은 색깔의 기물이 있습니다."),
    CAN_NOT_MOVE_RANGE("기물이 움직일 수 있는 범위가 아닙니다."),
    DO_NOT_STAY("제자리로 움직일 수 없습니다."),
    PAWN_MOVE_DIAGONALLY_WITH_ENEMY("폰이 대각선으로 움직이려면 적군이 있어야합니다."),
    CAN_NOT_MOVE_BECAUSE_OBSTACLE("동선에 기물이 있어서 움직일 수 없습니다."),
    WRONG_ROW_OR_COLUMN("잘못된 row와 column입니다."),
    WRONG_FILE_NAME("잘못된 File의 이름입니다."),
    WRONG_RANK_NAME("잘못된 rank의 이름입니다."),
    CAN_START_WITH_START_COMMAND("게임을 시작하려면 start를 입력해주세요"),
    EARLY_START_GAME("이미 게임이 진행중입니다."),
    WRONG_MOVE_COMMAND("잘못된 이동정보입니다.");

    private final String message;

    Error(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
