package de.rares.visnet.api;


import de.rares.visnet.api.math.differentials.DifFunction;
import de.rares.visnet.api.math.function.Function;

public class VisNetApi {


    public VisNetApi() {


    }

    public static void main(String[] args) {
        Function f = new Function("x^(2x) + (x + 2)x + 3x", 'x');
        DifFunction df = new DifFunction(f);
        for (Function function : df.part()) {
            System.out.println(function.toString());
        }





        System.out.println();
    }


}
