package droidexpression;

import java.util.LinkedList;

import droidexpression.assistant.Balance;
import droidexpression.assistant.EvaluatePosfix;
import droidexpression.assistant.FunctionOperators;
import droidexpression.assistant.PosfixFunction;

public class Evaluate {

    public static final int RADIANS = 0;
    public static final int DEGREES = 1;

    private String[] varFD = {"x"};
    private String[] varSD = {"x"};
    private String[] literals1 = {"x"};
    private String[] literals2 = {"x"};
    private FunctionOperators p = new FunctionOperators();
    private String[] constants;
    private double[] values;

    public double solve(String function, int unities) {
        if (unities != RADIANS && unities != DEGREES) {
            unities = RADIANS;
        }
        FunctionOperators funcOpe = new FunctionOperators();
        Balance balance = new Balance();
        EvaluatePosfix eva = new EvaluatePosfix();
        PosfixFunction pos = new PosfixFunction();

        double result = 0;
        if (constants != null && values != null) {
            if (constants.length != values.length) {
                return Double.NaN;
            }
        }
        try {
            String function2 = funcOpe.parenthesis(funcOpe.cleanFunction(function));
            String function3 = strLog(funcOpe.functionRepaired(function2));
            if (constants != null && values != null) {
                function3 = functionConstants(funcOpe.functionStrToFunctionArray(function3), constants, values);
            }
            function3 = function3.toLowerCase();
            boolean isBalance = balance.balance(function3);
            boolean acceptedValues = withoutUnacceptableValues(function3);
            boolean acceptedVars = withoutUnacceptableVariables(function3);
            boolean consistent1 = functionConsistent(function3);
            boolean consistent2 = functionConsistent2(function3);
            boolean consistent3 = functionConsistent3(function3);
            if (isBalance && acceptedValues && acceptedVars && consistent1 && consistent2
                    && consistent3) {
                String[] functionArray = funcOpe.functionStrToFunctionArray(function3);
                boolean consistent4 = functionConsistent4(functionArray);
                if (consistent4 == true) {
                    String[] posfixFunctionArray = pos.posfixFunction(functionArray);
                    result = eva.evaluatePosfixExpression(posfixFunctionArray, 0, 0, unities, varFD, varSD);
                } else {
                    result = Double.NaN;
                }
            } else {
                result = Double.NaN;
            }
        } catch (Exception err) {
            result = Double.NaN;
        }
        return result;
    }

    public double solveOneVariable(String function, double var1, int unities) {
        if (unities != RADIANS && unities != DEGREES) {
            unities = RADIANS;
        }
        FunctionOperators funcOpe = new FunctionOperators();
        Balance balance = new Balance();
        EvaluatePosfix eva = new EvaluatePosfix();
        PosfixFunction pos = new PosfixFunction();
        double result = 0;
        if (constants != null && values != null) {
            if (constants.length != values.length) {
                return Double.NaN;
            }
        }
        try {
            String function2 = funcOpe.parenthesis(funcOpe.cleanFunction(function));
            String function3 = strLog(funcOpe.functionRepaired(function2));
            if (constants != null && values != null) {
                function3 = functionConstants(funcOpe.functionStrToFunctionArray(function3), constants, values);
            }
            function3 = function3.toLowerCase();
            boolean isBalance = balance.balance(function3);
            boolean acceptedValues = withoutUnacceptableValues(function3);
            boolean acceptedVars = withoutUnacceptableVariables(function3);
            boolean consistent1 = functionConsistent(function3);
            boolean consistent2 = functionConsistent2(function3);
            boolean consistent3 = functionConsistent3(function3);
            if (isBalance && acceptedValues && acceptedVars && consistent1 && consistent2
                    && consistent3) {
                String[] functionArray = funcOpe.functionStrToFunctionArray(function3);
                boolean consistent4 = functionConsistent4(functionArray);
                if (consistent4 == true) {
                    String[] posfixFunctionArray = pos.posfixFunction(functionArray);
                    result = eva.evaluatePosfixExpression(posfixFunctionArray, var1, 0, unities, varFD, varSD);
                } else {
                    result = Double.NaN;
                }
            } else {
                result = Double.NaN;
            }
        } catch (Exception err) {
            result = Double.NaN;
        }
        return result;
    }

