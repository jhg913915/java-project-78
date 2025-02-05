package hexlet.code;

import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MapSchemaTest {

    @Test
    void testMapSchemaWithoutRequired() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
    }

    @Test
    void testMapSchemaRequired() {
        Validator v = new Validator();
        MapSchema schema = v.map().required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data));
    }

    @Test
    void testMapSchemaSizeof() {
        Validator v = new Validator();
        MapSchema schema = v.map().sizeof(2);

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

    @Test
    void testMapSchemaRequiredAndSizeof() {
        Validator v = new Validator();
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
}
