package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    @Test
    void testStringSchemaRequired() {
        StringSchema schema = v.string().required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("what does the fox say"));
        assertTrue(schema.isValid("hexlet"));
    }

    @Test
    void testStringSchemaContains() {
        StringSchema schema = v.string().contains("he");
        StringSchema schema2 = v.string().contains("hexl");
        StringSchema schema3 = v.string().contains("hexlhex");

        assertTrue(schema.isValid("hexl hexl hex hex hex"));
        assertTrue(schema2.isValid("hexl hexl hex hex hex"));
        assertFalse(schema3.isValid("hexl hexl hex hex hex"));
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
