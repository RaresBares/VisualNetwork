package de.rares.visnet.api;


import de.rares.visnet.api.math.diff.derivative.patterns.Pow;

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



        String function = "(x +2 + 34)^(2)";
        System.out.println(function);
        System.out.println(Pow.of(function).partfirst + "    " + Pow.of(function).partsecond);
      //  DifferentialCalc diff = new DifferentialCalc(function, new String[]{"x"});
    }


}
