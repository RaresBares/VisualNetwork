package de.rares.visnet.api.neuronalnetwork.normal;

import de.rares.visnet.api.neuronalnetwork.normal.element.layer.Layer;

public class NormalNeuronalNetwork {



    public Layer[] structure;
    public int outputDimension;
    public int inputDimension;

    public NormalNeuronalNetwork(Layer[] structure) {
        this.structure = structure;
        outputDimension = structure[structure.length -1].dimension;
        inputDimension = structure[0].dimension;
    }

}
