package interpreter.bytecode;

import java.util.List;

import interpreter.VirtualMachine;

public class PopCode extends ByteCode {

    private int numberOfPops;

    public PopCode() {

    }

    public PopCode(List<String> args) {
        if (args.size() > 0) {
            this.numberOfPops = Integer.parseInt(args.get(0));
        }
    }

    public void init(String[] arguments) {
        this.numberOfPops = Integer.parseInt(arguments[1]);
    }

    public void execute(VirtualMachine vm) {
        for (int i = 0; i < numberOfPops; i++) {
            vm.popFromStack();
        }
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "POP " + numberOfPops;
    }
}
