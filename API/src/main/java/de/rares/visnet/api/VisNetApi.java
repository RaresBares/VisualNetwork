package de.rares.visnet.api;


import de.rares.visnet.api.math.derivative.derivative.Derivative;


public class VisNetApi {


    public VisNetApi() {


    }

    public static void main(String[] args) {




      String function = "x * y".replace(" ", "");



       System.out.println("Ableitung: " + new Derivative(function).getDerivative("y"));




    }


}
