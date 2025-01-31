package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.Objects;

public class StringSchema {

    private Map<String, Predicate<String>> predicates = new HashMap<>();

    public StringSchema required() {
        predicates.put("required", s -> s != null && !s.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        Objects.requireNonNull(length, "Length cannot be null");
        predicates.put("minLength", s -> s == null || s.length() >= length);
        return this;
    }


    public StringSchema contains(String str) {
        Objects.requireNonNull(str, "String cannot be null");
        predicates.put("contains", s -> s == null || s.contains(str));
        return this;
    }

    public boolean isValid(String value) {
        return predicates.values().stream().allMatch(p -> p.test(value));
    }
}
