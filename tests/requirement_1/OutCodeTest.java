package tests.requirement_1;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import interpreter.bytecode.OutCode;
import org.junit.Test;

import interpreter.Program;
import interpreter.VirtualMachine;
//import interpreter.bytecode.OutCode;
import interpreter.bytecode.HaltCode;
import tests.helpers.VMHelper;

/**
 * PRE-CONDITIONS:
 *
 * Byte Codes Working:
 * - HaltCode (to stop the VM used to test)
 *
 * Objects implemented:
 * - Program
 * - RunTimeStack (using a VM to test which requires a runtime stack)
 */
import java.util.*;
import interpreter.bytecode.*;

public class OutCodeTest {
    private final PrintStream standardOut = System.out;

    @Test
    public void testOutCodeOn() throws IllegalArgumentException, IllegalAccessException {
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        List<ByteCode> list = new ArrayList<>();
        list.add(new OutCode(Arrays.asList("y")));
        list.add(new HaltCode());
        Program program = new Program(list);

        VirtualMachine vm = new VirtualMachine();
        vm.executeProgram(program);
        System.setOut(standardOut);

        boolean isOn = VMHelper.getIsOutputting(vm);

        assertTrue(isOn);
        assertTrue(outputStreamCaptor.toString().length() > 0);
    }

    @Test
    public void testOutCodeOff() throws IllegalArgumentException, IllegalAccessException {
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        List<ByteCode> list = new ArrayList<>();
        list.add(new OutCode(Arrays.asList("n")));
        list.add(new HaltCode());
        Program program = new Program(list);

        VirtualMachine vm = new VirtualMachine();
        vm.executeProgram(program);
        System.setOut(standardOut);

        boolean isOn = VMHelper.getIsOutputting(vm);

        assertFalse(isOn);
        assertTrue(outputStreamCaptor.toString().length() == 0);
    }
}
