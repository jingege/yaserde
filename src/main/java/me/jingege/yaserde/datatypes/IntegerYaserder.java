package me.jingege.yaserde.datatypes;

import me.jingege.yaserde.Deflat;
import me.jingege.yaserde.Inflat;
import me.jingege.yaserde.Yaserder;

/**
 * Integer yaserder
 * User: jingege
 * Datetime: 9/1/13 9:16 AM
 */
public class IntegerYaserder implements Yaserder {

    private int value;

    public IntegerYaserder(int value){
        this.value = value;
    }

    public IntegerYaserder() {

    }

    @Override
    public void inflate(Inflat inflat) {
        inflat.pushInt(this.value);
    }

    @Override
    public void deflate(Deflat deflat) {
        this.value = deflat.popInt();
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntegerYaserder that = (IntegerYaserder) o;

        if (value != that.value) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