    public double solveTwoVariables(String function, double var1, double var2, int unities) {
        if (unities != RADIANS && unities != DEGREES) {
            unities = RADIANS;
        }
        FunctionOperators funcOpe = new FunctionOperators();
        Balance balance = new Balance();
        EvaluatePosfix eva = new EvaluatePosfix();
        PosfixFunction pos = new PosfixFunction();
        double result = 0;
        if (constants != null && values != null) {
            if (constants.length != values.length) {
                return Double.NaN;
            }
        }
        try {
            String function2 = funcOpe.parenthesis(funcOpe.cleanFunction(function));
            String function3 = strLog(funcOpe.functionRepaired(function2));
            if (constants != null && values != null) {
                function3 = functionConstants(funcOpe.functionStrToFunctionArray(function3), constants, values);
            }
            function3 = function3.toLowerCase();
            boolean isBalance = balance.balance(function3);
            boolean acceptedValues = withoutUnacceptableValues(function3);
            boolean acceptedVars = withoutUnacceptableVariables(function3);
            boolean consistent1 = functionConsistent(function3);
            boolean consistent2 = functionConsistent2(function3);
            boolean consistent3 = functionConsistent3(function3);
            if (isBalance && acceptedValues && acceptedVars && consistent1 && consistent2
                    && consistent3) {
                String[] functionArray = funcOpe.functionStrToFunctionArray(function3);
                boolean consistent4 = functionConsistent4(functionArray);
                if (consistent4 == true) {
                    String[] posfixFunctionArray = pos.posfixFunction(functionArray);
                    result = eva.evaluatePosfixExpression(posfixFunctionArray, var1, var2, unities, varFD, varSD);
                } else {
                    result = Double.NaN;
                }
            } else {
                result = Double.NaN;
            }
        } catch (Exception err) {
            result = Double.NaN;
        }
        return result;
    }

    public void setConstants(String[] constants, double[] values) {
        this.constants = constants;
        this.values = values;
    }

    public boolean balancedFunction(String function) {
        FunctionOperators funcOpe = new FunctionOperators();
        Balance balance = new Balance();
        return balance.balance(funcOpe.cleanFunction(function));
    }

    public void addAcceptableFirstDependency(String[] vars) {
        if (vars != null) {
            varFD = vars;
        } else {
            varFD = literals1;
        }
    }

    public void addAcceptableSecondDependency(String[] vars) {
        if (vars != null) {
            varSD = vars;
        } else {
            varSD = literals2;
        }
    }

    //The functions only contains letters, operators, and numbers
    public boolean withoutUnacceptableValues(String function) {
        FunctionOperators funcOpe = new FunctionOperators();
        function = funcOpe.cleanFunction(function);
        for (int i = 0; i < function.length(); i++) {
            char c = function.charAt(i);
            if (isNumber(c) == false && isUppercase(c) == false && isLowercase(c) == false
                    && isOperator(c) == false) {
                return false;
            }
        }
        return true;
    }

    public String[] unacceptableValues(String function) {
        FunctionOperators funcOpe = new FunctionOperators();
        function = funcOpe.cleanFunction(function);
        String[] vU = null;
        LinkedList<Character> l = new LinkedList();
        LinkedList<String> founds = new LinkedList();
        for (int i = 0; i < function.length(); i++) {
            char c = function.charAt(i);
            if (isNumber(c) == false && isUppercase(c) == false && isLowercase(c) == false
                    && isOperator(c) == false && find(founds, "" + c) == false) {
                l.add(c);
                founds.add("" + c);
            }
        }
        vU = new String[l.size()];
        for (int i = 0; i < l.size(); i++) {
            vU[i] = "" + l.get(i);
        }
        return vU;
    }

