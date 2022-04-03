package net.zetlan.examples.core;

import net.zetlan.examples.Utils;
import net.zetlan.examples.db.BaseEntity;
import net.zetlan.examples.db.User;

import javax.ws.rs.WebApplicationException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BaseManager {

    protected <T extends BaseEntity, V> void requireUnique(Map<Integer, T> entities, V newValue, Function<T, V> getter) {
        Predicate<T> uniquenessTest = newValue instanceof String
                ? entity -> ((String) newValue).equalsIgnoreCase((String) getter.apply(entity))
                : entity -> Objects.equals(newValue, getter.apply(entity));

        List<T> existingEntities = entities.values()
                .stream()
                .filter(uniquenessTest)
                .collect(Collectors.toList());
        if (!Utils.isEmpty(existingEntities)) {
            throw new WebApplicationException(String.format("[%s] must be unique on collection of [%s]",
                    newValue.toString(), existingEntities.get(0).getClass().toString()));
        }
    }
}
