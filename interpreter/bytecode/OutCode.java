package interpreter.bytecode;

import interpreter.VirtualMachine;

public class OutCode extends ByteCode {
    private String flag;

    public void init(String[] arguments) {
        flag = arguments[1];
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.setOnFlag(flag.equals("Y"));
    }

}
