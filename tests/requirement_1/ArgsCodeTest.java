package tests.requirement_1;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import interpreter.bytecode.ArgsCode;
import org.junit.Test;

import interpreter.Program;
import interpreter.RunTimeStack;
import interpreter.VirtualMachine;
import interpreter.bytecode.HaltCode;
import interpreter.bytecode.LitCode;
import tests.helpers.RTSHelper;
import tests.helpers.VMHelper;
import interpreter.bytecode.*;;

/**
 * PRE-CONDITIONS:
 *
 * Byte Codes Working:
 * - HaltCode (to stop the VM used to test)
 * - LabelCode (branch target for CallCode)
 * - LitCode (stack set up for args)
 *
 * Objects implemented:
 * - Program
 * - RunTimeStack (using a VM to test which requires a runtime stack)
 */
public class ArgsCodeTest {

    @Test
    public void testArgsCodeWithFormals() throws IllegalArgumentException, IllegalAccessException {
        List<ByteCode> list = new ArrayList<>();
        list.add(new LitCode(Arrays.asList("9")));
        list.add(new LitCode(Arrays.asList("8")));
        list.add(new ArgsCode(Arrays.asList("1")));

        list.add(new LitCode(Arrays.asList("99")));
        list.add(new LitCode(Arrays.asList("88")));
        list.add(new LitCode(Arrays.asList("77")));
        list.add(new ArgsCode(Arrays.asList("2")));

        list.add(new HaltCode());
        Program program = new Program(list);

        VirtualMachine vm = new VirtualMachine();
        vm.executeProgram(program);

        RunTimeStack rts = VMHelper.getRts(vm);
        int pc = VMHelper.getPc(vm);

        assertEquals(8, pc);

        // We expect 3 entries in functions stack: 0, 1, 3
        Stack<?> runStack = RTSHelper.getFramePointers(rts);
        assertEquals(3, runStack.size());
        assertEquals(3, ((Integer) runStack.pop()).intValue());
        assertEquals(1, ((Integer) runStack.pop()).intValue());
        assertEquals(0, ((Integer) runStack.pop()).intValue());
    }

    @Test
    public void testArgsCodeNoFormals() throws IllegalArgumentException, IllegalAccessException {
        List<ByteCode> list = new ArrayList<>();
        list.add(new ArgsCode(Arrays.asList("0")));
        list.add(new ArgsCode(Arrays.asList("0")));
        list.add(new ArgsCode(Arrays.asList("0")));

        list.add(new HaltCode());
        Program program = new Program(list);

        VirtualMachine vm = new VirtualMachine();
        vm.executeProgram(program);

        RunTimeStack rts = VMHelper.getRts(vm);
        int pc = VMHelper.getPc(vm);

        assertEquals(4, pc);

        // We expect 3 entries in functions stack: 0, 0, 0
        Stack<?> runStack = RTSHelper.getFramePointers(rts);
        assertEquals(4, runStack.size());
        assertEquals(0, ((Integer) runStack.pop()).intValue());
        assertEquals(0, ((Integer) runStack.pop()).intValue());
        assertEquals(0, ((Integer) runStack.pop()).intValue());
        assertEquals(0, ((Integer) runStack.pop()).intValue());
    }
}
