package interpreter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import interpreter.bytecode.ByteCode;

public class ByteCodeLoader {
    private String filePath;

    public ByteCodeLoader(String filePath) {
        this.filePath = filePath;
    }

    public Program loadCodes() {
        List<ByteCode> byteCodes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\s+"); // Split line into tokens by whitespace
                String codeName = tokens[0]; // First token is the bytecode name

                // Get bytecode instance from CodeTable based on the bytecode name
                ByteCode bytecode = CodeTable.getCode(codeName);

                if (bytecode != null) {
                    // Pass any necessary parameters to the bytecode instance
                    bytecode.init(tokens);

                    byteCodes.add(bytecode);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create a new program with the loaded bytecodes
        return new Program(byteCodes);
    }
}
