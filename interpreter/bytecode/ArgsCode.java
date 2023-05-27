package interpreter.bytecode;

import interpreter.VirtualMachine;

public class ArgsCode extends ByteCode {
    private int numArgs; // Number of arguments

    @Override
    public void init(String[] arguments) {
        // Extract the number of arguments from the arguments array
        numArgs = Integer.parseInt(arguments[1]);
    }

    @Override
    public void execute(VirtualMachine vm) {
        // Create a new frame with the specified number of arguments
        vm.getRuntimeStack().newFrameAt(numArgs);
    }
}
