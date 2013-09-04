package me.jingege.yaserde.test;

import junit.framework.Assert;
import junit.framework.TestCase;
import me.jingege.yaserde.Deflat;
import me.jingege.yaserde.Inflat;

/**
 * User: jingege
 * Datetime: 8/31/13 12:46 AM
 */
public class TestRawYaserder extends TestCase {

    public void testBoolean(){
        boolean b1 = false;
        Boolean b2 = Boolean.TRUE;

        Inflat inflat = new Inflat();
        inflat.pushBoolean(b1);
        inflat.pushBoolean(b2);

        byte[] bytes = inflat.getBytes();

        Deflat deflat = new Deflat();
        deflat.deflat(bytes);

        Assert.assertFalse(deflat.popBoolean());
        Assert.assertTrue(deflat.popBoolean());
    }

    public void testChar(){
        char c1 = 'c';

        Inflat inflat = new Inflat();
        inflat.pushChar(c1);

        byte[] bytes = inflat.getBytes();

        Deflat deflat = new Deflat();
        deflat.deflat(bytes);

        Assert.assertTrue(deflat.popChar() == c1);
    }

    public void testDouble(){
        double d1 = 0.1;

        Inflat inflat = new Inflat();
        inflat.pushDouble(d1);

        byte[] bytes = inflat.getBytes();

        Deflat deflat = new Deflat();
        deflat.deflat(bytes);

        Assert.assertTrue(deflat.popDouble() == d1);
    }

    public void testFloat(){
        float f1 = 0.1F;

        Inflat inflat = new Inflat();
        inflat.pushFloat(f1);

        byte[] bytes = inflat.getBytes();

        Deflat deflat = new Deflat();
        deflat.deflat(bytes);

        Assert.assertTrue(deflat.popFloat() == f1);
    }

    public void testInt(){
        int aint = 3;

        Inflat inflat = new Inflat();
        inflat.pushInt(aint);

        byte[] bytes = inflat.getBytes();

        Deflat deflat = new Deflat();
        deflat.deflat(bytes);

        int result = deflat.popInt();

        Assert.assertEquals(aint, result);
    }

    public void testLong(){
        long l1 = 1L;

        Inflat inflat = new Inflat();
        inflat.pushLong(l1);

        byte[] bytes = inflat.getBytes();

        Deflat deflat = new Deflat();
        deflat.deflat(bytes);

        Assert.assertEquals(l1,deflat.popLong());
    }

    public void testShort(){
        short s1 = Short.MAX_VALUE;

        Inflat inflat = new Inflat();
        inflat.pushShort(s1);

        byte[] bytes = inflat.getBytes();

        Deflat deflat = new Deflat();
        deflat.deflat(bytes);

        Assert.assertEquals(s1,deflat.popShort());
    }
}
