package org.dyson.core.state

import org.dyson.core.state.j.StateMachine

sealed class State {
    object Solid : State()
    object Liquid : State()
    object Gas : State()
}

sealed class Event {
    object OnMelted : Event()
    object OnFroze : Event()
    object OnVaporized : Event()
    object OnCondensed : Event()
}

sealed class SideEffect {
    object LogMelted : SideEffect()
    object LogFrozen : SideEffect()
    object LogVaporized : SideEffect()
    object LogCondensed : SideEffect()
}

fun main() {
    KStateMachine.create<State, Event, SideEffect> {
        initialState(State.Solid)
        state<State.Solid> {
            on<Event.OnMelted> {
                transitionTo(State.Liquid, SideEffect.LogMelted)
            }
        }
    }

//    val stateMachine = StateMachine.create<State, Event, SideEffect> {
//        it.initialState(State.Solid)
//        it.state<State.Solid>(
//
//        )
//        state<State.Solid> {
//            on<Event.OnMelted> {
//                transitionTo(State.Liquid, SideEffect.LogMelted)
//            }
//        }

//        state<State.Liquid> {
//            on<Event.OnFroze> {
//                transitionTo(State.Solid, SideEffect.LogFrozen)
//            }
//            on<Event.OnVaporized> {
//                transitionTo(State.Gas, SideEffect.LogVaporized)
//            }
//        }
//        state<State.Gas> {
//            on<Event.OnCondensed> {
//                transitionTo(State.Liquid, SideEffect.LogCondensed)
//            }
//        }
//        onTransition {
//            val validTransition = it as? StateMachine.Transition.Valid ?: return@onTransition
//            when (validTransition.sideEffect) {
//                SideEffect.LogMelted -> logger.log(ON_MELTED_MESSAGE)
//                SideEffect.LogFrozen -> logger.log(ON_FROZEN_MESSAGE)
//                SideEffect.LogVaporized -> logger.log(ON_VAPORIZED_MESSAGE)
//                SideEffect.LogCondensed -> logger.log(ON_CONDENSED_MESSAGE)
//            }
//        }
    }
}