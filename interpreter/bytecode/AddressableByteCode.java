package interpreter.bytecode;

import interpreter.VirtualMachine;

public abstract class AddressableByteCode extends ByteCode {
    protected int address;

    public abstract void setAddress(int address);

    public int getAddress() {
        return address;
    }

    @Override
    public abstract void execute(VirtualMachine virtualMachine);
}
