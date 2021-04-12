package nextstep.refactoring.laddergame.engine;

import nextstep.refactoring.ladder.engine.Position;

import java.util.List;

public interface LadderGame<I, O> {

    LadderGameResult<I, O> getResult(LadderCompatible<I> input, LadderCompatible<O> output, Position position);
    List<LadderGameResult<I, O>>  getAllResult(LadderCompatible<I> input, LadderCompatible<O> output);

}
