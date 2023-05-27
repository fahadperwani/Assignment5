package tests.requirement_1;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Vector;
import java.util.stream.Stream;

import interpreter.bytecode.BopCode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import interpreter.Program;
import interpreter.RunTimeStack;
import interpreter.VirtualMachine;
//import interpreter.bytecode.BopCode;
import interpreter.bytecode.HaltCode;
import interpreter.bytecode.LitCode;
import tests.helpers.RTSHelper;
import tests.helpers.VMHelper;

/**
 * PRE-CONDITIONS:
 *
 * Byte Codes Working:
 * - HaltCode (to stop the VM used to test)
 * - LitCode (stack set up for operator)
 *
 * Objects implemented:
 * - Program
 * - RunTimeStack (using a VM to test which requires a runtime stack)
 */
import java.util.*;
import interpreter.bytecode.*;;

public class BopCodeTest {

    @ParameterizedTest
    @MethodSource("provideBopCodeTests")
    public void testBopCode(String operator, String valueOne, String valueTwo, int expected)
            throws IllegalArgumentException, IllegalAccessException {
        List<ByteCode> list = new ArrayList<>();
        list.add(new LitCode(Arrays.asList(valueOne)));
        list.add(new LitCode(Arrays.asList(valueTwo)));
        list.add(new BopCode(Arrays.asList(operator)));

        list.add(new HaltCode());
        Program program = new Program(list);

        VirtualMachine vm = new VirtualMachine();
        vm.executeProgram(program);

        RunTimeStack rts = VMHelper.getRts(vm);
        int pc = VMHelper.getPc(vm);

        assertEquals(4, pc);

        Vector<?> runStack = RTSHelper.getRunStack(rts);
        assertEquals(1, runStack.size());
        assertEquals(expected, runStack.remove(0));
    }

    private static Stream<Arguments> provideBopCodeTests() {
        return Stream.of(
                Arguments.of("+", "7", "9", 16),
                Arguments.of("-", "7", "9", -2),
                Arguments.of("*", "8", "3", 24),
                Arguments.of("/", "15", "7", 2),
                Arguments.of("==", "3", "9", 0),
                Arguments.of("==", "3", "3", 1),
                Arguments.of("!=", "3", "9", 1),
                Arguments.of("!=", "3", "3", 0),
                Arguments.of("<", "1", "11", 1),
                Arguments.of("<", "1", "1", 0),
                Arguments.of("<", "42", "1", 0),
                Arguments.of("<=", "1", "11", 1),
                Arguments.of("<=", "1", "1", 1),
                Arguments.of("<=", "42", "1", 0),
                Arguments.of(">", "1", "11", 0),
                Arguments.of(">", "1", "1", 0),
                Arguments.of(">", "42", "1", 1),
                Arguments.of(">=", "1", "11", 0),
                Arguments.of(">=", "1", "1", 1),
                Arguments.of(">=", "42", "1", 1),
                Arguments.of("|", "0", "0", 0),
                Arguments.of("|", "0", "1", 1),
                Arguments.of("|", "1", "0", 1),
                Arguments.of("|", "1", "1", 1),
                Arguments.of("&", "0", "0", 0),
                Arguments.of("&", "0", "1", 0),
                Arguments.of("&", "1", "0", 0),
                Arguments.of("&", "1", "1", 1));
    }
}
