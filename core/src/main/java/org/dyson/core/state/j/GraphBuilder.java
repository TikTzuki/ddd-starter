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
public class GraphBuilder<STATE, EVENT, SIDE_EFFECT> {
    private STATE initialState;
    private final Map<Matcher<STATE, STATE>, Graph.State<STATE, EVENT, SIDE_EFFECT>> stateDefinitions = new LinkedHashMap<>();
    private final List<Consumer<StateMachine.Transition>> onTransitionListeners = new ArrayList<>();

    public GraphBuilder(Graph<STATE, EVENT, SIDE_EFFECT> graph) {
        initialState = graph.getInitialState();
        stateDefinitions.putAll(graph.getStateDefinitions());
        onTransitionListeners.addAll(graph.getOnTransitionListeners());
    }

    public void initialState(STATE initialState) {
        this.initialState = initialState;
    }

    public <S extends STATE> void state(Matcher<STATE, STATE> stateMatcher, StateDefinitionBuilder<STATE> init) {
        stateDefinitions.put(stateMatcher, init.build());
    }

    public <S extends STATE> void state(StateDefinitionBuilder<STATE> init) {
        state(Matcher.any(), init);
    }

    public <S extends STATE> void state(STATE state, StateDefinitionBuilder<STATE> init) {
        state(Matcher.eq(state), init);
    }

    public void onTransition(Consumer<Transition> listener) {
        onTransitionListeners.add(listener);
    }

    public Graph<STATE, EVENT, SIDE_EFFECT> build() {
        return new Graph<>(initialState, new LinkedHashMap<>(stateDefinitions), new ArrayList<>(onTransitionListeners));
    }

    public GraphBuilder<STATE, EVENT, SIDE_EFFECT> apply(Consumer<GraphBuilder> block) {
        block.accept(this);
        return this;
    }

    public class StateDefinitionBuilder<S extends STATE> {
        private Graph.State<STATE, EVENT, SIDE_EFFECT> stateDefinition = new Graph.State<>();

        public <E extends EVENT> Matcher<EVENT, E> any() {
            return Matcher.any();
        }

        public <R extends EVENT> Matcher<EVENT, R> eq(R value) {
            return Matcher.eq(value);
        }

        public <E extends EVENT> void on(
                Matcher<EVENT, E> eventMatcher,
                BiFunction<S, E, Graph.State.TransitionTo<STATE, SIDE_EFFECT>> createTransitionTo
        ) {
            stateDefinition.transitions.put((Matcher<EVENT, EVENT>) eventMatcher, (state, event) ->
                    createTransitionTo.apply((S) state, (E) event));
        }

        public <E extends EVENT> void on(BiFunction<S, E, Graph.State.TransitionTo<STATE, SIDE_EFFECT>> createTransitionTo) {
            on(any(), createTransitionTo);
        }

        public <E extends EVENT> void on(
                E event,
                BiFunction<S, E, Graph.State.TransitionTo<STATE, SIDE_EFFECT>> createTransitionTo) {
            on(eq(event), createTransitionTo);
        }

        public void onEnter(BiConsumer<S, EVENT> listener) {
            stateDefinition.onEnterListeners.add((state, cause) -> listener.accept((S) state, cause));
        }

        public void onExit(BiConsumer<S, EVENT> listener) {
            stateDefinition.onExitListeners.add((state, cause) -> listener.accept((S) state, cause));
        }

        public Graph.State<STATE, EVENT, SIDE_EFFECT> build() {
            return stateDefinition;
        }

    }
}
