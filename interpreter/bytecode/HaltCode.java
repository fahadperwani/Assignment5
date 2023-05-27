package interpreter.bytecode;

import interpreter.VirtualMachine;

public class HaltCode extends ByteCode {

    public void init(String[] tokens) {
        // Halt code does not require any arguments to be initialized.
    }

    public void execute(VirtualMachine vm) {
        vm.setRunning(false);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "HALT";
    }
}
