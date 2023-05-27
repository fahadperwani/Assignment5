package tests.requirement_6;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import interpreter.bytecode.WriteCode;
import org.junit.Test;

import interpreter.Program;
import interpreter.VirtualMachine;
import interpreter.bytecode.OutCode;
import interpreter.bytecode.HaltCode;
import interpreter.bytecode.LitCode;

/**
 * PRE-CONDITIONS:
 *
 * Byte Codes Working:
 * - HaltCode (to stop the VM used to test)
 * - LitCode (stack set up for return)
 * - OutCode (since this bytecode requires a program to run in order to create
 * its output,
 * the console output is tested, and OutCode is required for console output)
 *
 * Objects implemented:
 * - Program
 * - RunTimeStack (using a VM to test which requires a runtime stack)
 */
import java.util.*;
import interpreter.bytecode.*;

public class WriteCodeTest {
    private final PrintStream standardOut = System.out;

    @Test
    public void testWriteCodeOutput() {
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        String literal = "987654";

        List<ByteCode> list = new ArrayList<>();
        list.add(new OutCode(Arrays.asList("y")));
        list.add(new LitCode(Arrays.asList(literal)));
        list.add(new WriteCode());
        list.add(new HaltCode());
        Program program = new Program(list);

        VirtualMachine vm = new VirtualMachine();
        vm.executeProgram(program);
        System.setOut(standardOut);

        String comparisonString = String.format("%sWRITE%s", System.lineSeparator(), System.lineSeparator());

        String output = outputStreamCaptor.toString();
        assertTrue(String.format("Expected [%s] in [%s]", comparisonString, output), output.contains(comparisonString));
    }
}
