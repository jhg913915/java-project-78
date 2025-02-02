package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    private Map<String, Predicate<T>> predicates = new HashMap<>();

    public boolean isValid(T value) {
        return predicates.values().stream().allMatch(p -> p.test(value));
    }

    public Map<String, Predicate<T>> getPredicates() {
        return predicates;
    }
}

