package javabot.util;

import static java.util.Arrays.asList;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class CollectionUtil {
	@SafeVarargs
	public static <T> HashSet<T> setOf(T ... array) {
		return setOf(asList(array));
	}

	public static <T> HashSet<T> setOf(List<T> list) {
		return new HashSet<>(list);
	}
	
	public static <T> boolean any(Collection<T> collection) {
		return collection != null && !collection.isEmpty();
	}
	
	public static <T> T getRandom(List<T> list) {
		return list.get(new Random().nextInt(list.size()));
	}
	
	public static <T> T first(List<T> list) {
		return list.get(0);
	}
	
	public static <T> T last(List<T> list) {
		return list.get(list.size() - 1);
	}
}
