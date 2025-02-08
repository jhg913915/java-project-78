package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public final class NumberSchemaTest {
    private Validator v;
    private NumberSchema schema;

    @BeforeEach
    void beforeEach() {
        v = new Validator();
        schema = v.number();
    }

    @Test
    void testNumberSchemaWithoutRequired() {
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(null));
    }


    @Test
    void testNumberSchemaRequired() {
        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 100})
    void testNumberSchemaPositivePositiveNumbers(int number) {
        schema.positive();

        assertTrue(schema.isValid(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {-5, 0})
    void testNumberSchemaPositiveNegativeAndZero(int number) {
        schema.positive();

        assertFalse(schema.isValid(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 6, 7, 8, 9, 10})
    void testNumberSchemaRangePositiveNumbers(int number) {
        schema.range(5, 10);

        assertTrue(schema.isValid(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 11, -1, 100})
    void testNumberSchemaRangeNegativeNumbers(int number) {
        NumberSchema newSchema = v.number().range(5, 10);

        assertFalse(newSchema.isValid(number));
    }


    @ParameterizedTest
    @ValueSource(ints = {5, 6, 7, 8, 9, 10})
    void testNumberSchemaAllRulesPositiveNumbers(int number) {
        schema.required().positive().range(5, 10);

        assertTrue(schema.isValid(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 4, 11, -6, -100})
    void testNumberSchemaAllRulesNegativeNumbers(int number) {
        NumberSchema newSchema = v.number().required().positive().range(5, 10);

        assertFalse(newSchema.isValid(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 50, -1})
    void testNumberSchemaPositiveRequiredAndPositiveNumbers(int number) {
        schema.required().positive();

        if (number > 0) {
            assertTrue(schema.isValid(number));
        } else {
            assertFalse(schema.isValid(number));
        }
    }
}
