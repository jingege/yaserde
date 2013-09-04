package me.jingege.yaserde.datatypes;

import me.jingege.yaserde.Deflat;
import me.jingege.yaserde.Inflat;
import me.jingege.yaserde.Reflecter;
import me.jingege.yaserde.Yaserder;

import java.util.*;

/**
 * Map yaserder
 * User: jingege
 * Datetime: 9/2/13 5:57 PM
 */
public class MapYaserder<K extends Yaserder,V extends Yaserder>  implements Yaserder,Map<K,V>{
    //class cache
    private static Map<Byte,Class<? extends Yaserder>> GENERIC_CLASS_CACHE = new HashMap<Byte, Class<? extends Yaserder>>();
    //id cache
    private static Map<Class<? extends Yaserder>,Byte> GENERIC_ID_CACHE = new HashMap<Class<? extends Yaserder>,Byte>();

    //register default types
    static{
        registerGenericType((byte)(Byte.MIN_VALUE + 1),BooleanYaserder.class);
        registerGenericType((byte)(Byte.MIN_VALUE + 2),CharYaserder.class);
        registerGenericType((byte)(Byte.MIN_VALUE + 3),DoubleYaserder.class);
        registerGenericType((byte)(Byte.MIN_VALUE + 4),FloatYaserder.class);
        registerGenericType((byte)(Byte.MIN_VALUE + 5),IntegerYaserder.class);
        registerGenericType((byte)(Byte.MIN_VALUE + 6),LongYaserder.class);
        registerGenericType((byte)(Byte.MIN_VALUE + 7),MapYaserder.class);
        registerGenericType((byte)(Byte.MIN_VALUE + 8),ShortYaserder.class);
        registerGenericType((byte)(Byte.MIN_VALUE + 9),StringYaserder.class);
    }
    private Map<K,V> map;

    public MapYaserder(){
        map = new LinkedHashMap<K, V>();//keep order
    }

    @Override
    public void inflate(Inflat inflat) {
        inflat.pushInt(map.size());
        Iterator<Map.Entry<K,V>> it = this.map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<K,V> entry = it.next();
            K key = entry.getKey();
            inflat.pushByte(getIdByClass(key.getClass()));//push type of any key
            inflat.pushYaserder(key);
            V value = entry.getValue();
            inflat.pushByte(getIdByClass(value.getClass()));//push type of any value
            inflat.pushYaserder(value);
        }

    }

    @Override
    public void deflate(Deflat deflat) {
        int size = deflat.popInt();

        for(int i = 0;i < size; i++){
            Class<? extends Yaserder> kclazz = readClass(deflat);
            K key = Reflecter.newInstance((Class<K>) kclazz);
            deflat.popYaserder(key);
            Class<? extends Yaserder> vclazz = readClass(deflat);
            V value = Reflecter.newInstance((Class<V>) vclazz);
            deflat.popYaserder(value);
            map.put(key,value);
        }

    }

    public static void registerGenericType(byte key,Class<? extends Yaserder> clazz){
        if(!GENERIC_CLASS_CACHE.containsKey(key)){
            GENERIC_CLASS_CACHE.put(key,clazz);
            GENERIC_ID_CACHE.put(clazz,key);
        }else{
            throw new IllegalArgumentException("Generic type registered");
        }
    }

    private Class<? extends Yaserder> readClass(Deflat deflat){
        byte id = deflat.popByte();
        return getClassById(id);
    }

    private  Class<? extends Yaserder> getClassById(byte id){
        Class<? extends Yaserder> clazz = GENERIC_CLASS_CACHE.get(id);
        if(clazz == null){
            throw new IllegalArgumentException("Generic type not registered");
        }
        return clazz;
    }
    private byte getIdByClass(Class<? extends Yaserder> clazz){
        Byte id = GENERIC_ID_CACHE.get(clazz);
        if(id == null){
            throw new IllegalArgumentException("Generic type not registered");
        }
        return id;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return map.get(key);
    }

    @Override
    public V put(K key, V value) {
        return map.put(key,value);
    }

    @Override
    public V remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        return map.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MapYaserder that = (MapYaserder) o;

        if (map != null ? !map.equals(that.map) : that.map != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return map != null ? map.hashCode() : 0;
    }
}

