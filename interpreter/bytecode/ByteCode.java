package interpreter.bytecode;

import interpreter.VirtualMachine;

public abstract class ByteCode {
    // Abstract method to initialize the bytecode with any necessary arguments
    public abstract void init(String[] arguments);

    // Abstract method to execute the bytecode operation
    public abstract void execute(VirtualMachine virtualMachine);
}
