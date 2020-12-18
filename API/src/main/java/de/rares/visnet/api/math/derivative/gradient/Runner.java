 package de.rares.visnet.api.math.derivative.gradient;

 import de.rares.visnet.api.math.calculator.Parser;

 import java.util.HashMap;

public class Runner {


    public Gradient gradient;
    public GradientType type = GradientType.SMOOTH;
    private final HashMap<String, Double> coords;
    public Double steplentgh = 0.2;


    public String getCoord(String t){

        return String.format("%.7f", coords.get(t)).replace(",", ".") ;


    }

    public Double getCoordValue(String t){

        return coords.get(t) ;


    }

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

           /* System.out.println("x-force " + force.get("x") + " in " +  coords.get("x") );
            System.out.println("y-force " + force.get("y") + " in " +  coords.get("y")  );*/
            coords.put(s, coords.get(s) + (sigma(force.get(s)) * steplentgh)); //  coords.put(s, coords.get(s) + (  force.get(s) * steplentgh));
            System.out.println("mod of  " + s + "= " + (sigma(force.get(s)) * steplentgh));
            System.out.println("Coord of " + s + "= " + coords.get(s) + (sigma(force.get(s)) * steplentgh));


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

                String der = gradient.derivations.get(var);
                System.out.println("Ableitung " + der);
                for (String cor : coords.keySet()) {
                    System.out.println("replacing " + cor + " with " + coords.get(cor) + " in " + der)
                    ;
                    der = der.replace(cor, String.format("%.12f", coords.get(cor)).replace(",", ".") + "");
                }


                Double res = -1 * Parser.eval(der).getValue();

                if (res == 0) {
                    res = 0.0;
                }

                System.out.println("Ergebnis von " + der + " is " + res);
                System.out.println(var + "  " + res);
                force.put(var, res);

            }

        }else if(type == GradientType.ZICKZACK){



        }


return force;
    }

}
