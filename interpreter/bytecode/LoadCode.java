package interpreter.bytecode;

import interpreter.VirtualMachine;

public class LoadCode extends ByteCode {
    private int offset; // Offset of the value in the runtime stack
    private int value;
    private String variable;

    @Override
    public void init(String[] arguments) {
        // Extract the offset argument from the arguments array and convert it to an
        // integer
        offset = Integer.parseInt(arguments[1]);
        if (arguments.length == 3)
            variable = arguments[2];
    }

    @Override
    public void execute(VirtualMachine vm) {
        value = vm.load(offset);
        vm.pushToStack(value);
        if (vm.getOnFlag())
            System.out.println(toString());
    }

    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer(String.format("%-25s", "LOAD " + value + " " + variable))
                .append("load<" + variable + ">");
        return buff + "";
    }
}
