package me.jingege.yaserde.datatypes;

import me.jingege.yaserde.Deflat;
import me.jingege.yaserde.Inflat;
import me.jingege.yaserde.Yaserder;

/**
 * Short yaserder
 * User: jingege
 * Datetime: 9/2/13 4:51 PM
 */
public class ShortYaserder implements Yaserder {

    private short value;

    public ShortYaserder(short value){
        this.value = value;
    }

    @Override
    public void inflate(Inflat inflat) {
        inflat.pushShort(value);
    }

    @Override
    public void deflate(Deflat deflat) {
        this.value = deflat.popShort();
    }

    public short getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShortYaserder that = (ShortYaserder) o;

        if (value != that.value) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) value;
    }
}
