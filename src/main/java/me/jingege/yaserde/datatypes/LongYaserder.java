package me.jingege.yaserde.datatypes;

import me.jingege.yaserde.Deflat;
import me.jingege.yaserde.Inflat;
import me.jingege.yaserde.Yaserder;

/**
 * Long yaserder
 * User: jingege
 * Datetime: 9/2/13 4:51 PM
 */
public class LongYaserder implements Yaserder{

    private long value;

    public LongYaserder(long value){
        this.value = value;
    }

    public LongYaserder() {

    }

    @Override
    public void inflate(Inflat inflat) {
        inflat.pushLong(value);
    }

    @Override
    public void deflate(Deflat deflat) {
        this.value = deflat.popLong();
    }

    public long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LongYaserder that = (LongYaserder) o;

        if (value != that.value) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (value ^ (value >>> 32));
    }
}
