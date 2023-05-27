package tests.requirement_1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Vector;

import interpreter.bytecode.WriteCode;
import org.junit.Test;

import interpreter.Program;
import interpreter.RunTimeStack;
import interpreter.VirtualMachine;
import interpreter.bytecode.HaltCode;
import interpreter.bytecode.LitCode;
//import interpreter.bytecode.WriteCode;
import tests.helpers.RTSHelper;
import tests.helpers.VMHelper;

/**
 * PRE-CONDITIONS:
 *
 * Byte Codes Working:
 * - HaltCode (to stop the VM used to test)
 * - LitCode (stack set up for return)
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
    public void testWriteCode() throws IllegalArgumentException, IllegalAccessException {
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        String literal = "987654";

        List<ByteCode> list = new ArrayList<>();
        list.add(new LitCode(Arrays.asList(literal)));
        list.add(new WriteCode());
        list.add(new HaltCode());
        Program program = new Program(list);

        VirtualMachine vm = new VirtualMachine();
        vm.executeProgram(program);
        System.setOut(standardOut);

        RunTimeStack rts = VMHelper.getRts(vm);
        int pc = VMHelper.getPc(vm);

        // Make sure that the pc was updated to the correct opcode
        assertEquals(3, pc);

        Vector<?> runStack = RTSHelper.getRunStack(rts);
        // Check that write left value on the stack
        assertEquals(1, runStack.size());
        assertEquals(Integer.parseInt(literal), runStack.remove(0));

        String expected = String.format("%s%s", literal, System.lineSeparator());
        String output = outputStreamCaptor.toString();
        assertTrue(output.equals(expected));
    }

}
