package org.dyson.core.state.j;

import java.util.Map;
import java.util.function.BiFunction;

public interface IState<STATE, EVENT, SIDE_EFFECT> {
    StateMachine.Transition getTransition(EVENT event);

    Graph.State<STATE, EVENT, SIDE_EFFECT> getDefinition();

    void notifyOnEnter(Object cause);

    ;

    void notifyOnExit(Object cause);
}
