package droidexpression.assistant;

import java.util.LinkedList;

public class EvaluatePosfix {

    private FunctionOperators p = new FunctionOperators();
    private Mathematics mat = new Mathematics();
    private LinkedList<Double> posfixList = new LinkedList();

    public double evaluatePosfixExpression(String[] expression, double cX, double cY,
                                           int unities, String[] varFD, String[] varSD) {
        String[] auxExpression = expression;
        for (int i = 0; i < auxExpression.length; i++) {
            if (!auxExpression[i].equals("+") && !auxExpression[i].equals("-")
                    && !auxExpression[i].equals("*") && !auxExpression[i].equals("/")
                    && !auxExpression[i].equals("^") && !p.isTrigonometricFunction(auxExpression[i])
                    && !auxExpression[i].equals("%")) {
                //Find variables
                if (checkVariables(varFD, auxExpression[i].toLowerCase())) {
                    double x = cX;
                    posfixList.push(x);
                } else if (checkVariables(varSD, auxExpression[i].toLowerCase())) {
                    double y = cY;
                    posfixList.push(y);
                } else {
                    String z = auxExpression[i];
                    String aux = String.valueOf(z);
                    double r = Double.parseDouble(aux);
                    posfixList.push(r);
                }
            } //Is operator
            else {
                if (!posfixList.isEmpty()) {
                    String oper = auxExpression[i];
                    double result = 0, bValue = 0, aValue = 0;
                    if (!p.isTrigonometricFunction(oper)) {
                        bValue = posfixList.pop();
                        aValue = posfixList.pop();
                        result = posfixOperation(aValue, bValue, oper, unities);
                        posfixList.push(result);
                    } else {
                        bValue = posfixList.pop();
                        result = posfixOperation(0, bValue, oper, unities);
                        posfixList.push(result);
                    }
                }
            }
        }
        double res = posfixList.getLast();
        return res;
    }

    private double posfixOperation(double a, double b, String operator,
                                   int unities) {
        double result = 0;
        char operatorChar = ' ';
        if (operator.equals("^")) {
            operatorChar = '^';
        } else if (operator.equals("*")) {
            operatorChar = '*';
        } else if (operator.equals("/")) {
            operatorChar = '/';
        } else if (operator.equals("%")) {
            operatorChar = '%';
        } else if (operator.equals("+")) {
            operatorChar = '+';
        } else if (operator.equals("-")) {
            operatorChar = '-';
        } else if (p.isTrigonometricFunction(operator)) {
            operatorChar = '?';
        }
        switch (operatorChar) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                result = a / b;
                break;
            case '%':
                result = a % b;
                break;
            case '^':
                result = Math.pow(a, b);
                break;
            case '?':
                result = mat.valueFunction(operator, b, unities);//b is the top
                break;
        }
        if (Double.isNaN(result)) {
            result = Double.POSITIVE_INFINITY;
        }
        return result;
    }

    private boolean checkVariables(String[] variables, String var) {
        boolean itsOk = false;
        for (int i = 0; i < variables.length; i++) {
            String varAux = variables[i].toLowerCase();
            if (varAux.equals(var)) {
                itsOk = true;
                i = variables.length;
            }
        }
        return itsOk;
    }

}