    public boolean withoutUnacceptableVariables(String function) {
        boolean varFound = false;
        FunctionOperators funcOpe = new FunctionOperators();
        function = funcOpe.cleanFunction(function);
        LinkedList<String> list = variables(function);
        String[] vecVar = attachVectorsConstantsAndVars();
        for (int i = 0; i < list.size(); i++) {
            String var = list.get(i);
            if (p.isTrigonometricFunction(var) == false) {
                for (int j = 0; j < vecVar.length; j++) {
                    if (var.toLowerCase().equals(vecVar[j]) && findInConstants(vecVar[j]) == false) {
                        varFound = true;
                        j = vecVar.length;
                    }
                }
                if (findInConstants(var) == true) {
                    varFound = true;
                }
                if (varFound == false) {
                    return false;
                }
                varFound = false;
            }
        }
        return true;
    }

    //return unacceptable variables from function
    public String[] unacceptableVariables(String function) {
        boolean varFound = false;
        LinkedList<String> variablesU = new LinkedList();
        FunctionOperators funcOpe = new FunctionOperators();
        function = funcOpe.cleanFunction(function);
        String[] varUna = null;
        LinkedList<String> list = variables(function);
        String[] vecVar = attachVectorsConstantsAndVars();
        LinkedList<String> founds = new LinkedList();
        for (int i = 0; i < list.size(); i++) {
            String var = list.get(i);
            if (p.isTrigonometricFunction(var) == false) {
                for (int j = 0; j < vecVar.length; j++) {
                    if (var.toLowerCase().equals(vecVar[j]) && findInConstants(vecVar[j]) == false) {//variable
                        varFound = true;
                        j = vecVar.length;
                    }
                }
                if (findInConstants(var) == true) {
                    varFound = true;
                }
                if (varFound == false && find(founds, var) == false) {
                    variablesU.add(var);
                    founds.add(var);
                }
                varFound = false;
            }
        }

        varUna = new String[variablesU.size()];
        for (int i = 0; i < variablesU.size(); i++) {
            varUna[i] = "" + variablesU.get(i);
        }
        return varUna;
    }

    //Find empty grouper ()
    private boolean functionConsistent(String function) {
        if (function.length() == 0) {
            return false;
        }
        boolean ok = true;
        //() -> generate err
        for (int i = 0; i < function.length() - 1; i++) {
            char c1 = function.charAt(i);
            char c2 = function.charAt(i + 1);
            if (c1 == '(' && c2 == ')') {
                ok = false;
                break;
            }
        }
        return ok;
    }

    //The should not go operators at final and data how +) o *), etc
    private boolean functionConsistent2(String function) {
        if (function.length() == 0) {
            return false;
        }
        boolean ok = true;
        //cos(x) + -> generate a error
        char lastToken = function.charAt(function.length() - 1);
        if (isOperatorStr("" + lastToken)) {
            return false;
        }
        if (function.length() > 1) {
            for (int i = 0; i < function.length() - 1; i++) {
                char current_c = function.charAt(i);
                char next_c = function.charAt(i + 1);
                if (next_c == ')' && isOperatorStr("" + current_c)) {
                    return false;
                }
            }
        }
        return ok;
    }

