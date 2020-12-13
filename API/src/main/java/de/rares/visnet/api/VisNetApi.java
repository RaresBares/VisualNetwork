package de.rares.visnet.api;


import de.rares.visnet.api.math.derivative.gradient.Gradient;


public class VisNetApi {


    public VisNetApi() {


    }

    public static void main(String[] args) {


           String function = "x*y";
        Gradient g = new Gradient(function, new String[]{"x","y"});
        g.output();

    }


}
