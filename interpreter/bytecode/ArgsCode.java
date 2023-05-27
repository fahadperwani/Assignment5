package interpreter.bytecode;

import java.util.List;

import interpreter.VirtualMachine;

public class ArgsCode extends ByteCode {
    private int numArgs; // Number of arguments

    public ArgsCode() {

    }

    public ArgsCode(List<String> args) {
        if (args.size() > 0) {
            this.numArgs = Integer.parseInt(args.get(0));
        }
    }

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

    @Override
    public String toString() {
        return "ARGS " + numArgs;
    }
}
