package net.zetlan.examples;

import java.util.Collection;
import java.util.Map;

public class Utils {
    private Utils() {}

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return map == null || map.isEmpty();
    }
}
