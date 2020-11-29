package de.rares.visnet.api.math.derivative.derivative.patterns;

public class X extends Pattern {
    public X(String partfirst) {
        super(partfirst, "");
    }


    public static X of(String function){
        return new X(function);
    }

    @Override
    public String getDerivate(String target) {
        return "1";
    }
}
