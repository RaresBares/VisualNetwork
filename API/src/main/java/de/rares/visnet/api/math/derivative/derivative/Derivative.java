package de.rares.visnet.api.math.derivative.derivative;

import de.rares.visnet.api.image.algorithms.structures.Pair;
import de.rares.visnet.api.math.derivative.derivative.patterns.Pattern;
import de.rares.visnet.api.math.derivative.derivative.patterns.Pow;
import de.rares.visnet.api.math.derivative.derivative.patterns.Quotient;
import de.rares.visnet.api.math.derivative.derivative.patterns.X;

import java.util.ArrayList;

public class Derivative {

    public String function;


    public Derivative(String function) {
        this.function = function;
    }

    public static String trimClauses(String function, boolean deep) {


                String f = function;
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
                    return f;
                }

            } else if (c == ')') {

                waitingClauses--;

                if (first == true && waitingClauses == 0 && i == (function.length() - 1)) {
                    newFunction = function.substring(1, function.length() - 1);


                } else if (first == true && waitingClauses == 0 && i != (function.length() - 1)) {
                    return f;
                }else if(first == false){
                    return f;
                }


            }

        }

        if(newFunction == "")
            newFunction = function;



        if (newFunction != function) {
            if (deep) {
                f = newFunction;

                String deepFunction = trimClauses(f, false);

                if (newFunction != deepFunction) {

                    f = trimClauses(f,true);
                    return f;

                }
                return f;
            } else {

                f = newFunction;
                return f;
            }
        }
        return f;

    }

    public String[] splitMultiply(String function) {
        ArrayList<String> parts = new ArrayList<String>();
        int waitingClauses = 0;
        int lastpos = 0;
        int pos = 0;
        for (int i = 0; i < function.toCharArray().length; i++) {


            char c = function.charAt(i);

            if (c != '*' && c != '(' && c != ')') {

            } else if (c == '*') {
                if (waitingClauses == 0) {


                 //   parts.add(function.substring(lastpos, i));

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
        for (String part : parts) {
           if(part == ""){
               res = null;
           }
        }
        return res;
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

    public String optimizeFunction() {

        String newFunction = "";

        for (String s : splitLinearAdd()) {

            Derivative dv = new Derivative(s.trim());

            dv.trimClauses(true);


            newFunction += dv.trimClauses(true);
        }
        function = newFunction;
        return function;

    }

    public Pair<String, String> extrConst(String target) {
    if(!function.contains(target)){
        return new Pair<>(function,"");
    }

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
       String key = "",value = "";
        for (int i = 0; i < res.length; i++) {
            String part = res[i];

            if(part.contains(target)){

            value += part + "*";

            }else{
                key += part + "*";
            }

        }

        if (key.endsWith("*")) {

            key = key.substring(0, key.length() - 1);
            //key += ")";
        }
        if (value.endsWith("*")) {

            value = value.substring(0, value.length() - 1);
          //  value += ")";
        }
        if (value == "()") {

            value = "";
        }

        if (key == "()") {

            key = "";
        }




        Pair pair = new Pair<String, String>(key, value);
        return pair;
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
        if(newFunction == "")
            newFunction = function;


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

    public Pattern getPattern(String f, String target) {

        String patternConv = "";

        try {

            ArrayList<String> parts = new ArrayList<String>();
            int waitingClauses = 0;
            int lastpos = 0;
            int pos = 0;
             patternConv = "";
            for (int i = 0; i < function.toCharArray().length; i++) {


                char c = function.charAt(i);

                if (c != '+' && c != '(' && c != ')') {

                } else if (c == '+') {
                    if (waitingClauses == 0) {
                        parts.add(f.substring(lastpos, i));

                        lastpos = i + 1;
                        pos++;


                    }
                } else if (c == '(') {

                    if (waitingClauses == 0) {
                        if (i >= 1)
                            patternConv += f.charAt(i - 1);
                    }
                    waitingClauses += 1;

                } else if (c == ')') {
                    if (waitingClauses == 1)
                        patternConv += "v";


                    waitingClauses -= 1;


                }


            }
        }catch (Exception e){
            if(f.replace(" ", "") == target){

                patternConv = "v";
            }
        }
        if(f.equals(target)){

            patternConv = "v";
        }
        Pattern pattern = null;
        switch (patternConv) {

            case "v/v":

                pattern = Quotient.of(f);
                break;
            case "v^v":

                pattern = Pow.of(f);
                break;
            case "v":

                pattern = X.of(f);

        }

        return pattern;
    }



    public String getDerivative(String target) {


        if(!function.contains(target)){
            return 0 + "";
        }

        trimClauses(true);

        String derivative = "";
        String[] lin = splitLinearAdd();

        for (String s : lin) {

            Pair<String, String> prod = new Derivative(s).extrConst(target);

            String consts = prod.getKey();


            String depend = prod.getValue(); // x^2 * x^3

          
            String[] mult = splitMultiply(trimClauses(depend, true)); // String[] mult = splitMultiply(trimClauses(depend, true));

            if(mult == null){

            }else {
            if (mult.length == 1) {
               
                Pattern p = getPattern(trimClauses(mult[0], true),target);
        
                derivative =  p.getDerivate(target); //derivative = "" + getPattern(trimClauses(mult[0], true)).getDerivate();

            } else if (mult.length > 1) {


                for (String m : mult) {

                    String mder = new Derivative(m).getDerivative(target);
                    for (String t : mult) {

                        if(t != m ){

                            derivative +=  t+ "*";
                        }else{

                        }

                    }

                    derivative += mder + "+";
                }
                derivative = derivative.substring(0, derivative.length() - 1);


            }}
            if (consts != "") {

            derivative = consts + "* (" +  derivative + ")";
           return derivative;

            }

        }

        return derivative;
    }


}
