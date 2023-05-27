package tests.requirement_1;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Vector;

import interpreter.bytecode.StoreCode;
import org.junit.Test;

import interpreter.Program;
import interpreter.RunTimeStack;
import interpreter.VirtualMachine;
import interpreter.bytecode.HaltCode;
import interpreter.bytecode.LitCode;
//import interpreter.bytecode.StoreCode;
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

public class StoreCodeTest {
    @Test
    public void testStoreCode() throws IllegalArgumentException, IllegalAccessException {
        String id = "zed";
        String literal = "71";

        List<ByteCode> list = new ArrayList<>();
        list.add(new LitCode(Arrays.asList("88", id)));
        list.add(new LitCode(Arrays.asList(literal)));
        list.add(new StoreCode(Arrays.asList("0", id)));
        list.add(new HaltCode());
        Program program = new Program(list);

        VirtualMachine vm = new VirtualMachine();
        vm.executeProgram(program);

        RunTimeStack rts = VMHelper.getRts(vm);
        int pc = VMHelper.getPc(vm);

        // Make sure that the pc was updated to the correct opcode
        assertEquals(4, pc);

        Vector<?> runStack = RTSHelper.getRunStack(rts);
        assertEquals(1, runStack.size());
        assertEquals(Integer.parseInt(literal), runStack.remove(0));
    }

}
