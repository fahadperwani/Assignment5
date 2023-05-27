package interpreter;

import java.util.HashMap;
import java.util.Map;

import interpreter.bytecode.*;

public class CodeTable {
    private static Map<String, Class<? extends ByteCode>> codeTable;

    static {
        codeTable = new HashMap<>();
        // Add mappings between bytecode names and their respective classes
        codeTable.put("LIT", LitCode.class);
        codeTable.put("LOAD", LoadCode.class);
        codeTable.put("CALL", CallCode.class);
        codeTable.put("ARGS", ArgsCode.class);
        codeTable.put("GOTO", GotoCode.class);
        codeTable.put("WRITE", WriteCode.class);
        codeTable.put("RETURN", ReturnCode.class);
        codeTable.put("POP", PopCode.class);
        codeTable.put("OUT", OutCode.class);
        codeTable.put("LABEL", LabelCode.class);
        codeTable.put("HALT", HaltCode.class);
        codeTable.put("CALL", CallCode.class);
        codeTable.put("STORE", StoreCode.class);
        codeTable.put("BOP", BopCode.class);
        codeTable.put("READ", ReadCode.class);
        // Add more mappings for other bytecodes as needed
    }

    public static ByteCode getCode(String codeName) {
        try {
            // Get the bytecode class based on the code name from the codeTable
            Class<? extends ByteCode> bytecodeClass = codeTable.get(codeName);
            if (bytecodeClass != null) {
                // Create an instance of the bytecode class using reflection
                return bytecodeClass.getDeclaredConstructor().newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
