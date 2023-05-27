package tests.requirement_6;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import interpreter.bytecode.*;
import org.junit.Test;

import interpreter.Program;
import interpreter.VirtualMachine;

/**
 * PRE-CONDITIONS:
 *
 * Byte Codes Working:
 * - HaltCode (to stop the VM used to test)
 * - LabelCode (branch target for CallCode)
 * - LitCode (stack set up)
 * - ArgsCode (frame setup for calling a function)
 * - OutCode (since this bytecode requires a program to run in order to create
 * its output,
 * the console output is tested, and OutCode is required for console output)
 *
 * Objects implemented:
 * - Program (resolveSymbolicAddresses must be implemented)
 * - RunTimeStack (using a VM to test which requires a runtime stack)
 */
import java.util.*;;

public class CallCodeTest {
    private final PrintStream standardOut = System.out;

    @Test
    public void testCallCodeOutputNoArgs() throws IllegalArgumentException, IllegalAccessException {
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        String functionName = "fn";
        String functionLabel = String.format("%s<<2>>", functionName);

        List<ByteCode> list = new ArrayList<>();
        list.add(new OutCode(Arrays.asList("y")));
        list.add(new LitCode(Arrays.asList("8")));
        list.add(new ArgsCode(Arrays.asList("0")));
        list.add(new CallCode(Arrays.asList(functionLabel)));
        list.add(new HaltCode());
        list.add(new LitCode(Arrays.asList("42")));
        list.add(new LitCode(Arrays.asList("42")));
        list.add(new LitCode(Arrays.asList("42")));
        list.add(new LitCode(Arrays.asList("42")));
        list.add(new LabelCode(Arrays.asList(functionLabel)));
        list.add(new HaltCode());
        Program program = new Program(list);

        program.resolveAddresses();

        VirtualMachine vm = new VirtualMachine();
        vm.executeProgram(program);
        System.setOut(standardOut);

        String comparisonString = String.format(
                "%-25s%s()",
                String.format("CALL %s", functionLabel),
                functionName);

        String output = outputStreamCaptor.toString();
        assertTrue(String.format("Expected [%s] in [%s]", comparisonString, output), output.contains(comparisonString));
    }

    @Test
    public void testCallCodeOutputOneArg() throws IllegalArgumentException, IllegalAccessException {
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        String functionName = "fn";
        String functionLabel = String.format("%s<<2>>", functionName);

        List<ByteCode> list = new ArrayList<>();
        list.add(new OutCode(Arrays.asList("y")));
        list.add(new LitCode(Arrays.asList("8")));
        list.add(new ArgsCode(Arrays.asList("1")));
        list.add(new CallCode(Arrays.asList(functionLabel)));
        list.add(new HaltCode());
        list.add(new LitCode(Arrays.asList("42")));
        list.add(new LitCode(Arrays.asList("42")));
        list.add(new LitCode(Arrays.asList("42")));
        list.add(new LitCode(Arrays.asList("42")));
        list.add(new LabelCode(Arrays.asList(functionLabel)));
        list.add(new HaltCode());
        Program program = new Program(list);

        program.resolveAddresses();

        VirtualMachine vm = new VirtualMachine();
        vm.executeProgram(program);
        System.setOut(standardOut);

        String comparisonString = String.format(
                "%-25s%s(8)",
                String.format("CALL %s", functionLabel),
                functionName);

        String output = outputStreamCaptor.toString();
        assertTrue(String.format("Expected [%s] in [%s]", comparisonString, output), output.contains(comparisonString));
    }

    @Test
    public void testCallCodeOutputMultipleArgs() throws IllegalArgumentException, IllegalAccessException {
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        String functionName = "fn";
        String functionLabel = String.format("%s<<2>>", functionName);

        List<ByteCode> list = new ArrayList<>();
        list.add(new OutCode(Arrays.asList("y")));
        list.add(new LitCode(Arrays.asList("8")));
        list.add(new LitCode(Arrays.asList("42")));
        list.add(new LitCode(Arrays.asList("9")));
        list.add(new ArgsCode(Arrays.asList("3")));
        list.add(new CallCode(Arrays.asList(functionLabel)));
        list.add(new HaltCode());
        list.add(new LitCode(Arrays.asList("42")));
        list.add(new LitCode(Arrays.asList("42")));
        list.add(new LitCode(Arrays.asList("42")));
        list.add(new LitCode(Arrays.asList("42")));
        list.add(new LabelCode(Arrays.asList(functionLabel)));
        list.add(new HaltCode());
        Program program = new Program(list);

        program.resolveAddresses();

        VirtualMachine vm = new VirtualMachine();
        vm.executeProgram(program);
        System.setOut(standardOut);

        String comparisonString = String.format(
                "%-25s%s(8,42,9)",
                String.format("CALL %s", functionLabel),
                functionName);

        String output = outputStreamCaptor.toString();
        assertTrue(String.format("Expected [%s] in [%s]", comparisonString, output), output.contains(comparisonString));
    }
}
