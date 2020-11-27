package de.rares.visnet.api.math.diff.derivative.patterns;

import de.rares.visnet.api.image.algorithms.structures.Pair;

import java.util.ArrayList;
import java.util.HashMap;

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
        parts.add(function.substring(lastpos));
        String[] res = parts.toArray(new String[parts.size()]);
        return res;
    }

    public Pair<String, String> extrConst() {
        ArrayList<String> parts = new ArrayList<String>();
        int waitingClauses = 0;
        int lastpos = 0;
        int pos = 0;
        for (int i = 0; i < function.toCharArray().length; i++) {


            char c = function.charAt(i);

            if (c != '*' && c != '(' && c != ')') {

            } else if (c == '*') {
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
        parts.add(function.substring(lastpos));
        String[] res = parts.toArray(new String[parts.size()]);
       String key = "(",value = "(";
        for (int i = 0; i < res.length; i++) {
            String part = res[i];

            if(part.contains("x")){

            value += part + "*";

            }else{
                key += part + "*";
            }

        }

        if(key.endsWith("*")){
            key = key.substring(0, key.length() - 1);
            key += ")";
        }
        if(value.endsWith("*")){
            value = value.substring(0, value.length() - 1);
            value += ")";
        }

        Pair pair = new Pair<String ,String>(key, value);
        return pair;
    }

    public String optimizeFunction() {

        String newFunction = "";

        for (String s : splitLinearAdd()) {

            Dericative dv = new Dericative(s.trim());

            dv.trimClauses(true);


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

    public HashMap<String, Pattern> getPattern(String[] parts){

        for (String part : parts) {

        }

            return null;
    }

    public String getDerivative() {
        return "";

    }

}
