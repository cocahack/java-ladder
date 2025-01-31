package nextstep.ladder.domain.ladder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class PointTest {

    @Test
    @DisplayName("최초에는 어느 지점과도 연결되지 않는다.")
    void shouldNotConnectAnyPointOriginally() {
        Point point = Point.of(0);

        assertThat(point.isConnectedAnotherPoint()).isFalse();
    }

    @Test
    @DisplayName("다른 지점과 연결할 수 있다.")
    void connectToAnotherPoint() {
        Point leftPoint = Point.of(0);
        Point rightPoint = Point.of(1);

        leftPoint.connectTo(rightPoint);
        rightPoint.connectTo(leftPoint);

        assertAll(
            () -> assertThat(leftPoint.isConnectedAnotherPoint()).isTrue(),
            () -> assertThat(rightPoint.isConnectedAnotherPoint()).isTrue()
        );
    }

    @Test
    @DisplayName("이미 연결된 지점을 다른 지점과 다시 연결할 수 없다.")
    void shouldNotConnectIfPointAlreadyConnect() {
        Point leftPoint = Point.of(0);
        Point rightPoint = Point.of(1);
        Point otherPoint = Point.of(2);

        leftPoint.connectTo(rightPoint);
        rightPoint.connectTo(leftPoint);

        assertAll(
            () -> assertThatThrownBy(() -> leftPoint.connectTo(otherPoint))
                .isInstanceOf(RuntimeException.class),
            () -> assertThatThrownBy(() -> rightPoint.connectTo(otherPoint))
                .isInstanceOf(RuntimeException.class)
        );
    }

}
