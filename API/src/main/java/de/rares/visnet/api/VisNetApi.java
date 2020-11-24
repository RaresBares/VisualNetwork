package de.rares.visnet.api;


import de.rares.visnet.api.neuronalnetwork.activationfunction.ReLU;
import de.rares.visnet.api.neuronalnetwork.normal.NormalNeuronalNetwork;
import de.rares.visnet.api.neuronalnetwork.normal.element.layer.Layer;

public class VisNetApi {


    public VisNetApi() {


    }

    public static void main(String[] args) {
        Layer layer1 = new Layer(2, new ReLU());
        Layer layer2 = new Layer(2, new ReLU());
        Layer layer3 = new Layer(2, new ReLU());
        NormalNeuronalNetwork network = new NormalNeuronalNetwork(new Layer[]{layer1, layer2, layer3});
        String[] res = network.convertToFunction();
        for (String re : res) {
            System.out.println(re);
        }
    }


}
