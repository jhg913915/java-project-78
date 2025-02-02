package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NumberSchemaTest {

    @Test
    void testNumberSchemaWithoutRequired() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(null));
    }


    @Test
    void testNumberSchemaRequired() {
        Validator v = new Validator();
        NumberSchema schema = v.number().required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));
    }

    @Test
    void testNumberSchemaPositive() {
        Validator v = new Validator();
        NumberSchema schema = v.number().positive();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(5));
        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));
    }

    @Test
    void testNumberSchemaRange() {
        Validator v = new Validator();
        NumberSchema schema = v.number().range(5, 10);
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }

    @Test
    void testNumberSchemaAllRules() {
        Validator v = new Validator();
        NumberSchema schema = v.number().required().positive().range(5, 10);

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(5));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }


    @Test
    void testNumberSchemaRequiredAndPositive() {
        Validator v = new Validator();
        NumberSchema schema = v.number().required().positive();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));
    }
}
