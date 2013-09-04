package me.jingege.yaserde.test;

import junit.framework.Assert;
import junit.framework.TestCase;
import me.jingege.yaserde.Deflat;
import me.jingege.yaserde.Inflat;
import me.jingege.yaserde.datatypes.StringYaserder;

/**
 * User: jingege
 * Datetime: 8/31/13 12:46 AM
 */
public class TestStringYaserder extends TestCase {


    public void testString(){
        String test = "chinese汉字chinese汉字chinese汉字chinese汉字chinese汉字chinese汉字chinese汉字chinese汉字chinese汉字chinese汉字";

        StringYaserder stringYaserder = new StringYaserder(test);

        Inflat inflat = new Inflat();
        inflat.pushString(test);

        byte[] bytes = inflat.getBytes();

        Deflat deflat = new Deflat(bytes);
        String str = deflat.popString();

        Assert.assertEquals(test,str);
    }

    public void testNullString(){
        String test = null;
        StringYaserder yaserder = new StringYaserder(test);
        Inflat inflat = new Inflat();
        inflat.pushString(test);

        Deflat deflat = new Deflat(inflat.getBytes());

        String str = deflat.popString();
        Assert.assertEquals(test,str);
    }

    public void testEmptyString(){
        String test = "   ";
        StringYaserder yaserder = new StringYaserder(test);
        Inflat inflat = new Inflat();
        inflat.pushString(test);

        Deflat deflat = new Deflat(inflat.getBytes());

        String str = deflat.popString();

        Assert.assertEquals(test,str);
    }

    public void testStringEncoding(){
        String test =  "encode";
        StringYaserder yaserder = new StringYaserder(test);
        Inflat inflat = new Inflat();
        inflat.pushString(test,"GBK");

        Deflat deflat = new Deflat(inflat.getBytes());

        String str = deflat.popString("GBK");

        Assert.assertEquals(test,str);
    }
}
