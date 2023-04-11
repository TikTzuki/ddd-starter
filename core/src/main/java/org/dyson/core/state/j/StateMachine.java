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


public class StateMachine<STATE extends IState, EVENT, SIDE_EFFECT> {
    private Graph<STATE, EVENT, SIDE_EFFECT> graph;
    private AtomicReference<STATE> stateRef;

    public STATE getState() {
        return stateRef.get();
    }

    private StateMachine(Graph<STATE, EVENT, SIDE_EFFECT> graph) {
        this.graph = graph;
    }


    public Transition transition(EVENT event) {
        synchronized (this) {
            STATE fromState = stateRef.get();
            Transition transition = fromState.getTransition(event);
            if (transition instanceof Valid) {
                stateRef.set(((Valid) transition).toState);
            }
            transition.notifyOnTransition();
            if (transition instanceof Valid) {
                Valid validTransition = (Valid) transition;
                validTransition.getFromState().notifyOnExit(event);
                validTransition.getToState().notifyOnEnter(event);
            }
            return transition;
        }
    }

    public StateMachine<STATE, EVENT, SIDE_EFFECT> with(Consumer<GraphBuilder<STATE, EVENT, SIDE_EFFECT>> init) {
        return create(graph.copy(getState()), init);
    }

    public static <STATE extends StateMachine.SimpleState, EVENT, SIDE_EFFECT> StateMachine<STATE, EVENT, SIDE_EFFECT> create(Consumer<GraphBuilder<STATE, EVENT, SIDE_EFFECT>> init) {
        return create(null, init);
    }

    public static <STATE extends StateMachine.SimpleState, EVENT, SIDE_EFFECT> StateMachine<STATE, EVENT, SIDE_EFFECT> create(
            Graph<STATE, EVENT, SIDE_EFFECT> graph,
            Consumer<GraphBuilder<STATE, EVENT, SIDE_EFFECT>> init
    ) {
        return new StateMachine(new GraphBuilder(graph).apply(init).build());
    }


    @AllArgsConstructor
    @Data
    public class Transition {
        public STATE fromState;
        public EVENT event;

        public void notifyOnTransition() {
            graph.onTransitionListeners.forEach(it -> it.accept(this));
        }


    }

    public class Valid extends Transition {


        public STATE toState;
        public SIDE_EFFECT sideEffect;

        public Valid(STATE fromState, EVENT event, STATE toState, SIDE_EFFECT sideEffect) {
            super(fromState, event);
            this.toState = toState;
            this.sideEffect = sideEffect;
        }

        public STATE getToState() {
            return toState;
        }

        public void setToState(STATE toState) {
            this.toState = toState;
        }

        public SIDE_EFFECT getSideEffect() {
            return sideEffect;
        }

        public void setSideEffect(SIDE_EFFECT sideEffect) {
            this.sideEffect = sideEffect;
        }
    }

    public class Invalid extends Transition {
        public Invalid(STATE fromState, EVENT event) {
            super(fromState, event);
        }

    }


    class SimpleState implements IState {

        @Override
        public Transition getTransition(Object event) {
            for (Map.Entry<Matcher<EVENT, EVENT>, BiFunction<STATE, EVENT, Graph.State.TransitionTo<STATE, SIDE_EFFECT>>> entry : getDefinition().transitions.entrySet()) {
                if (entry.getKey().matches((EVENT) event)) {
                    var x = entry.getValue().apply((STATE) this, (EVENT) event);
                    new StateMachine.Valid((STATE) this, event, x.toState, x.sideEffect);
                }
            }
            return new StateMachine.Invalid((STATE) this, event);
        }

        public Graph.State<STATE, EVENT, SIDE_EFFECT> getDefinition() {
            return graph.stateDefinitions.entrySet().stream()
                    .filter(it -> it.getKey().matches((STATE) this))
                    .map(Map.Entry::getValue)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(String.format("Missing definition for state %s", this.getClass().getSimpleName())));
        }

        public void notifyOnEnter(Object cause) {
            getDefinition().onEnterListeners.forEach(it -> it.accept((STATE) this, (EVENT) cause));
        }

        ;

        public void notifyOnExit(Object cause) {
            getDefinition().onExitListeners.forEach(it -> it.accept((STATE) this, (EVENT) cause));
        }
    }
}

