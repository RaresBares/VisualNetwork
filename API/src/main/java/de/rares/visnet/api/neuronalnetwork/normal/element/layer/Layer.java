package de.rares.visnet.api.neuronalnetwork.normal.element.layer;

import de.rares.visnet.api.math.structures.vector.Vector;
import de.rares.visnet.api.neuronalnetwork.activationfunction.ActivationFunction;
import de.rares.visnet.api.neuronalnetwork.normal.element.ElementType;
import de.rares.visnet.api.neuronalnetwork.normal.element.neuron.Neuron;



public class Layer {

    public Layer before;
    public int dimension;
    public Neuron[] neurons;
    public Vector<Double>[] weights;
    public ActivationFunction activationFunction;
    public ElementType layerType;

    public Layer(Layer before, Vector<Double>[] weights, ActivationFunction activationFunction, ElementType elementType) {
        this.layerType = elementType;
        this.before = before;
        dimension = weights.length;
        this.weights = weights;
        this.neurons = new Neuron[dimension];
        this.activationFunction = activationFunction;
        setupNeurons();
    }

    public Layer(int dimension, ActivationFunction activationFunction, ElementType elementType) {
        this.dimension = dimension;
        this.layerType = elementType;
        this.neurons = new Neuron[dimension];
        this.activationFunction = activationFunction;
        randomize();
        setupNeurons();
    }

    public double getWeight( int PositionNeuronFrom, int PositionNeuronTo){
        Vector<Double> NeuronToWeights = weights[PositionNeuronTo];
        return NeuronToWeights.content[PositionNeuronFrom];
    }


    public void setWeight( int PositionNeuronFrom, int PositionNeuronTo, double value){
        Vector<Double> NeuronToWeights = weights[PositionNeuronTo];
         NeuronToWeights.content[PositionNeuronFrom] = value;
    }

    public void setupNeurons() {

        for (int i = 0; i < neurons.length; i++) {

            if(layerType == ElementType.INPUT) {
                neurons[i] = new Neuron(activationFunction, layerType);
            }else {
                neurons[i] = new Neuron(activationFunction, layerType,weights[i]);
            }
        }
    }

    public void randomize() {


    }
}
