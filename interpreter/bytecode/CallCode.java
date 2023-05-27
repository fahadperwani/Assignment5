package interpreter.bytecode;

import interpreter.VirtualMachine;

public class CallCode extends ByteCode {
    private String functionName; // Name of the function to call
    private int targetAddress; // Target address to jump to
    private String args;

    @Override
    public void init(String[] arguments) {
        // Extract the function name and target address from the arguments array
        functionName = arguments[1];
    }

    @Override
    public void execute(VirtualMachine vm) {
        if (vm.getRuntimeStack().getFrameNumbers() < 2)
            return;
        // Push the return address onto the return address stack
        args = vm.getRuntimeStack().getFrame();
        targetAddress = vm.getLabelAddress(functionName);
        vm.pushReturnAddress(vm.getProgramCounter());

        // Set the program counter to the target address
        vm.setProgramCounter(targetAddress);
        if (vm.getOnFlag())
            System.out.println(toString());
    }

    public String toString() {
        String actual = functionName.replaceAll("<<.*?>>", "");
        StringBuffer buff = new StringBuffer(String.format("%-25s", "CALL " + functionName))
                .append("end " + actual);
        return buff + args;
    }
}
