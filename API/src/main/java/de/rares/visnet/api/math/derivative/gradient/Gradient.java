package de.rares.visnet.api.math.derivative.gradient;

import java.util.HashMap;
import java.util.Vector;

public class Gradient {

    public String function;
    public HashMap<String, String> derivations = new HashMap<>();

    public Gradient(String function, String[] derivations, String[] dimensions) {

        if (derivations.length == dimensions.length) {
            for (int i = 0; i < derivations.length; i++) {
                this.derivations.put(dimensions[0], derivations[1]);
                this.function = function;
            }
        } else {
            throw new IndexOutOfBoundsException("der = dim");
        }


    }

    public HashMap<String, Double> getGradient(double[] coords){


return null;
    }

}
