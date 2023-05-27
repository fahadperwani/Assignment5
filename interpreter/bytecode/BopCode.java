package interpreter.bytecode;

import interpreter.VirtualMachine;

public class BopCode extends ByteCode {
    private String operator;

    public void init(String[] tokens) {
        this.operator = tokens[1];
    }

    public void execute(VirtualMachine vm) {
        // Perform the binary operation based on the operator
        int operand2 = vm.popFromStack();
        int operand1 = vm.popFromStack();

        switch (operator) {
            case "+":
                vm.pushToStack(operand1 + operand2);
                break;
            case "-":
                vm.pushToStack(operand1 - operand2);
                break;
            case "*":
                vm.pushToStack(operand1 * operand2);
                break;
            case "/":
                vm.pushToStack(operand1 / operand2);
                break;
            case "==":
                vm.pushToStack(operand1 == operand2 ? 1 : 0);
                break;
            case "!=":
                vm.pushToStack(operand1 != operand2 ? 1 : 0);
                break;
            case "<":
                vm.pushToStack(operand1 < operand2 ? 1 : 0);
                break;
            case "<=":
                vm.pushToStack(operand1 <= operand2 ? 1 : 0);
                break;
            case ">":
                vm.pushToStack(operand1 > operand2 ? 1 : 0);
                break;
            case ">=":
                vm.pushToStack(operand1 >= operand2 ? 1 : 0);
                break;
            case "|":
                vm.pushToStack(operand1 | operand2);
                break;
            case "&":
                vm.pushToStack(operand1 & operand2);
                break;
        }
    }
}
