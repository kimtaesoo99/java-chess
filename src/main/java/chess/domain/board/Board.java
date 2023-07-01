package chess.domain.board;

import chess.domain.additional.Direction;
import chess.domain.pieces.Pieces;

import java.util.Collections;
import java.util.Map;

import static chess.domain.additional.Error.CAN_NOT_MOVE_BECAUSE_OBSTACLE;
import static chess.domain.additional.Error.CAN_NOT_MOVE_RANGE;
import static chess.domain.additional.Error.DO_NOT_STAY;
import static chess.domain.additional.Error.HAS_NOT_PIECES;
import static chess.domain.additional.Error.MOVE_LOCATION_IS_SAME_COLOR;
import static chess.domain.additional.Error.PAWN_MOVE_DIAGONALLY_WITH_ENEMY;
import static chess.domain.additional.Error.WRONG_TURN;

public class Board {

    private final Map<Location, Pieces> board;

    public Board(final Map<Location, Pieces> board) {
        this.board = board;
    }

    public void movePieces(final Location preLocation, final Location moveLocation, final State turn) {
        validationMovePieces(preLocation, moveLocation, turn);
        move(preLocation, moveLocation);
    }

    private void validationMovePieces(final Location preLocation, final Location moveLocation, final State turn) {
        checkExistPiecesInLocation(preLocation, moveLocation);
        validationTurn(preLocation, turn);
        checkCanMovePiecesInLocation(preLocation, moveLocation);

        if (board.get(preLocation).isPawn()) {
            checkPawnMove(preLocation, moveLocation);
            return;
        }
        checkExistPiecesInRoute(preLocation, moveLocation);
    }

    private void validationTurn(final Location preLocation, final State turn) {
        if (!board.get(preLocation).isSameColor(turn)) {
            throw new IllegalStateException(WRONG_TURN.getMessage());
        }
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

        return piecesInPreIndex.isSameColor(piecesInMoveIndex);
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

    public Map<Location, Pieces> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}

