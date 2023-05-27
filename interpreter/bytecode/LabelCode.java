package interpreter.bytecode;

import interpreter.VirtualMachine;

public class LabelCode extends ByteCode {
    private String labelName; // Name of the label

    @Override
    public void init(String[] arguments) {
        labelName = arguments[1];
    }

    @Override
    public void execute(VirtualMachine vm) {
    }

    public String getLabelName() {
        return labelName;
    }
}
