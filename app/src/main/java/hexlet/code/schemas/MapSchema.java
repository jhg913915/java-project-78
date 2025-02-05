package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    private Map<String, BaseSchema<String>> schemas;

    public MapSchema required() {
        getPredicates().put("required", Objects::nonNull);
        return this;
    }

    public MapSchema sizeof(int size) {
        getPredicates().put("sizeof", s -> s == null || s.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> newSchemas) {
        this.schemas = newSchemas;
        getPredicates().put("shape", this::validateShape);
        return this;
    }

    private boolean validateShape(Map<?, ?> map) {
        if (schemas == null || map == null) {
            return true;
        }
        for (Map.Entry<String, BaseSchema<String>> entry : schemas.entrySet()) {
            String key = entry.getKey();
            BaseSchema schema = entry.getValue();
            Object value = map.get(key);
            if (!schema.isValid(value)) {
                return false;
            }
        }
        return true;
    }
}
