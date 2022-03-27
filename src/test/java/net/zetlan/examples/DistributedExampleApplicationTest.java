package net.zetlan.examples;

import net.zetlan.examples.db.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DistributedExampleApplicationTest {

    @Test
    void testGetEntityClasses() {
        List<Class<?>> classes = DistributedExampleApplication.getEntityClasses();
        assertTrue(classes != null && classes.size() > 0, "Should not be empty");
        assertTrue(classes.contains(User.class), "Should contain User.class");
    }

}