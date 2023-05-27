package tests.requirement_6;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import interpreter.bytecode.ArgsCode;
import org.junit.Test;

/**
 * PRE-CONDITIONS:
 *
 * None
 */
public class ArgsCodeTest {

    @Test
    public void testArgsCodeOutput() {
        String argCount = "42";
        ArgsCode code = new ArgsCode(Arrays.asList(argCount));

        String comparisonString = String.format("ARGS %s", argCount);

        assertTrue(code.toString().equals(comparisonString));
    }
}
