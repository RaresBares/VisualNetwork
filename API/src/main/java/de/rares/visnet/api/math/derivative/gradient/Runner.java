package de.rares.visnet.api.math.derivative.gradient;

import java.util.HashMap;
import java.util.Vector;

public class Runner {


    public Gradient gradient;
    public GradientType type;
    public HashMap<String, Double> coords;

    public Runner(Gradient gradient, HashMap<String, Double> coords) {
        this.gradient = gradient;
        this.coords = coords;
    }


    public void walk(HashMap<String, Double> dir){


    }


    public HashMap<String, Double> getForce(){

        if(type == GradientType.SMOOTH){

            for (String var : coords.keySet()) {
              //  double der = gar;
            }

        }else if(type == GradientType.ZICKZACK){



        }


return null;
    }

}
