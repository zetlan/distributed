package net.zetlan.examples;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.reflect.ClassPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Utils {
    private final static Logger LOGGER = LoggerFactory.getLogger(Utils.class);
    private Utils() {}

    private static Predicate<ClassPath.ClassInfo> hasPackageName(String packageName) {
        return clazz -> clazz.getPackageName().equalsIgnoreCase(packageName);
    }

    @VisibleForTesting
    static Predicate<Class<?>> hasAnnotation(Class annotationClass) {
        return clazz -> clazz.getAnnotation(annotationClass) != null;
    }

    public static List<Class<?>> getPackageClassesWithAnnotation(String packageName, Class annotationClass) {
        try {
            return ClassPath.from(Thread.currentThread().getContextClassLoader())
                    .getAllClasses()
                    .stream()
                    .filter(hasPackageName(packageName))
                    .map(ClassPath.ClassInfo::load)
                    .filter(hasAnnotation(annotationClass))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error("IO Exception when loading classes:", e);
            return Collections.emptyList();
        }
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
}
