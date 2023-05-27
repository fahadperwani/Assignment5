package tests.requirement_1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;

import interpreter.bytecode.FalsebranchCode;
import org.junit.Test;

import interpreter.Program;
import interpreter.VirtualMachine;
//import interpreter.bytecode.FalsebranchCode;
import interpreter.bytecode.HaltCode;
import interpreter.bytecode.LabelCode;
import interpreter.bytecode.LitCode;
import tests.helpers.VMHelper;

/**
 * PRE-CONDITIONS:
 *
 * Byte Codes Working:
 * - HaltCode (to stop the VM used to test)
 * - LabelCode (branch target for FalseBranch)
 * - LitCode (stack set up for return)
 *
 * Objects implemented:
 * - Program (resolveSymbolicAddresses must be implemented)
 * - RunTimeStack (using a VM to test which requires a runtime stack)
 */
import java.util.*;
import interpreter.bytecode.*;;

public class FalseBranchCodeTest {

    @Test
    public void testFalseBranchCodeOnFalse() throws IllegalArgumentException, IllegalAccessException {
        String label = "rgb<<000>>";

        List<ByteCode> list = new ArrayList<>();
        list.add(new LitCode(Arrays.asList("0")));
        list.add(new FalsebranchCode(Arrays.asList(label)));
        list.add(new HaltCode());
        list.add(new LabelCode(Arrays.asList(label)));
        list.add(new HaltCode());
        Program program = new Program(list);

        program.resolveAddresses();

        VirtualMachine vm = new VirtualMachine();
        vm.executeProgram(program);

        int pc = VMHelper.getPc(vm);

        assertEquals(5, pc);
    }

    @Test
    public void testFalseBranchCodeOnTrue() throws IllegalArgumentException, IllegalAccessException {
        String label = "rgb<<000>>";

        List<ByteCode> list = new ArrayList<>();
        list.add(new LitCode(Arrays.asList("1")));
        list.add(new FalsebranchCode(Arrays.asList(label)));
        list.add(new HaltCode());
        list.add(new LabelCode(Arrays.asList(label)));
        list.add(new HaltCode());
        Program program = new Program(list);

        program.resolveAddresses();
        VirtualMachine vm = new VirtualMachine();
        vm.executeProgram(program);

        int pc = VMHelper.getPc(vm);

        assertEquals(3, pc);
    }
}