    //The should not go operators how **, /*, +/, &^, etc
    private boolean functionConsistent3(String function) {
        if (function.length() == 0) {
            return false;
        }
        boolean ok = true;
        if (function.length() > 1) {
            for (int i = 0; i < function.length() - 1; i++) {
                char currentToken = function.charAt(i);
                char nextToken = function.charAt(i + 1);
                if ((currentToken == '/' && nextToken == '/')
                        || (currentToken == '*' && nextToken == '*')
                        || (currentToken == '%' && nextToken == '%')
                        || (currentToken == '^' && nextToken == '^')
                        || (currentToken == '/' && nextToken == '*')
                        || (currentToken == '/' && nextToken == '^')
                        || (currentToken == '/' && nextToken == '%')
                        || (currentToken == '*' && nextToken == '/')
                        || (currentToken == '*' && nextToken == '%')
                        || (currentToken == '*' && nextToken == '^')
                        || (currentToken == '%' && nextToken == '*')
                        || (currentToken == '%' && nextToken == '/')
                        || (currentToken == '%' && nextToken == '^')
                        || (currentToken == '^' && nextToken == '*')
                        || (currentToken == '^' && nextToken == '/')
                        || (currentToken == '^' && nextToken == '%')
                        || (currentToken == '+' && nextToken == '*')
                        || (currentToken == '+' && nextToken == '/')
                        || (currentToken == '+' && nextToken == '%')
                        || (currentToken == '+' && nextToken == '^')
                        || (currentToken == '-' && nextToken == '*')
                        || (currentToken == '-' && nextToken == '/')
                        || (currentToken == '-' && nextToken == '%')
                        || (currentToken == '-' && nextToken == '^')
                        || (currentToken == '(' && nextToken == '^')
                        || (currentToken == '(' && nextToken == '*')
                        || (currentToken == '(' && nextToken == '%')
                        || (currentToken == '(' && nextToken == '/')) {
                    return false;
                }
            }
        }
        return ok;
    }

    private boolean functionConsistent4(String[] function) {
        if (function.length == 0) {
            return false;
        }
        if (p.isTrigonometricFunction(function[function.length - 1])) {
            return false;
        }
        if (isOperator2Str(function[0])) {
            return false;
        }
        if (isOperatorStr(function[function.length - 1])) {
            return false;
        }
        boolean ok = true;
        for (int i = 0; i < function.length - 1; i++) {
            String str1 = function[i];
            String str2 = function[i + 1];
            if (p.isTrigonometricFunction(str1) == true && str2.equals("(") == false) {
                ok = false;
                break;
            }
        }
        return ok;
    }

    //'consts' and 'values', have the same length
    private String functionConstants(String[] functionArray, String[] consts,
                                     double[] values) {
        String[] newFunctionArray = new String[functionArray.length];
        for (int i = 0; i < functionArray.length; i++) {
            String str = functionArray[i];
            boolean found = false;
            int index = 0;
            //found the const
            for (int c = 0; c < consts.length; c++) {
                String cons = consts[c];
                if (cons.equals(str)) {
                    index = c;
                    found = true;
                    c = consts.length;
                }
            }
            if (found == true) {
                newFunctionArray[i] = "" + values[index];
            } else if (found == false) {
                newFunctionArray[i] = functionArray[i];
            }
        }
        String function = "";
        for (int i = 0; i < newFunctionArray.length; i++) {
            function += newFunctionArray[i];
        }
        return function;
    }

    private boolean find(LinkedList<String> list, String value) {
        for (int i = 0; i < list.size(); i++) {
            String v = list.get(i);
            if (v.equals(value)) {
                return true;
            }
        }
        return false;
    }

