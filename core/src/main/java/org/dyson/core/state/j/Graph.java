package org.dyson.core.state.j;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.dyson.core.state.j.StateMachine.Transition;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.*;


@Data
@AllArgsConstructor
class Graph<STATE, EVENT, SIDE_EFFECT> {
    STATE initialState;
    Map<Matcher<STATE, STATE>, State<STATE, EVENT, SIDE_EFFECT>> stateDefinitions;
    List<Consumer<Transition>> onTransitionListeners;

    public Graph copy(STATE state) {
        return new Graph(state, this.stateDefinitions, this.onTransitionListeners);
    }


    static class State<S, E, SIDE_EFFECT> {
        List<BiConsumer<S, E>> onEnterListeners = new ArrayList<>();
        List<BiConsumer<S, E>> onExitListeners = new ArrayList<>();

        LinkedHashMap<Matcher<E, E>, BiFunction<S, E, TransitionTo<S, SIDE_EFFECT>>> transitions = new LinkedHashMap();

        static class TransitionTo<S, SIDE_EFFECT> {
            S toState;
            SIDE_EFFECT sideEffect;
        }
    }


}

