package de.rares.visnet.api;


import de.rares.visnet.api.image.algorithms.structures.Pair;
import de.rares.visnet.api.math.diff.derivative.patterns.Dericative;


public class VisNetApi {


    public VisNetApi() {


    }

    public static void main(String[] args) {
       /* Layer layer1 = new Layer(2, new ReLU());
        Layer layer2 = new Layer(2, new ReLU());
        Layer layer3 = new Layer(2, new ReLU());
        NormalNeuronalNetwork network = new NormalNeuronalNetwork(new Layer[]{layer1, layer2, layer3});
        String[] res = network.convertToFunction();
        for (String re : res) {
            System.out.println(re);
        }*/



        String function = "x * x^2 * 2 * 3 *4 ".replace(" ", "");
        Dericative d = new Dericative(function);

        Pair<String, String> p = d.extrConst();
       System.out.println(p.getValue() + " * " + p.getKey());
      //  DifferentialCalc diff = new DifferentialCalc(function, new String[]{"x"});
    }


}
