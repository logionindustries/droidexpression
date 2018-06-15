package droidexpression.assistant;

import java.util.LinkedList;

public class FunctionOperators {

    //Convert String function to Array string function
    public String[] functionStrToFunctionArray(String function) {
        String[] function2 = null;
        LinkedList<String> functionArray = new LinkedList();
        String functionFlag = function + "*";//Flag
        String store = "";
        for (int i = 0; i < functionFlag.length(); i++) {
            if (functionFlag.charAt(i) == '+' || functionFlag.charAt(i) == '-'
                    || functionFlag.charAt(i) == '*' || functionFlag.charAt(i) == '/'
                    || functionFlag.charAt(i) == '^' || functionFlag.charAt(i) == '('
                    || functionFlag.charAt(i) == ')' || functionFlag.charAt(i) == '%') {
                functionArray.add(String.valueOf(functionFlag.charAt(i)));
            }
            else {
                for (int k = i; k < functionFlag.length(); k++) {
                    if (functionFlag.charAt(k) != '+' && functionFlag.charAt(k) != '-'
                            && functionFlag.charAt(k) != '*' && functionFlag.charAt(k) != '/'
                            && functionFlag.charAt(k) != '^' && functionFlag.charAt(k) != '('
                            && functionFlag.charAt(k) != ')' && functionFlag.charAt(k) != '%') {
                        store += functionFlag.charAt(k);
                        i++;
                    } else {
                        if(!store.trim().equals("")) {
                            functionArray.add(store.trim());
                        }
                        store = "";
                        i--;
                        k = functionFlag.length();
                    }
                }
            }
        }
        function2 = new String[functionArray.size() - 1];//remove final flag '*'
        for (int i = 0; i < functionArray.size() - 1; i++) {
            function2[i] = functionArray.get(i);
        }
        String[] function3 = removeAmbiguityMultiplication(function2);
        return function3;
    }

    public String cleanFunction(String function) {
        String newFunction = function;
        String assistantStr = "";
        for (int i = 0; i < newFunction.length(); i++) {
            if (newFunction.charAt(i) != ' ' && newFunction.charAt(i) != ':') {
                assistantStr += newFunction.charAt(i);
            }
        }
        newFunction = assistantStr;
        return newFunction;
    }

    //For functions how 2 + (-2) ^ 2
    //Return ---> 2 + (0 - 2) ^ 2
    public String functionRepaired(String strFunction) {
        String newFunction = "";
        String functionB = removeAmbiguityAddSubstraction(strFunction);
        String function = removeAmbiguityContinuedOperators(functionB);
        for (int i = 0; i < function.length(); i++) {
            if (function.charAt(i) == '(') {
                newFunction += function.charAt(i);//concat (
                if (function.charAt(i + 1) == '-') {
                    newFunction += "0";
                } else if (function.charAt(i + 1) == '+') {
                    newFunction += "0";
                }
            } else {
                newFunction += function.charAt(i);
            }
        }
        return newFunction;
    }

    //2(3) -> is valid, it is translated to : 2 * (3)
    //theta(3) -> is valid, it is translated to : theta * (3)
    //(2)(3) -> is valid, it is translated to : (2) * (3)
    private String[] removeAmbiguityMultiplication(String[] functionArray) {
        LinkedList<String> newFunctionArray = new LinkedList();
        String[] newFunction = null;
        for (int i = 0; i < functionArray.length - 1; i++) {
            String str1 = functionArray[i];
            String str2 = functionArray[i + 1];
            newFunctionArray.add(str1);
            if ((isLiteralOrConstantOrNumber(str1) || str1.equals(")")) && str2.equals("(")) {
                newFunctionArray.add("*");
            }
        }
        newFunctionArray.add(functionArray[functionArray.length - 1]);//Add last token
        newFunction = new String[newFunctionArray.size()];
        for (int i = 0; i < newFunctionArray.size(); i++) {
            String str = newFunctionArray.get(i);
            newFunction[i] = str;
        }
        newFunction = removeAmbiguityMultiplication2(newFunction);
        return newFunction;
    }

