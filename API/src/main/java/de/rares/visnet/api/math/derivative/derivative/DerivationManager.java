package de.rares.visnet.api.math.derivative.derivative;

public class DerivationManager {

    private final Derivative derivative;



    public DerivationManager(String function){

        derivative = new Derivative(function);
    }

    public static String clearClauses(String func){
        return Derivative.trimClauses(func,true);
    }

    public String derive(String refer){
//s
        return derivative.getDerivative(refer);

    }



}
