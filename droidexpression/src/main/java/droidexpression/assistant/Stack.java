package droidexpression.assistant;

public class Stack<T> {

    private Object[] stack;
    private int index = -1;

    public void createStack(int size) {
        stack = new Object[size];
    }

    public int sizeStack() {
        return stack.length;
    }

    public boolean fullStack() {
        if (index != stack.length - 1) {
            return false;
        }
        return true;
    }

    public boolean emptyStack() {
        if (index == -1) {
            return true;
        }
        return false;
    }

    public void push(T value) {
        if (!fullStack()) {
            index++;
            stack[index] = value;
        }
    }

    public T pop() {
        T pop = null;
        if (!emptyStack()) {
            pop = (T) stack[index];
            index--;
        }
        return pop;
    }

    public T last() {
        if (!emptyStack()) {
            return (T) stack[index];
        }
        return null;
    }

    public void cleanStack() {
        index = -1;
    }

}
