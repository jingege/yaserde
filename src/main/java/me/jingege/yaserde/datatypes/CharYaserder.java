package me.jingege.yaserde.datatypes;

import me.jingege.yaserde.Deflat;
import me.jingege.yaserde.Inflat;
import me.jingege.yaserde.Yaserder;

/**
 * Char yaserder
 * User: jingege
 * Datetime: 9/2/13 4:52 PM
 */
public class CharYaserder implements Yaserder{

    private char value;

    public CharYaserder(char value){
        this.value = value;
    }

    public CharYaserder() {

    }

    @Override
    public void inflate(Inflat inflat) {
        inflat.pushChar(value);
    }

    @Override
    public void deflate(Deflat deflat) {
        this.value = deflat.popChar();
    }

    public char getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CharYaserder that = (CharYaserder) o;

        if (value != that.value) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) value;
    }
}