    private boolean findInConstants(String var) {
        if (constants != null) {
            for (int i = 0; i < constants.length; i++) {
                if (var.equals(constants[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    //Apart all variables, include the trascendents
    private LinkedList<String> variables(String function) {
        LinkedList<String> lv = new LinkedList();
        String acum = "";
        function += "*";//flag
        for (int i = 0; i < function.length(); i++) {
            char x = function.charAt(i);
            if (isLowercase(x) || isUppercase(x)) {
                acum += x;
            } else {
                if (!acum.equals("")) {
                    lv.add(acum);
                }
                acum = "";
            }
        }
        return lv;
    }

    private String strLog(String function) {
        String newStr = "";
        String strAssis = "";
        LinkedList<String> strList = functionByParts(function);
        int countLog = 0;
        for (int i = 0; i < strList.size(); i++) {
            String c = (String) strList.get(i);
            if (c.equals("log")) {
                countLog++;
            }
        }
        for (int i = 0; i < countLog; i++) {
            strAssis = strFunctionLog(function);
            function = strAssis;
        }
        newStr = function;
        return newStr;
    }

    // log(5, x) -> log5(x)
    private String strFunctionLog(String function) {
        LinkedList<String> strList = functionByParts(function);
        String newFunction = "";
        for (int i = 0; i < strList.size(); i++) {
            String var = strList.get(i);
            if (var.equals("log")) {
                String leftPar = "", base = "", coma = "", val = "";
                try {
                    leftPar = strList.get(i + 1);
                    base = strList.get(i + 2);
                    coma = strList.get(i + 3);
                    val = strList.get(i + 4);
                } catch (Exception err) {
                }
                i += 4;//skip previous values (leftPar + base + coma + val)
                if (leftPar.equals("(") && isNumberStr(base) && coma.endsWith(",")) {
                    String fL = "log" + base + "(" + val;
                    newFunction += fL;
                } else if (leftPar.equals("(")) {
                    newFunction += "log" + "(";
                    i -= 3;//skip all except '('
                } else {
                    newFunction += "#";//generate the error
                }
            } else {
                newFunction += var;
            }
        }
        return newFunction;
    }

    private LinkedList<String> functionByParts(String function) {
        LinkedList<String> lv = new LinkedList();
        String acum = "";
        function += "*";//flag
        for (int i = 0; i < function.length(); i++) {
            char x = function.charAt(i);
            if (isLowercase(x) || isUppercase(x) || isNumber(x) || x == '.') {
                acum += x;
            } else {
                if (!acum.equals("")) {
                    lv.add(acum);
                }
                lv.add("" + x);
                acum = "";
            }
        }
        lv.removeLast();//remove flag
        return lv;
    }

    //Attach constants and variables in one vector
    private String[] attachVectorsConstantsAndVars() {
        String[] nV = null;
        if (constants != null) {
            nV = new String[varFD.length + varSD.length + constants.length];
        } else {
            nV = new String[varFD.length + varSD.length];
        }
        int count = 0;
        for (int i = 0; i < varFD.length; i++) {
            nV[count] = varFD[i].toLowerCase();
            count++;
        }
        for (int i = 0; i < varSD.length; i++) {
            nV[count] = varSD[i].toLowerCase();
            count++;
        }
        if (constants != null) {
            for (int i = 0; i < constants.length; i++) {
                nV[count] = constants[i];
                count++;
            }
        }
        return nV;
    }

    private boolean isNumber(char c) {
        for (char i = '0'; i <= '9'; i++) {
            if (c == i) {
                return true;
            }
        }
        return false;
    }

    private boolean isNumberStr(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!isNumber(c) && c != '.') {
                return false;
            }
        }
        return true;
    }

    private boolean isLowercase(char c) {
        for (int i = 'a'; i <= 'z'; i++) {
            if (c == i) {
                return true;
            }
        }
        if (c == 'ñ') {
            return true;
        }
        return false;
    }

    private boolean isUppercase(char c) {
        for (int i = 'A'; i <= 'Z'; i++) {
            if (c == i) {
                return true;
            }
        }
        if (c == 'Ñ') {
            return true;
        }
        return false;
    }

    private boolean isOperator(char c) {
        if (c == '+' || c == '-' || c == '*' || c == '/' || c == '%'
                || c == '^' || c == '(' || c == ')' || c == '.'
                || c == '[' || c == ']' || c == ',') {
            return true;
        }
        return false;
    }

    private boolean isOperatorStr(String c) {
        if (c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/")
                || c.equals("%") || c.equals("^") || c.equals(".") || c.equals(",")) {
            return true;
        }
        return false;
    }

    private boolean isOperator2Str(String c) {
        if (c.equals("*") || c.equals("/")
                || c.equals("%") || c.equals("^") || c.equals(".") || c.equals(",")) {
            return true;
        }
        return false;
    }

}
