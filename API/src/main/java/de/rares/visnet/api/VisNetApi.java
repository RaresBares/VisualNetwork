package de.rares.visnet.api;


import de.rares.visnet.api.math.derivative.derivative.Derivative;


public class VisNetApi {


    public VisNetApi() {


    }

    public static void main(String[] args) {


           String function = "(x)^(3)";
        /   String pos = "3.2";
           System.out.println(new Derivative(function).getDerivative("x").replaceAll("x", pos));
     //   System.out.println( Parser.eval(new Derivative(function).getDerivative("x").replaceAll("x", pos)).getValue());


    }


}
