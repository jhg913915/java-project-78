package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public final class StringSchemaTest {
    private Validator v;

    @BeforeEach
    void beforeEach() {
        v = new Validator();
    }

    @Test
    void testStringSchemaWithoutRequired() {
        StringSchema schema = v.string();

        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testStringSchemaRequiredNegative(String value) {
        StringSchema schema = v.string().required();

        assertFalse(schema.isValid(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"what does the fox say", "hexlet"})
    void testStringSchemaRequiredPositive(String value) {
        StringSchema schema = v.string().required();

        assertTrue(schema.isValid(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"hexl hexl hex hex hex"})
    void testStringSchemaContainsPositive(String value) {
        StringSchema schema = v.string().contains("he");
        StringSchema schema2 = v.string().contains("hexl");

        assertTrue(schema.isValid(value));
        assertTrue(schema2.isValid(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"hexl hexl hex hex hex"})
    void testStringSchemaContainsNegative(String value) {
        StringSchema schema3 = v.string().contains("hexlhex");

        assertFalse(schema3.isValid(value));
    }

    @Test
    void testStringSchemaRequiredAndContains() {
        StringSchema schema = v.string().required().contains("he");

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("hexl hexl hex hex hex"));
        assertFalse(schema.isValid("ehxlet"));
    }

    @Test
    void testStringSchemaMinLength() {
        StringSchema schema1 = v.string().minLength(10);
        StringSchema schema2 = v.string().minLength(10).minLength(4);

        assertFalse(schema1.isValid("Hex"));
        assertFalse(schema1.isValid("Hexlet"));
        assertTrue(schema2.isValid("Hexlet"));
    }

    @Test
    void testStringSchemaAllRules() {
        StringSchema schema = v.string().required().minLength(5).contains("hex");

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid("hex"));
        assertTrue(schema.isValid("hexl hexl hex hex hex"));
        assertTrue(schema.isValid("hexlet"));
    }
}
