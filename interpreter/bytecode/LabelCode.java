package interpreter.bytecode;

import java.util.List;

import interpreter.VirtualMachine;

public class LabelCode extends ByteCode {
    private String labelName; // Name of the label

    public LabelCode() {
    }

    public LabelCode(List<String> args) {
        if (args.size() > 0) {
            this.labelName = args.get(0);
        }
    }

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
