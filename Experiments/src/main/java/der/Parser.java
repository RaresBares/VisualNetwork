package der;


import der.elements.*;

import java.util.ArrayList;

public class Parser {

    public static void main(String[] args) {
        String func = "(x)^(2)";

        System.out.println(match(func).getDerivative());


    }


    public static Element match(String t) {

        String[] adds = splitAdd(t);
        String[] mult = splitMult(t);


        if (adds.length != 1) {
            return new Sum(adds);
        }
        if (mult.length != 1) {
            System.out.println("here");
            return new Product(mult);
        }


        Pair<String[], String> sub = substitute(t);
        switch (sub.getValue()) {

            case "v^v":
                System.out.println("tof " + sub.getValue() + " is " + sub.getKey()[0] + " and " + sub.getKey()[1]);
                return new Pow(trimClauses(sub.getKey()[0]), trimClauses(sub.getKey()[1]));

            case "x":
                return new X();

            case "v/v":
                return new Quotient(trimClauses(sub.getKey()[0]), trimClauses(sub.getKey()[1]));
            case "t":
                return new Const(sub.getKey()[0]);


        }
        return null;

    }


    public static Pair<String[], String> substitute(String s) {

        if(s.equals("x")){
            return new Pair<>(new String[0], "x");
        }
        System.out.println(!s.contains("x") + " has to match " + s);
        if(!s.contains("x")){
            return new Pair<>(new String[]{s}, "t");
        }

        String sub = "";
        ArrayList<String> elem = new ArrayList<>();
        boolean inbigClause = false;
        int bigClausepos = 0;
        String copfunc = s   + "a";
        for (int i = 0; i < s.toCharArray().length; i++) {
            char c = s.charAt(i);
            System.out.println(c + " is in " + InClauses(s, i) + " clauses and " + (InClauses(s, i) == 0) + " & " + (c != ')'));
            if (InClauses(s, i) == 0 && c != ')'  && InClauses(copfunc, i+1) == 0 ) {

                sub += c;

            }
            if (c == ')') {
                System.out.println("is last: " + (InClauses(s, i) == 1 )+ ( InClauses(copfunc, i+1) == 0));
                if (InClauses(s, i) == 1 &&  InClauses(copfunc, i+1) == 0) {// && InClauses(copfunc, i+1) == 0

                    inbigClause = false;
                    elem.add(s.substring(bigClausepos + 1, i));
                }

            }

            if (c == '(') {
                System.out.println("in " + !inbigClause);
                if (!inbigClause) {
                    inbigClause = true;
                    sub += "v";
                    bigClausepos = i;
                }

            }

        }


        return new Pair<>(elem.toArray(new String[elem.size()]), sub);

    }

    public static String[] splitAdd(String function) {
        System.out.println("reciveds " + function);
        ArrayList<String> res = new ArrayList<String>();
        String last = function;
        int lastAdd = 0;
        for (int i = 0; i < function.length(); i++) {

            if (function.charAt(i) == '+' && InClauses(function, i) == 0) {
                System.out.println(InClauses(function, i) + "foubd at " + i + " in " + function);

                res.add(function.substring(lastAdd, i));
                lastAdd = i;
                last = function.substring(lastAdd + 1);

            } else if (function.charAt(i) == '-') {

                res.add(function.substring(lastAdd, i));
                last = function.substring(lastAdd + 2);
                lastAdd = i + 1;

            }
        }
        System.out.println(function + " is a sum of");

        res.add(last);
        for (String re : res) {
            System.out.println(" | " + re);
        }
        return res.toArray(new String[res.size()]);

    }


    public static String[] splitMult(String function) {
        System.out.println("recived " + function);
        ArrayList<String> res = new ArrayList<String>();
        String last = function;
        int lastAdd = 0;
        for (int i = 0; i < function.length(); i++) {

            if (function.charAt(i) == '*' && InClauses(function, i) == 0) {
                System.out.println("foundmult at " + i + "(" + function.substring(lastAdd, i) + ")");

                res.add(function.substring(lastAdd, i));
                lastAdd = i;
                last = function.substring(lastAdd + 1);

            }/*else if(function.charAt(i) == '/'){

                res.add(function.substring(lastAdd, i));
                last = function.substring(lastAdd + 2, function.length());
                lastAdd = i + 1;

            }*/
        }
        System.out.println(function + " Last is " + last);
        res.add(last);
        return res.toArray(new String[res.size()]);

    }

    public static int InClauses(String function, int pos) {


        int waiting = 0;
        for (int i = 0; i < pos; i++) {

            if (function.charAt(i) == '(') {

                waiting++;
            } else if (function.charAt(i) == ')') {
                waiting--;


            }
        }

        return waiting;


    }

    public static int getClosingClause(String t, int pos) {

            if (t.charAt(pos) == '(') {

                int waiting = 1;
                for (int i = pos + 1; i < t.length(); i++) {

                    if (t.charAt(i) == '(') {

                        waiting++;
                    } else if (t.charAt(i) == ')') {
                        waiting--;

                        if (waiting == 0) {
                            return i;
                        }
                    }
                }


            }

            return -1;

    }


    public static String trimClauses(String function) {
        System.out.println("to trim " + function);
        String res = function;
        if (function.charAt(0) == '(') {
            if (getClosingClause(function, 0) == function.length() - 1) {
                res = function.substring(1, function.length() - 1);
                res = trimClauses(res);
            } else {

            }

        }
        return res;
    }
}
