package de.rares.visnet.api.math.derivative.derivative;


import de.rares.visnet.api.math.derivative.derivative.elements.*;

import java.util.ArrayList;

public class Parser {

    public static void main(String[] args) {
        String func = "(x)^(2)+y";
        String target = "y";
        System.out.println(match(func,target).getDerivative());


    }


    public static Element match(String t, String target) {

        String[] adds = splitAdd(t,target);
        String[] mult = splitMult(t,target);


        if (adds.length != 1) {
            return new Sum(adds,target);
        }
        if (mult.length != 1) {
            System.out.println("here");
            return new Product(mult,target);
        }


        Pair<String[], String> sub;
        sub = substitute(t,target);
        switch (sub.getValue()) {

            case "v^v":
               if(sub.getKey()[1].contains(target)){

                   return new Exp(trimClauses(sub.getKey()[0],target), trimClauses(sub.getKey()[1],target),target);

               }
                return new Pow(trimClauses(sub.getKey()[0],target), trimClauses(sub.getKey()[1],target),target);

            case "Variable":
                return new X(target);

            case "v/v":
                return new Quotient(trimClauses(sub.getKey()[0],target), trimClauses(sub.getKey()[1],target),target);
            case "t":

                return new Const(sub.getKey()[0]);


        }
        return null;

    }


    public static Pair<String[], String> substitute(String s,String target) {

        if(s.equals(target)){
            return new Pair<>(new String[0], "Variable");
        }
        System.out.println(!s.contains(target) + " has to match " + target + "  " + s);
        if(!s.contains(target)){

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

    public static String[] splitAdd(String function,String target) {

        ArrayList<String> res = new ArrayList<String>();
        String last = function;
        int lastAdd = -1;
        for (int i = 0; i < function.length(); i++) {

            if (function.charAt(i) == '+' && InClauses(function, i) == 0) {


                res.add(function.substring(lastAdd+1, i));
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


    public static String[] splitMult(String function,String target) {
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


    public static String trimClauses(String function,String target) {
        System.out.println("to trim " + function);
        String res = function;
        if (function.charAt(0) == '(') {
            if (getClosingClause(function, 0) == function.length() - 1) {
                res = function.substring(1, function.length() - 1);
                res = trimClauses(res,target);
            } else {

            }

        }
        return res;
    }
}
