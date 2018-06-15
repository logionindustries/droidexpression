package droidexpression.assistant;

import java.util.LinkedList;

public class Balance {

    private LinkedList<Character> balanceList = new LinkedList();

    public boolean balance(String function) {
        String strFunction = function;
        for (int i = 0; i < strFunction.length(); i++) {
            if (strFunction.charAt(i) == '(') {
                balanceList.push(strFunction.charAt(i));
            } else {
                if (strFunction.charAt(i) == ')') {
                    if(balanceList.isEmpty()){
                        return false;
                    }
                    char ch = balanceList.pop();
                    if (ch != '(') {
                        return false;
                    }
                }
            }
        }
        if (balanceList.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

}
