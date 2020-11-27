package de.rares.visnet.api.math.diff.derivative.patterns;

import java.util.ArrayList;

public class Dericative {

    public String function;


    public Dericative(String function) {
        this.function = function;
    }

    public String[] splitLinearAdd() {
        ArrayList<String> parts = new ArrayList<String>();
        int waitingClauses = 0;
        int lastpos = 0;
        int pos = 0;
        for (int i = 0; i < function.toCharArray().length; i++) {


            char c = function.charAt(i);

            if (c != '+' && c != '(' && c != ')') {

            } else if (c == '+') {
                if (waitingClauses == 0) {
                    parts.add(function.substring(lastpos, i));
                    lastpos = i + 1;
                    pos++;


                }
            } else if (c == '(') {


                waitingClauses += 1;

            } else if (c == ')') {


                waitingClauses -= 1;


            }

        }
        parts.add(function.substring(lastpos, function.length() - 1));
        String[] res = parts.toArray(new String[parts.size()]);
        return res;
    }

    public String optimizeFunction() {

        String newFunction = "";
        System.out.println(splitLinearAdd().length);
        for (String s : splitLinearAdd()) {

            Dericative dv = new Dericative(s.trim());

            System.out.println("DV: " + dv.trimClauses(true) + " von " + dv.function);
            newFunction += dv.trimClauses(true);
        }
        function = newFunction;
        return function;

    }

    public String trimClauses(boolean deep) {

        int waitingClauses = 0;
        boolean first = false;
        String newFunction = "";
        for (int i = 0; i < function.toCharArray().length; i++) {

            char c = function.charAt(i);

            if (c == '(') {


                waitingClauses += 1;

                if (i == 0 && first == false) {
                    first = true;
                } else if (i != 0 && first == false) {
                    return function;
                }

            } else if (c == ')') {

                waitingClauses--;
                if (first == true && waitingClauses == 0 && i == (function.length() - 1)) {
                    newFunction = function.substring(1, function.length() - 1);
                } else if (first == true && waitingClauses == 0 && i != (function.length() - 1)) {
                    return function;
                }


            }

        }

        if (newFunction != function) {
            if (deep) {
                function = newFunction;
                String deepFunction = trimClauses(false);
                if (newFunction != deepFunction) {
                    function = trimClauses(true);
                    return function;

                }
                return function;
            } else {
                function = newFunction;
                return function;
            }
        }
        return function;

    }

    public String getDerivative() {
        return "";

    }

}
