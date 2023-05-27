package interpreter.bytecode;

import interpreter.VirtualMachine;

public class GotoCode extends ByteCode {
    private String labelName; // Name of the label to jump to
    private int targetAddress; // Target address to jump to

    @Override
    public void init(String[] arguments) {
        labelName = arguments[1];
    }

    @Override
    public void execute(VirtualMachine vm) {
        targetAddress = vm.getLabelAddress(labelName);
        vm.setProgramCounter(targetAddress);
    }
}
