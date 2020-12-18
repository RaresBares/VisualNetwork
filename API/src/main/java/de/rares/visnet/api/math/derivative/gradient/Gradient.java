package de.rares.visnet.api.math.derivative.gradient;



import de.rares.visnet.api.math.derivative.derivative.Parser;

import java.util.HashMap;

public class Gradient {

    private String[] dimensions;
    public String function;
    public HashMap<String, String> derivations = new HashMap<>();

    public Gradient(String function, String[] derivations, String[] dimensions) {

        if (derivations.length == dimensions.length) {
            for (int i = 0; i < derivations.length; i++) {
                this.derivations.put(dimensions[0], derivations[1]);
                this.function = function;
                this.dimensions = dimensions;
            }
        } else {
            throw new IndexOutOfBoundsException("der = dim");
        }


    }

    public Gradient(String function, String[] dimensions) {

        this.function = function;
        this.dimensions = dimensions;
        setupDerivations();


    }

    public void setupDerivations(){

        for (String dimension : dimensions) {
            derivations.put(dimension, Parser.match(function,dimension).getDerivative());
        }
    }

    public void output(){

        for (String s : derivations.keySet()) {
            System.out.println("t: " + s  + " der: " + derivations.get(s) );
        }

    }

    public HashMap<String, Double> getGradient(double[] coords){

return null;
    }

}
