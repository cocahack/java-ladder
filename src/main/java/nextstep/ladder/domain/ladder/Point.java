package nextstep.ladder.domain.ladder;

import nextstep.ladder.domain.ladder.exception.PointAlreadyConnectedException;

class Point {

    private Point connectedPoint;
    private final Position position;

    Point() {
        this(new Position(0));
    }

    Point(Position position) {
        connectedPoint = this;
        this.position = position;
    }

    void connectTo(Point point) {
        if (connectedPoint != this) {
            throw new PointAlreadyConnectedException();
        }

        connectedPoint = point;
    }

    public boolean isConnectedAnotherPoint() {
        return connectedPoint != this;
    }

    public boolean isConnectedTo(Point point) {
        return connectedPoint == point;
    }

    public Position getPositionOfConnectedPoint() {
        return connectedPoint.position;
    }

    public boolean hasPosition(Position thatPosition) {
        return position.equals(thatPosition);
    }

}
