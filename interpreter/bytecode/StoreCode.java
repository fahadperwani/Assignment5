package interpreter.bytecode;

import java.util.List;

import interpreter.VirtualMachine;

public class StoreCode extends ByteCode {
    private int offset; // Offset for the variable
    private String variable; // Name of the variable
    private int value;

    public StoreCode() {
    }

    public StoreCode(List<String> args) {
        if (args.size() > 0) {
            this.variable = args.get(0);
        }
    }

    // @Override
    public void init(String[] arguments) {
        // Extract the offset and variable name from the arguments array
        offset = Integer.parseInt(arguments[1]);
        variable = arguments[2];
    }

    @Override
    public void execute(VirtualMachine vm) {
        // Get the value from the top of the stack
        value = vm.popFromStack();

        // Calculate the target address
        int targetAddress = vm.getRuntimeStack().getFrameStart() + offset;

        // Store the value at the target address in the runtime stack
        vm.getRuntimeStack().store(targetAddress, value);
        if (vm.getOnFlag())
            System.out.println(toString());
    }

    public String toString() {
        StringBuffer buff = new StringBuffer(String.format("%-25s", "STORE " + offset + " " + variable))
                .append(variable + " = " + value);
        return buff + "";
    }
}
