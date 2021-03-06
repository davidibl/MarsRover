package de.lv1871.dms.MarsRover.domain;

import java.util.function.BiFunction;
import java.util.function.Function;

@FunctionalInterface
public interface ExtendedFunction<T, F, R> extends BiFunction<T, F, R> {

	default Function<T, Function<F, R>> curry() {
		return a -> b -> this.apply(a, b);
	}

	default Function<F, R> curryWith(T value) {
		return curry().apply(value);
	}
}
