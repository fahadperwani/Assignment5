package tests.requirement_1;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Vector;

import interpreter.bytecode.PopCode;
import org.junit.Test;

import interpreter.Program;
import interpreter.RunTimeStack;
import interpreter.VirtualMachine;
import interpreter.bytecode.HaltCode;
import interpreter.bytecode.LitCode;
//import interpreter.bytecode.PopCode;
import tests.helpers.RTSHelper;
import tests.helpers.VMHelper;

/**
 * PRE-CONDITIONS:
 *
 * Byte Codes Working:
 * - HaltCode (to stop the VM used to test)
 * - LitCode (stack set up for popping)
 *
 * Objects implemented:
 * - Program
 * - RunTimeStack (using a VM to test which requires a runtime stack)
 */
import java.util.*;
import interpreter.bytecode.*;

public class PopCodeTest {
    @Test
    public void testPopCode() throws IllegalArgumentException, IllegalAccessException {
        List<ByteCode> list = new ArrayList<>();
        list.add(new LitCode(Arrays.asList("1")));
        list.add(new LitCode(Arrays.asList("2")));
        list.add(new LitCode(Arrays.asList("3")));
        list.add(new LitCode(Arrays.asList("4")));
        list.add(new LitCode(Arrays.asList("5")));
        list.add(new PopCode(Arrays.asList("3")));

        list.add(new HaltCode());
        Program program = new Program(list);

        VirtualMachine vm = new VirtualMachine();
        vm.executeProgram(program);

        RunTimeStack rts = VMHelper.getRts(vm);
        int pc = VMHelper.getPc(vm);

        assertEquals(7, pc);

        Vector<?> runStack = RTSHelper.getRunStack(rts);
        assertEquals(2, runStack.size());
    }

}