    private String[] removeAmbiguityMultiplication2(String[] functionArray) {
        LinkedList<String> newFunctionArray = new LinkedList();
        String[] newFunction = null;
        for (int i = 0; i < functionArray.length - 1; i++) {
            String str1 = functionArray[i];
            String str2 = functionArray[i + 1];
            newFunctionArray.add(str1);
            if (str1.equals(")") && isLiteralOrConstOrNumberOrFunction(str2)) {
                newFunctionArray.add("*");
            }
        }
        newFunctionArray.add(functionArray[functionArray.length - 1]);//Add last token
        newFunction = new String[newFunctionArray.size()];
        for (int i = 0; i < newFunctionArray.size(); i++) {
            String str = newFunctionArray.get(i);
            newFunction[i] = str;
        }
        return newFunction;
    }

    private boolean isLiteralOrConstantOrNumber(String token) {
        boolean ok = true;
        if (isTrigonometricFunction(token)) {
            return false;
        }
        for (int i = 0; i < token.length(); i++) {
            char c = token.charAt(i);
            if (!Character.isLetter(c) && !Character.isDigit(c) && c != '.') {
                ok = false;
                break;
            }
        }
        return ok;
    }

    private boolean isLiteralOrConstOrNumberOrFunction(String token) {
        boolean ok = true;
        if (isTrigonometricFunction(token)) {
            return true;
        }
        for (int i = 0; i < token.length(); i++) {
            char c = token.charAt(i);
            if (!Character.isLetter(c) && !Character.isDigit(c) && c != '.') {
                ok = false;
                break;
            }
        }
        return ok;
    }

    public boolean isTrigonometricFunction(String token) {
        if(token == null){
            return false;
        }
        token = token.toLowerCase();
        if (token.equals("sen") || token.equals("sin") || token.equals("cos") || token.equals("tan")
                || token.equals("cot") || token.equals("sec") || token.equals("csc")
                || token.equals("asen") || token.equals("asin") || token.equals("acos") || token.equals("atan")
                || token.equals("acot") || token.equals("asec") || token.equals("acsc")
                || token.equals("senh") || token.equals("sinh") || token.equals("cosh") || token.equals("tanh")
                || token.equals("coth") || token.equals("sech") || token.equals("csch")
                || token.equals("asenh") || token.equals("asinh") || token.equals("acosh") || token.equals("atanh")
                || token.equals("acoth") || token.equals("asech") || token.equals("acsch")
                || token.equals("log") || token.equals("ln") || token.equals("e")
                || token.equals("sqrt") || token.equals("cbrt") || token.equals("abs")
                || token.equals("gd") || token.equals("agd") || token.startsWith("log")
                || token.startsWith("rnd")) {
            return true;
        }
        return false;
    }

    //change [ -> (  and  ] -> )
    public String parenthesis(String function) {
        function = function.replace("[", "(");
        function = function.replace("]", ")");
        return function;
    }

    private String removeAmbiguityAddSubstraction(String function) {
        String newFunction = "";
        function += "*";
        for (int i = 0; i < function.length() - 1; i++) {
            char c = function.charAt(i);
            char c2 = function.charAt(i + 1);
            if (c == '-' && c2 == '-') {
                newFunction += "+";
                i++;
            } else if (c == '-' && c2 == '+') {
                newFunction += "-";
                i++;
            } else if (c == '+' && c2 == '-') {
                newFunction += "-";
                i++;
            } else if (c == '+' && c2 == '+') {
                newFunction += "+";
                i++;
            } else {
                newFunction += c;
            }
        }
        //Check if there are more ambiguities
        boolean status = false;
        for (int i = 0; i < function.length() - 1; i++) {
            char c = function.charAt(i);
            char c2 = function.charAt(i + 1);
            if (c == '-' && c2 == '-') {
                status = true;
                i = function.length();
            } else if (c == '-' && c2 == '+') {
                status = true;
                i = function.length();
            } else if (c == '+' && c2 == '-') {
                status = true;
                i = function.length();
            } else if (c == '+' && c2 == '+') {
                status = true;
                i = function.length();
            }
        }
        if (status == false) {
            return newFunction;
        } else {
            return removeAmbiguityAddSubstraction(newFunction);
        }
    }

