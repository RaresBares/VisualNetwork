package de.rares.visnet.api.math.derivative.derivative.patterns;

import de.rares.visnet.api.math.derivative.derivative.Derivative;

public class Quotient extends Pattern{
    public Quotient(String partfirst, String secondfirst) {
        super(partfirst, secondfirst);
    }


    public static Quotient of(String function){
        String partfirst = "";
        String partsecond = "";
        int waitingClauses = 0;
        int pos = 0;
        boolean found= false;


        for (int i = 0; i < function.toCharArray().length; i++) {
            char c = function.charAt(i);

            if(c == '('){
              waitingClauses++;
            }else if(c == ')'){
                waitingClauses--;
            }else if(c == '/'){

                if(waitingClauses == 0){
                    pos = i;
                    if(found){
                        throw new IllegalArgumentException("illegal formed Quotient");
                    }
                    found = true;
                }



            }


        }


        return new Quotient(function.substring(1, pos - 1),function.substring( pos + 2, function.length() - 1));

    }

    @Override
    public String getDerivate(String target) {
        String der = "(" + new Derivative(partfirst).getDerivative(target)+ " * (" + partsecond + ") - " + new Derivative(partsecond).getDerivative(target) + " * " + partfirst + ")/((" + partsecond + ") * (" + partsecond + "))";



        return  der;
    }
}
