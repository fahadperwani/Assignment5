package interpreter.bytecode;

import java.util.List;

import interpreter.VirtualMachine;

public class OutCode extends ByteCode {
    private String flag;

    public OutCode() {
    }

    public OutCode(List<String> args) {
        if (args.size() > 0) {
            this.flag = args.get(0);
        }
    }

    public void init(String[] arguments) {
        flag = arguments[1];
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.setOnFlag(flag.equals("Y"));
    }

}
