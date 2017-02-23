package javabot.util;

import static java.util.Arrays.asList;

import java.util.HashSet;
import java.util.List;

public class CollectionUtil {
	@SafeVarargs
	public static <T> HashSet<T> setOf(T ... array) {
		return setOf(asList(array));
	}

	public static <T> HashSet<T> setOf(List<T> list) {
		return new HashSet<>(list);
	}
}
