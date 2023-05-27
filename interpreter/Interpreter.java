package interpreter;

public class Interpreter {
    private Program program;
    private VirtualMachine virtualMachine;

    public Interpreter(String bytecodeFile) {
        // CodeTable.init(); // Initialize the bytecode mapping

        ByteCodeLoader loader = new ByteCodeLoader(bytecodeFile);
        program = loader.loadCodes();

        virtualMachine = new VirtualMachine();
    }

    public void run() {
        program.resolveAddresses(); // Resolve symbolic addresses

        virtualMachine.executeProgram(program); // Execute the program
    }

    public static void main(String[] args) {
        if (args.length == 0) {
        System.out.println("No bytecode file specified.");
        return;
        }

        String bytecodeFile = args[0];
        Interpreter interpreter = new Interpreter(bytecodeFile);
        interpreter.run();
    }
}
