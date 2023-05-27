package tests.requirement_6;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import interpreter.bytecode.OutCode;

/**
 * PRE-CONDITIONS:
 *
 * None
 */
public class OutCodeTest {

    @Test
    public void testOutCodeOutput() {
        OutCode code = new OutCode(Arrays.asList("y"));

        String comparisonString = "OUT y";
        assertTrue(code.toString().equals(comparisonString));

        code = new OutCode(Arrays.asList("n"));
        comparisonString = "OUT n";
        assertTrue(code.toString().equals(comparisonString));
    }
}
