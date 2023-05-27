package interpreter.bytecode;

import interpreter.VirtualMachine;

public class WriteCode extends ByteCode {
    @Override
    public void init(String[] arguments) {

    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.peekFromStack();
    }
}
