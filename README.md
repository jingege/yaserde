yaserder
========

A very simple serialization/deserialization framework

> yaserder = Yet Another Serialization Deserialization framework

 * 简单易用
 * 基于ByteBuffer
 * 针对简单数据类型场景

####序列化简单类型

```java

int aint = 3;

Inflat inflat = new Inflat();
inflat.pushInt(aint);

byte[] bytes = inflat.getBytes();

//~here the bytes may be transferred by I/O

Deflat deflat = new Deflat();
deflat.deflat(bytes);

int result = deflat.popInt();

```

####序列化Map

```java

MapYaserder<StringYaserder,IntegerYaserder> mapYaserder = new MapYaserder<StringYaserder,IntegerYaserder>();

mapYaserder.put(new StringYaserder("abc"),new IntegerYaserder(2));
mapYaserder.put(new StringYaserder("def"),new IntegerYaserder(3));

Inflat inflat = new Inflat();
inflat.pushYaserder(mapYaserder);

byte[] bytes = inflat.getBytes();

//~here the bytes may be transferred by I/O

MapYaserder<StringYaserder,IntegerYaserder> map = new MapYaserder<StringYaserder, IntegerYaserder>();
Deflat deflat = new Deflat(bytes);
deflat.popYaserder(map);

IntegerYaserder v1 = map.get(new StringYaserder("abc"));

```

###TODO

 * 更多的Yaserder内嵌支持
 * 易用性
