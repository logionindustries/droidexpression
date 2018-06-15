package droidexpression.assistant;

import java.util.LinkedList;

public class PosfixFunction {

    private FunctionOperators posfixPrefix = new FunctionOperators();
    private Stack<String> stackPosfixCharacters = new Stack<String>();
    private LinkedList<String> posfixExpression = new LinkedList();

    public String[] posfixFunction(String[] functionArray) {
        stackPosfixCharacters.createStack(functionArray.length);
        String[] posfixArray;
        for (int i = 0; i < functionArray.length; i++) {
            //Operands (numbers, vars)
            if (!functionArray[i].equals("(") && !functionArray[i].equals("^") && !functionArray[i].equals("*") && !functionArray[i].equals("/")
                    && !functionArray[i].equals("+") && !functionArray[i].equals("-") && !functionArray[i].equals(")")
                    && !posfixPrefix.isTrigonometricFunction(functionArray[i]) && !functionArray[i].equals("%")) {
                posfixExpression.add(functionArray[i]);
            } //Operators
            else if (functionArray[i].equals("(") || functionArray[i].equals("^")
                    || functionArray[i].equals("*") || functionArray[i].equals("/")
                    || functionArray[i].equals("+") || functionArray[i].equals("-")
                    || posfixPrefix.isTrigonometricFunction(functionArray[i])
                    || functionArray[i].equals("%")) {
                if (stackPosfixCharacters.emptyStack()) {
                    stackPosfixCharacters.push(functionArray[i]);
                } else if (!stackPosfixCharacters.emptyStack()) {
                    int x = priorityOutStack(functionArray[i]);
                    int y = priorityInStack(stackPosfixCharacters.last());
                    if (x > y) {
                        stackPosfixCharacters.push(functionArray[i]);
                    } else if (x <= y) {
                        do {
                            String s = stackPosfixCharacters.pop();
                            posfixExpression.add(s);
                        } while ((priorityOutStack(functionArray[i]) <= priorityInStack(stackPosfixCharacters.last())));
                        stackPosfixCharacters.push(functionArray[i]);
                    }
                }
            } //Right parenthesis
            else if (functionArray[i].equals(")")) {
                String grouperC = "";
                do {
                    if (!stackPosfixCharacters.last().equals("(")) {
                        grouperC = stackPosfixCharacters.pop();
                        posfixExpression.add(grouperC);
                    }
                } while (!stackPosfixCharacters.last().equals("("));

                if (stackPosfixCharacters.last().equals("(")) {
                    stackPosfixCharacters.pop();
                }
                //After get all tokens into parenthesis, quest if the last element
                //is a trigonometric function, in that case add to posfix
                if (posfixPrefix.isTrigonometricFunction(stackPosfixCharacters.last())) {
                    String ft = stackPosfixCharacters.pop();
                    posfixExpression.add(ft);
                }
            }
        }
        if (!stackPosfixCharacters.emptyStack()) {
            for (int au = 0; au < stackPosfixCharacters.sizeStack(); au++) {
                String x = stackPosfixCharacters.pop();
                if (x != null && !x.equals("")) {
                    posfixExpression.add(x);
                }
            }
        }
        posfixArray = new String[posfixExpression.size()];
        for (int n = 0; n < posfixExpression.size(); n++) {
            posfixArray[n] = posfixExpression.get(n);
        }
        stackPosfixCharacters.cleanStack();
        return posfixArray;
    }

    private int priorityInStack(String sData) {
        int prior = 0;
        char cData = ' ';
        if (sData.equals("^")) {
            cData = '^';
        } else if (sData.equals("*")) {
            cData = '*';
        } else if (sData.equals("/")) {
            cData = '/';
        } else if (sData.equals("%")) {
            cData = '%';
        } else if (sData.equals("+")) {
            cData = '+';
        } else if (sData.equals("-")) {
            cData = '-';
        } else if (sData.equals("(")) {
            cData = '(';
        } else if (posfixPrefix.isTrigonometricFunction(sData)) {
            cData = '?';
        }

        switch (cData) {
            case '^':
                prior = 3;
                break;
            case '*':
            case '/':
            case '%':
                prior = 2;
                break;
            case '+':
            case '-':
                prior = 1;
                break;
            case '(':
                prior = 0;
                break;
            case '?':
                prior = -1;
                break;
        }
        return prior;
    }

    private int priorityOutStack(String sData) {
        int prior = 0;
        char cData = ' ';
        if (sData.equals("^")) {
            cData = '^';
        } else if (sData.equals("*")) {
            cData = '*';
        } else if (sData.equals("/")) {
            cData = '/';
        } else if (sData.equals("%")) {
            cData = '%';
        } else if (sData.equals("+")) {
            cData = '+';
        } else if (sData.equals("-")) {
            cData = '-';
        } else if (sData.equals("(")) {
            cData = '(';
        } else if (posfixPrefix.isTrigonometricFunction(sData)) {
            cData = '?';
        }

        switch (cData) {
            case '^':
                prior = 4;
                break;
            case '*':
            case '/':
            case '%':
                prior = 2;
                break;
            case '+':
            case '-':
                prior = 1;
                break;
            case '(':
                prior = 5;
                break;
            case '?':
                prior = 6;
                break;
        }
        return prior;
    }

}
