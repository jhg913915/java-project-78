package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class MapSchemaTest {
    private Validator v;

    @BeforeEach
    void beforeEach() {
        v = new Validator();
    }

    @Test
    void testMapSchemaWithoutRequired() {
        MapSchema schema = v.map();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
    }

    @Test
    void testMapSchemaRequired() {
        MapSchema schema = v.map().required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        assertTrue(schema.isValid(data));
    }

    @ParameterizedTest
    @MethodSource("provideMapSizeTestCases")
    void testMapSchemaSizeof(MapSizeTestCase testCase) {
        MapSchema schema = v.map().sizeof(testCase.size);

        assertEquals(schema.isValid(testCase.inputMap), testCase.expected);
    }

    static Stream<MapSizeTestCase> provideMapSizeTestCases() {
        Map<String, String> data1 = new HashMap<>();
        data1.put("key1", "value1");

        Map<String, String> data2 = new HashMap<>();
        data2.put("key1", "value1");
        data2.put("key2", "value2");

        Map<String, String> data3 = new HashMap<>();
        data3.put("key1", "value1");
        data3.put("key2", "value2");
        data3.put("key3", "value3");

        return Stream.of(
                new MapSizeTestCase(2, data1, false),
                new MapSizeTestCase(2, data2, true),
                new MapSizeTestCase(2, data3, false)
        );
    }

    @Test
    void testMapSchemaRequiredAndSizeof() {
        MapSchema schema = v.map().required().sizeof(2);

        assertFalse(schema.isValid(null));

        Map<String, String> data1 = new HashMap<>();
        data1.put("key1", "value1");

        assertFalse(schema.isValid(data1));

        Map<String, String> data2 = new HashMap<>();
        data2.put("key1", "value1");
        data2.put("key2", "value2");

        assertTrue(schema.isValid(data2));

        Map<String, String> data3 = new HashMap<>();
        data3.put("key1", "value1");
        data3.put("key2", "value2");
        data3.put("key3", "value3");

        assertFalse(schema.isValid(data3));
    }

    @ParameterizedTest
    @MethodSource("provideMapShapeTestCases")
    void testMapSchemaShape(MapShapeTestCase testCase) {
        MapSchema schema = v.map();
        schema.shape(testCase.schemas);

        assertEquals(schema.isValid(testCase.inputMap), testCase.expected);
    }

    static Stream<MapShapeTestCase> provideMapShapeTestCases() {
        Validator v = new Validator();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");

        Map<String, String> human4 = new HashMap<>();
        human4.put("firstName", null);
        human4.put("lastName", "Smith");

        Map<String, String> human5 = new HashMap<>();
        human5.put("firstName", "John");
        human5.remove("lastName"); // Simulate missing key

        return Stream.of(
                new MapShapeTestCase(schemas, human1, true),
                new MapShapeTestCase(schemas, human2, false),
                new MapShapeTestCase(schemas, human3, false),
                new MapShapeTestCase(schemas, human4, false),
                new MapShapeTestCase(schemas, human5, false)
        );
    }

    record MapSizeTestCase(int size, Map<String, String> inputMap, boolean expected) {
    }

    record MapShapeTestCase(Map<String, BaseSchema<String>> schemas, Map<String, String> inputMap, boolean expected) {
    }
}
