package de.rares.visnet.api.math.differentials;


import de.rares.visnet.api.math.function.Function;

import java.util.ArrayList;
import java.util.HashMap;

public class DifFunction {


    public Function function;

    public DifFunction(Function function) {
        this.function = function;
    }

    public Function[] part() {

        String gpattern = function.pattern;
        int waitingClauses = 0;
        HashMap<Integer, Integer> openingClauses = new HashMap<>();
        String lpattern = "";
        ArrayList<Function> parts = new ArrayList<>();


        for (int i = 0; i < gpattern.toCharArray().length; i++) {


            char c = gpattern.charAt(i);
            if (c != '+' && c != '(' && c != ')') {


                lpattern += c;

            } else if (c == '+') {

                if (waitingClauses == 0) {
                    parts.add(new Function(lpattern, function.argument));
                    lpattern = "";
                } else {
                    lpattern += c;
                }


            } else if (c == '(') {

                lpattern += c;
                waitingClauses += 1;
                openingClauses.put(waitingClauses, i);
            } else if (c == ')') {

                lpattern += c;
                int pos1 = openingClauses.get(waitingClauses);
                int pos2 = i;
                String subfunc = gpattern.substring(pos1 + 1, pos2);
                System.out.println(subfunc);
                waitingClauses -= 1;

            }


            if ((i + 1) == gpattern.toCharArray().length) {
                parts.add(new Function(lpattern, function.argument));
                lpattern = "";
            }
        }
        return parts.toArray(new Function[parts.size()]);

    }
}
