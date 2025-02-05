package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public final class NumberSchemaTest {
    Validator v;

    @BeforeEach
    void beforeEach() {
        v = new Validator();
    }

    @Test
    void testNumberSchemaWithoutRequired() {
        NumberSchema schema = v.number();
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(null));
    }


    @Test
    void testNumberSchemaRequired() {
        NumberSchema schema = v.number().required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));
    }

    @Test
    void testNumberSchemaPositive() {
        NumberSchema schema = v.number().positive();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(5));
        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));
    }

    @Test
    void testNumberSchemaRange() {
        NumberSchema schema = v.number().range(5, 10);
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }

    @Test
    void testNumberSchemaAllRules() {
        NumberSchema schema = v.number().required().positive().range(5, 10);

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(5));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }


    @Test
    void testNumberSchemaRequiredAndPositive() {
        NumberSchema schema = v.number().required().positive();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));
    }
}
