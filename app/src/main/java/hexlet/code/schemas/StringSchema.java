package hexlet.code.schemas;

import java.util.Objects;

public class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        getPredicates().put("required", s -> s != null && !s.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        getPredicates().put("minLength", s -> s == null || s.length() >= length);
        return this;
    }

    public StringSchema contains(String str) {
        Objects.requireNonNull(str, "String cannot be null");
        getPredicates().put("contains", s -> s == null || s.contains(str));
        return this;
    }
}
