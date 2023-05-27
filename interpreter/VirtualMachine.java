package interpreter;

import java.util.Stack;

import interpreter.bytecode.*;;

public class VirtualMachine {
    private RuntimeStack runtimeStack;
    private Program program;
    private int programCounter;
    private boolean isRunning;
    private Stack<Integer> returnAddresses;
    private boolean onFlag;

    public VirtualMachine() {
        runtimeStack = new RuntimeStack();
        returnAddresses = new Stack<>();
        program = null;
        programCounter = 0;
        isRunning = false;
        onFlag = false;
    }

    public void setOnFlag(boolean onFlag) {
        this.onFlag = onFlag;
    }

    public boolean getOnFlag() {
        return onFlag;
    }

    public void executeProgram(Program program) {
        this.program = program;
        programCounter = 0;
        isRunning = true;

        while (isRunning) {
            ByteCode bytecode = program.getCode(programCounter);
            bytecode.execute(this);
            programCounter++;
            if (onFlag && !(bytecode instanceof OutCode))
                runtimeStack.dump();
        }
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    // Other methods for accessing/manipulating the runtime stack

    public RuntimeStack getRuntimeStack() {
        return runtimeStack;
    }

    public int getProgramCounter() {
        return programCounter;
    }

    public void setProgramCounter(int programCounter) {
        this.programCounter = programCounter;
    }

    // Methods for accessing/manipulating the runtime stack

    public int popFromStack() {
        return runtimeStack.pop();
    }

    public void pushToStack(int value) {
        runtimeStack.push(value);
    }

    public int peekFromStack() {
        return runtimeStack.peek();
    }

    // Other methods as needed

    public void jumpToAddress(int address) {
        programCounter = address;
    }

    public int load(int offset) {
        return runtimeStack.load(offset);
    }

    public int getLabelAddress(String label) {
        return program.getLabelAddress(label);
    }

    public void pushReturnAddress(int address) {
        returnAddresses.push(address);
    }

    public int popReturnAddress() {
        return returnAddresses.pop();
    }
}
