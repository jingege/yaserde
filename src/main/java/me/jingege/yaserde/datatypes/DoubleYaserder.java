package me.jingege.yaserde.datatypes;

import me.jingege.yaserde.Deflat;
import me.jingege.yaserde.Inflat;
import me.jingege.yaserde.Yaserder;

/**
 * Double yaserder
 * User: jingege
 * Datetime: 9/2/13 5:01 PM
 */
public class DoubleYaserder implements Yaserder{

    private double value;

    public DoubleYaserder(double value){
        this.value = value;
    }

    public DoubleYaserder() {

    }

    @Override
    public void inflate(Inflat inflat) {
        inflat.pushDouble(value);
    }

    @Override
    public void deflate(Deflat deflat) {
        this.value = deflat.popDouble();
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DoubleYaserder that = (DoubleYaserder) o;

        if (Double.compare(that.value, value) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(value);
        return (int) (temp ^ (temp >>> 32));
    }
}
