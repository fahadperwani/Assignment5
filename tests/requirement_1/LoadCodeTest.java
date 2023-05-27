package tests.requirement_1;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Vector;

import interpreter.bytecode.LoadCode;
import org.junit.Assert;
import org.junit.Test;

import interpreter.Program;
import interpreter.RunTimeStack;
import interpreter.VirtualMachine;
import interpreter.bytecode.HaltCode;
import interpreter.bytecode.LitCode;
//import interpreter.bytecode.LoadCode;
import interpreter.errors.StackUnderflowException;
import tests.helpers.RTSHelper;
import tests.helpers.VMHelper;

/**
 * PRE-CONDITIONS:
 *
 * Byte Codes Working:
 * - HaltCode (to stop the VM used to test)
 * - LitCode (stack set up for loading variables)
 *
 * Objects implemented:
 * - Program
 * - RunTimeStack (using a VM to test which requires a runtime stack)
 */
import java.util.*;
import interpreter.bytecode.*;

public class LoadCodeTest {

    @Test
    public void testLoadCode() throws IllegalArgumentException, IllegalAccessException {
        String valueOne = "5", valueTwo = "42", valueThree = "1";
        String idOne = "blarg", idTwo = "boop", idThree = "hello";

        List<ByteCode> list = new ArrayList<>();
        list.add(new LitCode(Arrays.asList(valueOne, idOne)));
        list.add(new LitCode(Arrays.asList(valueTwo, idTwo)));
        list.add(new LitCode(Arrays.asList(valueThree, idThree)));
        list.add(new LoadCode(Arrays.asList("1", idTwo)));
        list.add(new LoadCode(Arrays.asList("2", idThree)));
        list.add(new LoadCode(Arrays.asList("0", idOne)));
        list.add(new HaltCode());
        Program program = new Program(list);

        VirtualMachine vm = new VirtualMachine();
        vm.executeProgram(program);

        RunTimeStack rts = VMHelper.getRts(vm);
        int pc = VMHelper.getPc(vm);

        assertEquals(7, pc);

        Vector<?> runStack = RTSHelper.getRunStack(rts);
        assertEquals(6, runStack.size());

        try {
            assertEquals(Integer.parseInt(valueOne), rts.pop());
            assertEquals(Integer.parseInt(valueThree), rts.pop());
            assertEquals(Integer.parseInt(valueTwo), rts.pop());
        } catch (Exception e) {
            Assert.fail("Incorrectly received a StackUnderflowException");
        }
    }

}
