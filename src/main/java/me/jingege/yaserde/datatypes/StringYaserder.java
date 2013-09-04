package me.jingege.yaserde.datatypes;

import me.jingege.yaserde.Deflat;
import me.jingege.yaserde.Inflat;
import me.jingege.yaserde.Yaserder;

/**
 * String yaserder
 * User: jingege
 * Datetime: 8/30/13 5:21 PM
 */
public class StringYaserder implements Yaserder{

    String value;

    public StringYaserder(){};

    public StringYaserder(String value){
        this.value = value;
    }

    @Override
    public void inflate(Inflat inflat) {
         inflat.pushString(value);
    }

    @Override
    public void deflate(Deflat deflat) {
        this.value = deflat.popString();
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StringYaserder yaserder = (StringYaserder) o;

        if (value != null ? !value.equals(yaserder.value) : yaserder.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
