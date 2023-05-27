package tests.requirement_1;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.Test;

import interpreter.Program;
import interpreter.VirtualMachine;
import interpreter.bytecode.HaltCode;
import tests.helpers.VMHelper;
import java.util.*;
import interpreter.bytecode.*;

public class HaltCodeTest {

    @Test
    public void testHaltCode() throws IllegalArgumentException, IllegalAccessException {
        List<ByteCode> list = new ArrayList<>();
        list.add(new HaltCode());

        Program program = new Program(list);
        program.resolveAddresses();

        VirtualMachine vm = new VirtualMachine();
        vm.executeProgram(program);

        int pc = VMHelper.getPc(vm);
        boolean isRunning = VMHelper.getIsRunning(vm);

        assertEquals(1, pc);
        assertFalse(isRunning);
    }
}
