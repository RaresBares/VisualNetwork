package de.rares.visnet.api.neuronalnetwork.normal;

import de.rares.visnet.api.math.function.Function;
import de.rares.visnet.api.math.structures.vector.Vector;
import de.rares.visnet.api.neuronalnetwork.normal.element.layer.Layer;
import de.rares.visnet.api.neuronalnetwork.normal.element.neuron.Neuron;




public class NormalNeuronalNetwork {



    public Layer[] structure;
    public int outputDimension;
    public int inputDimension;
    public Function nnFunction;
    public NormalNeuronalNetwork(Layer[] structure) {
        this.structure = structure;
        outputDimension = structure[structure.length -1].dimension;
        inputDimension = structure[0].dimension;
    }



    public void convertToFunction(int layerPos){


            Layer layer = structure[layerPos];

            for (int nPos = 0; nPos < layer.dimension; nPos++ ){

                Neuron n = layer.neurons[nPos];
                Vector<Double> neuronWeights = layer.weights[nPos];
                                
            }

    }






}
