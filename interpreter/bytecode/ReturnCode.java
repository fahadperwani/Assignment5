package interpreter.bytecode;

import java.util.List;

import interpreter.VirtualMachine;

public class ReturnCode extends ByteCode {
    private int toReturn;
    private String variable;

    public ReturnCode(List<String> args) {
        if (args.size() > 0) {
            this.variable = args.get(0);
        }
    }

    @Override
    public void init(String[] arguments) {
        // No additional initialization needed for the RETURN bytecode
        if (arguments.length == 2)
            variable = arguments[1];
    }

    @Override
    public void execute(VirtualMachine vm) {
        // Set the program counter to the address popped from the return address stack
        int returnAddress = vm.popReturnAddress();
        vm.setProgramCounter(returnAddress);
        toReturn = vm.getRuntimeStack().popFrame();
        if (vm.getOnFlag())
            System.out.println(toString());
    }

    public String toString() {
        String actual = variable.replaceAll("<<.*?>>", "");
        ;
        StringBuffer buff = new StringBuffer(String.format("%-25s", "RETURN " + variable))
                .append("end " + actual + ": ");
        return buff.append(toReturn) + "";
    }
}
