package tests.requirement_4;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import interpreter.bytecode.CallCode;
import interpreter.bytecode.FalsebranchCode;
import interpreter.bytecode.GotoCode;
import org.junit.Test;

import interpreter.Program;
import interpreter.bytecode.*;
import java.util.*;

public class ProgramTest {

    @Test
    public void testResolveSymbolicAddresses() {
        String target0 = "jdfhg<<45>>";
        String target1 = "sdfghq<<42>>";
        String target2 = "dfsdt<<314>>";

        List<ByteCode> list = new ArrayList<>();

        list.add(new LabelCode(Arrays.asList(target0)));
        list.add(new LabelCode(Arrays.asList(target1)));
        list.add(new LabelCode(Arrays.asList(target2)));
        list.add(new CallCode(Arrays.asList(target2)));
        list.add(new GotoCode(Arrays.asList(target1)));
        list.add(new FalsebranchCode(Arrays.asList(target0)));
        Program program = new Program(list);

        program.resolveAddresses();

        assertEquals(((CallCode) program.getCode(3)).getBranchTarget(), 2);
        assertEquals(((GotoCode) program.getCode(4)).getBranchTarget(), 1);
        assertEquals(((FalsebranchCode) program.getCode(5)).getBranchTarget(), 0);
    }
}
