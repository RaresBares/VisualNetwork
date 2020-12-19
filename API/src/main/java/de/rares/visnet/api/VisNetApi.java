package de.rares.visnet.api;


import de.rares.visnet.api.math.structures.NumberType;
import de.rares.visnet.api.math.structures.vector.Vector;
import de.rares.visnet.api.math.structures.vector.VectorType;
import de.rares.visnet.api.neuronalnetwork.activationfunction.ActivationFunction;
import de.rares.visnet.api.neuronalnetwork.normal.NormalNeuronalNetwork;
import de.rares.visnet.api.neuronalnetwork.normal.element.ElementType;
import de.rares.visnet.api.neuronalnetwork.normal.element.layer.Layer;


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
        Vector v = new Vector(4, VectorType.COLUMN, NumberType.REAL);
        v.content = new Double[]{1.0,2.0,3.0,4.0};
         Layer input = new Layer(4, ActivationFunction.Sigmoid,ElementType.INPUT);
         Layer output = new Layer(input,new Vector[]{v} ,ActivationFunction.Sigmoid, ElementType.HIDDEN);
         Layer[] layer  = new Layer[]{input,output};
         NormalNeuronalNetwork network = new NormalNeuronalNetwork(layer);
        System.out.println(network.convertToFunction()[0]);






            /*

       Gradient g = new Gradient(function, new String[]{"x","y"});
        g.output();
        HashMap<String, Double> coords = new HashMap<>();
        coords.put("x", 2.0);
         coords.put("y",0.0);

        Runner r = new Runner(g,coords);
        r.optimize(100);
        System.out.println(r.getCoord("x") + "    " + r.getCoord("y") + "     " );
*/
    }

}
