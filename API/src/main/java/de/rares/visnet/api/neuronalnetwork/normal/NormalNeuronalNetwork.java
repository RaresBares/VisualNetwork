package de.rares.visnet.api.neuronalnetwork.normal;

import de.rares.visnet.api.neuronalnetwork.normal.element.layer.Layer;




public class NormalNeuronalNetwork {
/*

Notice:


weights are named like: w1223
which means: Weight for the connection between the  third neuron in Layer 2 and the 4th Neuron in Layer 3

neurons are named like: n12
which means: the third neuron in the second layer
 */


    public Layer[] structure;
    public int outputDimension;
    public int inputDimension;

    public NormalNeuronalNetwork(Layer[] structure) {
        this.structure = structure;
        outputDimension = structure[structure.length -1].dimension;
        inputDimension = structure[0].dimension;

    }


    public String[] convertToFunction(){
        int layerPos = 0;

        Layer layer = structure[layerPos];
        String[] result = new String[layer.dimension];
        for (int i = 0; i < layer.dimension; i++) {

            String neuron = "sig(";
            for (int j = 0; j < layer.neurons.length; j++) {
                if((j + 1) == layer.neurons.length){
                    neuron += "N" + (layerPos ) + "" + j  + " * "  + "W" + (layerPos ) + "" + j + "" + (layerPos + 1) + "" + i + ")" ;
                }else {
                    neuron += "N" + (layerPos ) + "" + j  + " * "  + "W" + (layerPos ) + "" + j + "" + (layerPos + 1) + "" + i + " + " ;
                }
            }

            result[i] = neuron;

        }

            String[] resu = result;
        for (int i = 0; i < layer.dimension; i++) {
            System.out.println("i" + i);
            resu = convertToFunction(resu,  1 );
        }


        return resu;




    }

    private String[] convertToFunction(String[] prefNeurons,  int layerPos){


            Layer layer = structure[layerPos];
        String[] result = new String[layer.dimension];
        for (int i = 0; i < layer.dimension; i++) {

            String neuron = "sig(";
            for (int j = 0; j < layer.neurons.length; j++) {
              if((j + 1) == layer.neurons.length){
                  neuron += prefNeurons[j] + " * "  + "W" + (layerPos ) + "" + j + "" + (layerPos + 1)  + "" + i + ")" ;
              }else {
                  neuron += prefNeurons[j]   + " * "  + "W" + (layerPos ) + "" + j + "" + (layerPos + 1)  + "" + i + " + " ;
              }
            }

            result[i] = neuron;

        }


            return result;







    }






}
