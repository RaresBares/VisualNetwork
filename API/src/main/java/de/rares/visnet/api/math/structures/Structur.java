package de.rares.visnet.api.math.structures;

public interface Structur<T extends Number> {

    public Structur multiply(Structur target);

    public Structur add(Structur target);

    public Structur subtract(Structur target);

    public Structur divide(Structur target);

    public Structur pow(Structur target);
    public void setElement(int position, T value);


}
