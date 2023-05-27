package interpreter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import interpreter.bytecode.*;

public class Program {
    private List<ByteCode> byteCodes;
    private Map<String, Integer> labelAddresses;

    public Program(List<ByteCode> byteCodes) {
        this.byteCodes = byteCodes;
        this.labelAddresses = new HashMap<>();
    }

    public ByteCode getCode(int index) {
        if (index >= 0 && index < byteCodes.size()) {
            return byteCodes.get(index);
        }
        return null;
    }

    public int getSize() {
        return byteCodes.size();
    }

    public void resolveAddresses() {
        for (int i = 0; i < byteCodes.size(); i++) {
            ByteCode bytecode = byteCodes.get(i);
            if (bytecode instanceof LabelCode) {
                labelAddresses.put(((LabelCode) bytecode).getLabelName(), i);
            }
        }
    }

    public int getLabelAddress(String label) {
        return labelAddresses.get(label);
    }
}
