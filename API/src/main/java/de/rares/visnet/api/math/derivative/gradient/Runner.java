 package de.rares.visnet.api.math.derivative.gradient;

 import de.rares.visnet.api.math.calculator.Parser;

 import java.util.HashMap;

public class Runner {


    public Gradient gradient;
    public GradientType type = GradientType.SMOOTH;
    public HashMap<String, Double> coords;
    public Double steplentgh = 1.0;

    public Runner(Gradient gradient, HashMap<String, Double> coords) {
        this.gradient = gradient;
        this.coords = coords;
    }





    public void optimize (int steps){
        for (int i = 0; i < steps; i++) {

            walk(getForce());
        }


    }


    public void walk(HashMap<String, Double> dir){

        for (String s : dir.keySet()) {
            HashMap<String, Double> force = getForce();
            System.out.println("var is "  + s);
            System.out.println("force " + force.get("x"));
            System.out.println("force " + force.get("y"));
            coords.put(s, coords.get(s) + (  sigma(force.get(s)) * steplentgh)); //  coords.put(s, coords.get(s) + (  force.get(s) * steplentgh));
        }


    }


    public int sigma(double d){

        if(d < 0){
            return -1;
        }
        if(d == 0){
            return 0;
        }
        return 1;

    }


    public HashMap<String, Double> getForce(){
        HashMap<String, Double> force = new HashMap<>();
        if(type == GradientType.SMOOTH){

            for (String var : coords.keySet()) {

              String der  = gradient.derivations.get(var);
                System.out.println("der on " + var + " " + "is " + der);
                for (String cor : coords.keySet()) {

                  der =   der.replace(cor, coords.get(cor) + "");
                }


                Double res = -1 * Parser.eval(der).getValue();
               force.put(var,res);

            }

        }else if(type == GradientType.ZICKZACK){



        }


return force;
    }

}
