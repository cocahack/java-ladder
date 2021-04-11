package nextstep.refactoring.ladder;

import nextstep.refactoring.ladder.engine.Line;
import nextstep.refactoring.ladder.engine.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class LadderLineTest {

    @Test
    @DisplayName("최소 두 개의 지점이 있어야 한다.")
    void lineHasPointsAtLeastTwo() {
        assertAll(
            () -> assertThatThrownBy(() -> LadderLine.of(LadderPoint.of(0, Direction.right()))),
            () -> assertThatThrownBy(() -> LadderLine.of(Collections.emptyList()))
        );
    }

    @Test
    @DisplayName("현재 위치에서 연결된 지점이 있는 경우 이동한다.")
    void movePoint() {
        LadderPoint first = LadderPoint.of(0, Direction.right());
        LadderPoint second = first.next();
        Line line = LadderLine.of(first, second);

        assertThat(line.move(Position.of(0))).isEqualTo(Position.of(1));
    }

}