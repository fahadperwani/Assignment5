package interpreter.bytecode;

import java.util.List;

import interpreter.VirtualMachine;

public class LitCode extends ByteCode {
    private int value; // Value associated with the LIT bytecode instruction
    private String variable;

    public LitCode() {
    }

    public LitCode(List<String> args) {
        if (args.size() > 0) {
            this.value = Integer.parseInt(args.get(0));
        }
    }

    @Override
    public void init(String[] arguments) {
        // Extract the value argument from the arguments array and convert it to an
        // integer
        value = Integer.parseInt(arguments[1]);
        if (arguments.length == 3)
            variable = arguments[2];
    }

    @Override
    public void execute(VirtualMachine vm) {
        // Push the value onto the runtime stack of the virtual machine
        vm.pushToStack(value);
        if (vm.getOnFlag())
            System.out.println(toString());
    }

    public String toString() {
        StringBuffer buff = new StringBuffer(
                String.format("%-25s", "LIT " + value + " " + (variable != null ? variable : ""))).append("int ");
        if (variable == null)
            return buff.append((value)) + "";
        else
            return buff + variable + " = " + value;
    }
}
