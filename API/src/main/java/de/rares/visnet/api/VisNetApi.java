package de.rares.visnet.api;


import de.rares.visnet.api.math.derivative.gradient.Gradient;
import de.rares.visnet.api.math.derivative.gradient.Runner;

import java.util.HashMap;


public class VisNetApi {


    public VisNetApi() {


    }

   /* public static void main(String[] args) {


           String function = "((x)^(2)) * ((y)^(2))";
        Gradient g = new Gradient(function, new String[]{"x", "y"});
        g.output();
        HashMap<String, Double> coords = new HashMap<>();
        coords.put("x", 4.0);
        coords.put("y", 3.0);
        Runner r = new Runner(g,coords);
        for (String s : r.getForce().keySet()) {
            System.out.println("in " + s  + " force is " + r.getForce().get(s));
        }

    }*/

     public static void main(String[] args) {


         String function = "(e)^((x)^(2))";


       Gradient g = new Gradient(function, new String[]{"x","y"});
        g.output();
        HashMap<String, Double> coords = new HashMap<>();
        coords.put("x", 2.0);
         coords.put("y",0.0);

        Runner r = new Runner(g,coords);
        r.optimize(100);
        System.out.println(r.getCoord("x") + "    " + r.getCoord("y") + "     " );

    }

}
