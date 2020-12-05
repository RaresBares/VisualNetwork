package de.rares.visnet.api.math.derivative.gradient;

import java.util.HashMap;
import java.util.Vector;

public class Runner {


    public Gradient gradient;

    public HashMap<String, Double> coords;

    public Runner(Gradient gradient, HashMap<String, Double> coords) {
        this.gradient = gradient;
        this.coords = coords;
    }



    public HashMap<String, Double> getForce(){}

}
