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
class Matcher<T, R extends T> {
    private Class<R> clazz;
    private List<Predicate<T>> predicates = new ArrayList<>();

    public Matcher(Class<R> clazz) {
        this.clazz = clazz;
        predicates.add(clazz::isInstance);
    }

    public Matcher where(Function<R, Boolean> predicate) {
        predicates.add(it -> predicate.apply((R) it));
        return this;
    }

    public boolean matches(T value) {
        return predicates.stream().allMatch(pre -> pre.test(value));
    }

    public static <T, R extends T> Matcher<T, R> any(Class<R> clazz) {
        return new Matcher<>(clazz);
    }

    public static <T, R extends T> Matcher<T, R> any() {
        Class<R> clazz = ((Class) ((ParameterizedType) Matcher.class
                .getGenericSuperclass()).getActualTypeArguments()[0]);
        return any(clazz);
    }

    public static <T, R extends T> Matcher<T, R> eq(R value) {
        return any().where(it -> it.getClass().equals(value.getClass()) && it.equals(value));
    }


}
