package interpreter;

import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class RuntimeStack {
    private Vector<Integer> runStack;
    private Stack<Integer> framePointers;

    public RuntimeStack() {
        runStack = new Vector<>();
        framePointers = new Stack<>();
        framePointers.push(0); // Initial frame pointer at position 0
    }

    public int peek() {
        if (!runStack.isEmpty()) {
            return runStack.lastElement();
        }
        return 0; // Return 0 if stack is empty (adjust as per your requirements)
    }

    public int pop() {
        if (!runStack.isEmpty()) {
            return runStack.remove(runStack.size() - 1);
        }
        return 0; // Return 0 if stack is empty (adjust as per your requirements)
    }

    public void push(int value) {
        runStack.add(value);
    }

    public void store(int index, int value) {
        runStack.removeElementAt(index);
        runStack.add(index, value);
    }

    public void newFrameAt(int offset) {
        framePointers.push(runStack.size() - offset);
    }

    public String getFrame() {
        List<Integer> lastFrame = runStack.subList(framePointers.peek(), runStack.size());
        return lastFrame.toString().replace("[", "(").replace("]", ")");
    }

    public int popFrame() {
        int returnValue = pop();
        int frameStart = framePointers.pop();
        while (runStack.size() > frameStart) {
            pop();
        }
        push(returnValue);
        return returnValue;
    }

    public int getFrameSize() {
        if (framePointers.size() < 2) {
            return runStack.size();
        }
        int previousFrameStart = framePointers.get(framePointers.size() - 2);
        return runStack.size() - previousFrameStart;
    }

    public int getFrameStart() {
        return framePointers.peek();
    }

    public void dump() {
        int frameCount = framePointers.size();

        for (int i = frameCount - 1; i > 0; i--) {
            int frameStart = framePointers.get(i - 1);
            int frameEnd = framePointers.get(i);
            List<Integer> frame = runStack.subList(frameStart, frameEnd);
            System.out.print(frame + " ");
        }

        List<Integer> lastFrame = runStack.subList(framePointers.peek(), runStack.size());
        System.out.print(lastFrame);

        System.out.println();
        System.out.println();
    }

    public int load(int offset) {
        return runStack.get(framePointers.peek() + offset);
    }

    public int getFrameNumbers() {
        return framePointers.size();
    }

}
