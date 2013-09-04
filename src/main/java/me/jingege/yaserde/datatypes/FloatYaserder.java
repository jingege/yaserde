package me.jingege.yaserde.datatypes;

import me.jingege.yaserde.Deflat;
import me.jingege.yaserde.Inflat;
import me.jingege.yaserde.Yaserder;

/**
 * Float yaserder
 * User: jingege
 * Datetime: 9/2/13 4:52 PM
 */
public class FloatYaserder implements Yaserder{

    private float value;

    public FloatYaserder(float value){
        this.value = value;
    }

    public FloatYaserder() {

    }

    @Override
    public void inflate(Inflat inflat) {
        inflat.pushFloat(value);
    }

    @Override
    public void deflate(Deflat deflat) {
        this.value = deflat.popFloat();
    }

    public float getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FloatYaserder that = (FloatYaserder) o;

        if (Float.compare(that.value, value) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (value != +0.0f ? Float.floatToIntBits(value) : 0);
    }
}
