package me.jingege.yaserde.test;

import junit.framework.Assert;
import junit.framework.TestCase;
import me.jingege.yaserde.Deflat;
import me.jingege.yaserde.Inflat;
import me.jingege.yaserde.datatypes.IntegerYaserder;
import me.jingege.yaserde.datatypes.MapYaserder;
import me.jingege.yaserde.datatypes.StringYaserder;

/**
 * User: jingege
 * Datetime: 8/31/13 12:46 AM
 */
public class TestMapYaserder extends TestCase {


    public void testMap(){
        MapYaserder<StringYaserder,IntegerYaserder> mapYaserder = new MapYaserder<StringYaserder,IntegerYaserder>();

        mapYaserder.put(new StringYaserder("abc"),new IntegerYaserder(2));
        mapYaserder.put(new StringYaserder("def"),new IntegerYaserder(3));

        Inflat inflat = new Inflat();
        inflat.pushYaserder(mapYaserder);

        byte[] bytes = inflat.getBytes();

        MapYaserder<StringYaserder,IntegerYaserder> map = new MapYaserder<StringYaserder, IntegerYaserder>();
        Deflat deflat = new Deflat(bytes);
        deflat.popYaserder(map);

        IntegerYaserder v1 = map.get(new StringYaserder("abc"));
        Assert.assertEquals(2,v1.getValue());
        Assert.assertEquals(3,map.get(new StringYaserder("def")).getValue());
    }

}
