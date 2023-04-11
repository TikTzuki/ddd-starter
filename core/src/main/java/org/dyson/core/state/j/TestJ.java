package org.dyson.core.state.j;

import java.util.function.Consumer;

public class TestJ {
    class State extends IState {
        class Solid extends State {
        }

        class Liquid extends State {
        }

        class Gas extends State {
        }
    }

    class Event {
        class OnMelted  extends Event{}
        class OnFroze extends Event{}
        class OnVaporized extends Event{}
    }
    class SideEffect{
        class LogMelted extends  SideEffect{}
    }

    public static void main(String[] args) {
        StateMachine.create(new Consumer<GraphBuilder<State, Event, SideEffect>>() {
            @Override
            public void accept(GraphBuilder<State, Event, SideEffect> graphBuilder) {

            }
        });
//            builder.initialState(State.Solid);
//            builder.state(State.Solid, state -> {
//                state.on(Event.OnMelted.class, event -> {
//                    return new Transition.Valid<>(State.Liquid, SideEffect.LogMelted);
//                });
//            });
    }
}
