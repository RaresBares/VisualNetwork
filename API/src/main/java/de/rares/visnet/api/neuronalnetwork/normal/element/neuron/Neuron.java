package de.rares.visnet.api.neuronalnetwork.normal.element.neuron;

import de.rares.visnet.api.math.structures.Structur;
import de.rares.visnet.api.math.structures.vector.Vector;
import de.rares.visnet.api.math.structures.vector.VectorType;
import de.rares.visnet.api.neuronalnetwork.activationfunction.ActivationFunction;
import de.rares.visnet.api.neuronalnetwork.normal.element.ElementType;


public class Neuron {


    private final ElementType elementType;
    public ActivationFunction activationFunction;
    public Vector<Double> weights;

    public Neuron(ActivationFunction activationFunction, ElementType elementType, Vector<Double> weights) {
        this.activationFunction = activationFunction;
        this.weights = weights;
        this.elementType = elementType;
    }


    public Neuron(ActivationFunction activationFunction, ElementType elementType) {
        this.activationFunction = activationFunction;

        this.elementType = elementType;
    }



    public double process(Vector<Double> i) {
        if (elementType.equals(ElementType.INPUT) && i.dimension == 1) {
            return i.content[0];
        } else {
            Vector input;
            if (i.vectorType == VectorType.ROW) {
                input = i.copy();
            } else {
                input = i.transform();
            }

            Structur rawProd = input.multiply(weights);
            if(rawProd instanceof Vector ){
                Vector result = (Vector) rawProd;
                if(result.vectorType == VectorType.NUMBER){
                    return result.content[0].doubleValue();
                }else {
                    throw new RuntimeException("");
                }
            }else {
                throw new RuntimeException("");
            }

        }
    }

}
