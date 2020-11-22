package de.rares.visnet.api.math.function;

public class Function {

    public final String pattern;
    public final char argument;

    public Function(String pattern, char argument){
        this.pattern = pattern;
        this.argument = argument;


    }


    @Override
    public String toString() {
        return "Function{" +
                "pattern='" + pattern + '\'' +
                '}';
    }
}