    private String removeAmbiguityContinuedOperators(String function) {
        String newFunction = "";
        function += "*";
        char previousCharacter = '?';
        for (int i = 0; i < function.length() - 1; i++) {
            if (isOperator(previousCharacter) && isOperator(function.charAt(i))
                    && function.charAt(i + 1) == '(') {
                int countLeftPar = 1;
                int countRightPar = 0;
                newFunction += "(";
                newFunction += function.charAt(i);
                newFunction += "(";
                int position = i;
                i++;
                for (int j = position + 2; j < function.length(); j++) {
                    char c = function.charAt(j);
                    if (c == '(') {
                        countLeftPar++;
                    }
                    if (c == ')') {
                        countRightPar++;
                    }

                    if (countLeftPar == countRightPar) {
                        newFunction += ")";
                        j = function.length();
                    } else {
                        newFunction += function.charAt(j);
                        i++;
                    }
                }
            } else if (isOperator(previousCharacter) && isOperator(function.charAt(i))
                    && isLiteral(function.charAt(i + 1))) {
                int countLeftPar = 1;
                int countRightPar = 0;
                boolean found = false;
                newFunction += "(";
                newFunction += function.charAt(i);
                int position = i;
                i++;
                for (int j = position + 1; j < function.length(); j++) {
                    char c = function.charAt(j);

                    if (c == '(' && found == true) {
                        countLeftPar++;
                    }
                    if (c == ')') {
                        countRightPar++;
                    }

                    if (countLeftPar == countRightPar) {
                        newFunction += "))";
                        j = function.length();
                    } else {
                        newFunction += function.charAt(j);
                        i++;
                    }

                    if (c == '(' && found == false) {
                        found = true;
                    }
                }

            } else if (isOperator(previousCharacter) && isOperator(function.charAt(i))
                    && function.charAt(i + 1) != '(') {
                newFunction += "(";
                newFunction += function.charAt(i);
                int position = i;
                i++;
                for (int j = position + 1; j < function.length(); j++) {
                    if (isOperator(function.charAt(j)) == false) {
                        newFunction += function.charAt(j);
                        i++;
                    } else {
                        newFunction += ")";
                        j = function.length();
                    }
                }
            } else {
                newFunction += function.charAt(i);
            }
            previousCharacter = function.charAt(i);
        }

        //Check that dont exist more ambiguities
        boolean ok = true;
        previousCharacter = '?';
        for (int i = 0; i < newFunction.length() - 1; i++) {
            if (isOperator(previousCharacter) && isOperator(newFunction.charAt(i))
                    && newFunction.charAt(i + 1) == '(') {
                ok = false;
                i = newFunction.length();
            } else if (isOperator(previousCharacter) && isOperator(newFunction.charAt(i))
                    && isLiteral(newFunction.charAt(i + 1))) {
                ok = false;
                i = newFunction.length();
            } else if (isOperator(previousCharacter) && isOperator(newFunction.charAt(i))
                    && newFunction.charAt(i + 1) != '(') {
                ok = false;
                i = newFunction.length();
            }
            if (ok == true) {
                previousCharacter = newFunction.charAt(i);
            }
        }

        if (ok == true) {
            return newFunction;
        } else {
            return removeAmbiguityContinuedOperators(newFunction);
        }
    }

    private boolean isOperator(char c) {
        boolean itsOk = false;
        if (c == '+' || c == '-' || c == '*' || c == '^'
                || c == '/' || c == '%') {
            itsOk = true;
        }
        return itsOk;
    }

    private boolean isLiteral(char c) {
        boolean itsOk = false;
        if (Character.isLetter(c)) {
            itsOk = true;
        }
        return itsOk;
    }

}
