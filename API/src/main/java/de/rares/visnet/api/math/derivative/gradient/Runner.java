 package de.rares.visnet.api.math.derivative.gradient;

 import de.rares.visnet.api.math.calculator.Parser;

 import java.util.HashMap;

public class Runner {


    public Gradient gradient;
    public GradientType type = GradientType.SMOOTH;
    public HashMap<String, Double> coords;

    public Runner(Gradient gradient, HashMap<String, Double> coords) {
        this.gradient = gradient;
        this.coords = coords;
    }


    public void walk(HashMap<String, Double> dir){


    }


    public HashMap<String, Double> getForce(){
        HashMap<String, Double> force = new HashMap<>();
        if(type == GradientType.SMOOTH){

            for (String var : coords.keySet()) {

              String der  = gradient.derivations.get(var);
                for (String cor : coords.keySet()) {

                  der =   der.replace(cor, coords.get(cor) + "");
                }


                Double res = Parser.eval(der).getValue();
               force.put(var,res);

            }

        }else if(type == GradientType.ZICKZACK){



        }


return force;
    }

}
