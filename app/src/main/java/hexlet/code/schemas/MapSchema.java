package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema required() {
        getPredicates().put("required", Objects::nonNull);
        return this;
    }

    public MapSchema sizeof(int size) {
        getPredicates().put("sizeof", s -> s == null || s.size() == size);
        return this;
    }
}
