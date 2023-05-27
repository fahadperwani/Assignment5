package interpreter.bytecode;

import java.util.List;

import interpreter.VirtualMachine;

public class GotoCode extends ByteCode {
    private String labelName; // Name of the label to jump to
    private int targetAddress; // Target address to jump to

    public GotoCode() {
    }

    public GotoCode(List<String> args) {
        if (args.size() > 0) {
            this.targetAddress = Integer.parseInt(args.get(0));
        }
    }

    @Override
    public void init(String[] arguments) {
        labelName = arguments[1];
    }

    @Override
    public void execute(VirtualMachine vm) {
        targetAddress = vm.getLabelAddress(labelName);
        vm.setProgramCounter(targetAddress);
    }

    public int getBranchTarget() {
        return Integer.parseInt(labelName);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "GOTO " + labelName;
    }
}
