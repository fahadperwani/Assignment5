package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.List;

public class FalsebranchCode extends ByteCode {
    private String label;

    public FalsebranchCode(List<String> args) {
        if (!args.isEmpty()) {
            this.label = args.get(0);
        }
    }

    public void init(String[] tokens) {
        this.label = tokens[1];
    }

    public void execute(VirtualMachine vm) {
        int value = vm.popFromStack();
        if (value == 0) {
            int branchTarget = getBranchTarget();
            vm.setProgramCounter(branchTarget - 1);
        }
    }

    public int getBranchTarget() {
        return Integer.parseInt(label);
    }

    public String toString() {
        return "FALSEBRANCH " + label;
    }
}
