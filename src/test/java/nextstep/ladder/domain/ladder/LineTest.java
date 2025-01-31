package nextstep.ladder.domain.ladder;

import nextstep.ladder.dto.Connections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class LineTest {

    static List<Point> oneWayConnectedPoints() {
        List<Point> points = separatedPoints(4);

        points.get(3)
              .connectTo(points.get(2));

        return points;
    }

    static List<Point> separatedPoints(int numberOfPoints) {
        return IntStream.range(0, numberOfPoints)
                        .mapToObj(Point::of)
                        .collect(Collectors.toList());
    }

    @Test
    @DisplayName("라인이 가진 지점이 너무 적으면 예외 처리한다.")
    void throwExceptionIfLineHasFewPoints() {
        List<Point> emptyPointList = Collections.emptyList();
        List<Point> singlePointList = Collections.singletonList(Point.of(0));

        assertAll(
            () -> assertThatThrownBy(() -> new Line(emptyPointList)).isInstanceOf(RuntimeException.class),
            () -> assertThatThrownBy(() -> new Line(singlePointList)).isInstanceOf(RuntimeException.class)
        );
    }

    @Test
    @DisplayName("단방향 연결된 지점이 있으면 예외 처리한다.")
    void throwExceptionLineHasOnyWayConnectedPoints() {
        List<Point> points = oneWayConnectedPoints();

        assertThatThrownBy(() -> new Line(points)).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("지점 간 연결 정보를 담은 객체를 추출한다.")
    void extractConnection() {
        List<Point> points = separatedPoints(8);
        points.get(1).connectTo(points.get(2));
        points.get(2).connectTo(points.get(1));

        points.get(4).connectTo(points.get(5));
        points.get(5).connectTo(points.get(4));

        Line line = new Line(points);

        Connections connections = new Connections(
            Arrays.asList(false, true, false, false, true, false, false)
        );

        assertThat(line.exportConnections()).isEqualToComparingFieldByField(connections);
    }

    @Test
    @DisplayName("연결된 지점을 횡단한다. 연결되지 않은 지점은 그대로 통과한다.")
    void traversePoints() {
        List<Point> points = separatedPoints(4);
        points.get(1).connectTo(points.get(2));
        points.get(2).connectTo(points.get(1));

        Line line = new Line(points);

        assertAll(
            () -> assertThat(line.traverse(Lane.wrap(0))).isEqualTo(Lane.wrap(0)),
            () -> assertThat(line.traverse(Lane.wrap(1))).isEqualTo(Lane.wrap(2)),
            () -> assertThat(line.traverse(Lane.wrap(2))).isEqualTo(Lane.wrap(1)),
            () -> assertThat(line.traverse(Lane.wrap(3))).isEqualTo(Lane.wrap(3))
        );
    }

}
