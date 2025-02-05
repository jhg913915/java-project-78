package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema required() {
        getPredicates().put("required", Objects::nonNull);
        return this;
    }

    public NumberSchema range(int min, int max) {
        getPredicates().put("range", s -> s == null || (s >= min && s <= max));
        return this;
    }

    public NumberSchema positive() {
        getPredicates().put("positive", s -> s == null || s > 0);
        return this;
    }
}
