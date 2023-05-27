package tests.requirement_1;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Stack;

import interpreter.bytecode.*;
import org.junit.Test;

import interpreter.Program;
import interpreter.VirtualMachine;
//import interpreter.bytecode.ArgsCode;
//import interpreter.bytecode.CallCode;
import tests.helpers.VMHelper;

/**
 * PRE-CONDITIONS:
 *
 * Byte Codes Working:
 * - HaltCode (to stop the VM used to test)
 * - LabelCode (branch target for CallCode)
 * - LitCode (stack set up)
 * - ArgsCode (frame setup for calling a function)
 *
 * Objects implemented:
 * - Program (resolveSymbolicAddresses must be implemented)
 * - RunTimeStack (using a VM to test which requires a runtime stack)
 */
import java.util.*;

public class CallCodeTest {

    @Test
    public void testCallCodeUpdatesReturnAddresses() throws IllegalArgumentException, IllegalAccessException {
        String function = "fn<<2>>";
        List<ByteCode> list = new ArrayList<>();

        list.add(new LitCode(Arrays.asList("9")));
        list.add(new LitCode(Arrays.asList("8")));
        list.add(new ArgsCode(Arrays.asList("1")));
        list.add(new CallCode(Arrays.asList(function)));
        list.add(new HaltCode());
        list.add(new LitCode(Arrays.asList("42")));
        list.add(new LitCode(Arrays.asList("42")));
        list.add(new LitCode(Arrays.asList("42")));
        list.add(new LitCode(Arrays.asList("42")));
        list.add(new LabelCode(Arrays.asList(function)));
        list.add(new HaltCode());

        Program program = new Program(list);

        program.resolveAddresses();

        VirtualMachine vm = new VirtualMachine();
        vm.executeProgram(program);
        Stack<?> returnAddresses = VMHelper.getReturnAddresses(vm);
        int lastReturnAddress = Integer.valueOf((Integer) returnAddresses.pop());

        assertEquals(3, lastReturnAddress);
    }

    @Test
    public void testCallCodeUpdatesPC() throws IllegalArgumentException, IllegalAccessException {
        String function = "fn<<2>>";

        List<ByteCode> list = new ArrayList<>();
        list.add(new LitCode(Arrays.asList("9")));
        list.add(new LitCode(Arrays.asList("8")));
        list.add(new ArgsCode(Arrays.asList("1")));
        list.add(new CallCode(Arrays.asList(function)));
        list.add(new HaltCode());
        list.add(new LitCode(Arrays.asList("42")));
        list.add(new LitCode(Arrays.asList("42")));
        list.add(new LitCode(Arrays.asList("42")));
        list.add(new LitCode(Arrays.asList("42")));
        list.add(new LabelCode(Arrays.asList(function)));
        list.add(new HaltCode());
        Program program = new Program(list);

        program.resolveAddresses();

        VirtualMachine vm = new VirtualMachine();
        vm.executeProgram(program);

        int pc = VMHelper.getPc(vm);

        // Make sure that the pc was updated to the label opcode
        assertEquals(11, pc);
    }
}
