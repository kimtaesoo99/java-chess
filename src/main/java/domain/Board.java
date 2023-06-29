package domain;

import java.util.Map;

import static domain.Error.*;

public class Board {

    private static final int FILE_MAX = 8;
    private static final int FILE_INIT = 1;
    private static final int RANK_MAX = 7;
    private static final int RANK_INIT = 0;
    private static final String EMPTY = ".";
    private static final char FIRST_RANK = 'a';
    private static final char FIRST_FILE = '0';

    private final Map<Location, Pieces> board;

    public Board(final Map<Location, Pieces> board) {
        this.board = board;
    }

    public String getBoardStatus() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int file = FILE_MAX; file >= FILE_INIT; file--) {
            findBoardStatus(file, stringBuilder);
        }
        return stringBuilder.toString();
    }

    private void findBoardStatus(final int file, final StringBuilder stringBuilder) {
        for (int rank = RANK_INIT; rank <= RANK_MAX; rank++) {
            Location location = Location.from((char) (FIRST_RANK + rank), (char) (FIRST_FILE + file));
            addBoardStatus(location, stringBuilder);
        }
    }

    private void addBoardStatus(final Location location, final StringBuilder stringBuilder) {
        if (board.containsKey(location)) {
            stringBuilder.append(board.get(location).getName());
            return;
        }
        stringBuilder.append(EMPTY);
    }

    public void movePieces(final Location preLocation, final Location moveLocation) {
        validationMovePieces(preLocation, moveLocation);
        move(preLocation, moveLocation);
    }

    private void validationMovePieces(final Location preLocation, final Location moveLocation) {
        checkExistPiecesInLocation(preLocation, moveLocation);
        checkCanMovePiecesInLocation(preLocation, moveLocation);
        if (board.get(preLocation).isPawn()) {
            checkPawnMove(preLocation, moveLocation);
            return;
        }
        checkExistPiecesInRoute(preLocation, moveLocation);
    }

    private void checkExistPiecesInLocation(final Location preLocation, final Location moveLocation) {
        if (!board.containsKey(preLocation)) {
            throw new IllegalStateException(HAS_NOT_PIECES.getMessage());
        }

        if (board.containsKey(moveLocation) && isSameColor(preLocation, moveLocation)) {
            throw new IllegalStateException(MOVE_LOCATION_IS_SAME_COLOR.getMessage());
        }
    }

    private boolean isSameColor(final Location preLocation, final Location moveLocation) {
        Pieces piecesInPreIndex = board.get(preLocation);
        Pieces piecesInMoveIndex = board.get(moveLocation);
        return piecesInPreIndex.isBlack() == piecesInMoveIndex.isBlack();
    }

    private void checkCanMovePiecesInLocation(final Location preLocation, final Location moveLocation) {
        if (!board.get(preLocation).canMove(preLocation.getRank(), preLocation.getFile(), moveLocation)) {
            throw new IllegalStateException(CAN_NOT_MOVE_RANGE.getMessage());
        }

        if (preLocation.equals(moveLocation)) {
            throw new IllegalStateException(DO_NOT_STAY.getMessage());
        }
    }

    private void checkPawnMove(final Location preLocation, final Location moveLocation) {
        Direction direction = findDirection(preLocation, moveLocation);
        if (direction.isUpOrDown()) {
            checkRoute(direction, preLocation, moveLocation);
            return;
        }

        if (!board.containsKey(moveLocation) || isSameColor(preLocation, moveLocation)) {
            throw new IllegalStateException(PAWN_MOVE_DIAGONALLY_WITH_ENEMY.getMessage());
        }
    }

    private void checkExistPiecesInRoute(final Location preLocation, final Location moveLocation) {
        if (board.get(preLocation).isKnight()) {
            return;
        }
        Direction direction = findDirection(preLocation, moveLocation);
        checkRoute(direction, preLocation, moveLocation);
    }

    private Direction findDirection(final Location preLocation, final Location moveLocation) {
        int rowDirection = Direction.getSubtractDirection(preLocation.getRank(), moveLocation.getRank());
        int columnDirection = Direction.getSubtractDirection(preLocation.getFile(), moveLocation.getFile());

        return Direction.from(rowDirection, columnDirection);
    }

    private void checkRoute(final Direction direction, final Location preLocation, final Location moveLocation) {
        char nowRank = (char) (preLocation.getRank() + direction.getRow());
        char nowFile = (char) (preLocation.getFile() + direction.getColumn());

        while (!moveLocation.isSameLocation(nowRank, nowFile)) {
            checkExistPiecesInLocation(nowRank, nowFile);
            nowRank = (char) (nowRank + direction.getRow());
            nowFile = (char) (nowFile + direction.getColumn());
        }
    }

    private void checkExistPiecesInLocation(final char nowRank, final char nowFile) {
        Location nowLocation = Location.from(nowRank, nowFile);
        if (board.containsKey(nowLocation)) {
            throw new IllegalStateException(CAN_NOT_MOVE_BECAUSE_OBSTACLE.getMessage());
        }
    }

    private void move(final Location preLocation, final Location moveLocation) {
        Pieces pieces = board.get(preLocation);
        board.remove(preLocation);
        board.put(moveLocation, pieces);
    }
}

