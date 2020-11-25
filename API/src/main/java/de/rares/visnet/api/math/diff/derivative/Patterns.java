package de.rares.visnet.api.math.diff.derivative;

public enum Patterns {

    CONST("v", "0"), X("x", "1"), POT("x^(v)", "v * x^(v - 1)"), FACT("v * x", "v");


   public final String pattern;
    public final String result;

    Patterns(String s, String r) {
        this.pattern = s.replace(" ", "");
        this.result = r.replace(" ", "");
    }
}
