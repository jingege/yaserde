package me.jingege.yaserde.datatypes;

import me.jingege.yaserde.Deflat;
import me.jingege.yaserde.Inflat;
import me.jingege.yaserde.Yaserder;

/**
 * Boolean yaserder
 * User: jingege
 * Datetime: 9/2/13 4:51 PM
 */
public class BooleanYaserder implements Yaserder {

    private boolean value;

    public BooleanYaserder(){}

    public BooleanYaserder(boolean value){
        this.value = value;
    }

    @Override
    public void inflate(Inflat inflat) {
        inflat.pushBoolean(this.value);
    }

    @Override
    public void deflate(Deflat deflat) {
        this.value = deflat.popBoolean();
    }

    public boolean isValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BooleanYaserder that = (BooleanYaserder) o;

        if (value != that.value) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (value ? 1 : 0);
    }
}
