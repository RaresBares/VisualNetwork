package de.rares.visnet.api.math.diff.derivative.patterns;

public abstract class Pattern {

    public String partfirst;
    public String partsecond;

    public Pattern( String partfirst, String secondfirst) {
        this.partfirst = partfirst;
        this.partsecond = secondfirst;
    }


    public abstract String getDerivate();




}
