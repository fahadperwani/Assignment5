package interpreter.bytecode;

import interpreter.VirtualMachine;

public class PopCode extends ByteCode {

    private int numberOfPops;

    public void init(String[] arguments) {
        this.numberOfPops = Integer.parseInt(arguments[1]);
    }

    public void execute(VirtualMachine vm) {
        for (int i = 0; i < numberOfPops; i++) {
            vm.popFromStack();
        }
    }
}
