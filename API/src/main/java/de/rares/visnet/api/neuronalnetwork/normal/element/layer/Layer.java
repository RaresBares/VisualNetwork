package de.rares.visnet.api.neuronalnetwork.normal.element.layer;

import de.rares.visnet.api.math.structures.vector.Vector;
import de.rares.visnet.api.neuronalnetwork.activationfunction.ActivationFunction;
import de.rares.visnet.api.neuronalnetwork.normal.element.neuron.Neuron;



public class Layer {

    public Layer before;
    public int dimension;
    public Neuron[] neurons;
    public Vector<Double>[] weights;
    public ActivationFunction activationFunction;

    public Layer(Layer before, Vector<Double>[] weights, ActivationFunction activationFunction) {
        this.before = before;
        dimension = weights.length;
        this.weights = weights;
        this.activationFunction = activationFunction;
        setupNeurons();
    }

    public Layer(int dimension, ActivationFunction activationFunction) {
        this.dimension = dimension;
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

    }

    public void randomize() {


    }
}
