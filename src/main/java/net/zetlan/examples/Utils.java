package net.zetlan.examples;

import java.util.Collection;

public class Utils {
    private Utils() {}

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
}
