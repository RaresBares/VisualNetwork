package de.rares.visnet.api.neuronalnetwork.activationfunction;

import de.rares.visnet.api.math.calculator.Parser;

public enum ActivationFunction {

    ReLu("variable"), Sigmoid("(1)/(1+(2.1415)^((-1)*(variable)))");


    private final String term;

    ActivationFunction(String s){
        this.term = s;
    }

    public String getTerm(){

        return term;

    }


    public Double calc(String var){


        return Parser.eval(term.replace("variable", var)).getValue();

    }


    public String buildTerm(String var){


        return term.replace("variable", var);

    }




}
