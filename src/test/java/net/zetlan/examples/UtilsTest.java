package net.zetlan.examples;


import net.zetlan.examples.db.User;
import org.junit.jupiter.api.Test;

import javax.persistence.Entity;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


class UtilsTest {

    @Test
    void getPackageClassesWithAnnotation() {
        List<Class<?>> entities = Utils.getPackageClassesWithAnnotation("net.zetlan.examples.db", Entity.class);
        assertTrue(entities.contains(User.class));
    }

    @Test
    void testHasAnnotation() {
        Class<?> klazz = User.class;

        var annotations = klazz.getAnnotations();

        assertTrue(Utils.hasAnnotation(Entity.class).test(User.class));
    }
}