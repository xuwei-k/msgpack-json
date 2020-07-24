# msgpack-json

[![Build Status](https://travis-ci.com/xuwei-k/msgpack-json.png)](http://travis-ci.com/xuwei-k/msgpack-json)


### example

#### msgpack binary data => json string

```
$ scala -e 'java.nio.file.Files.write(new java.io.File("msgpack_data1").toPath, Array(0x81, 0xa3, 0x61, 0x62, 0x63, 0x92, 0xc2, 0x2a).map(_.toByte))'
$ curl -X POST -d @msgpack_data1 https://msgpack-json.appspot.com/msgpack2json
{"abc":[false,42]}
```

#### json string => msgpack binary data

```
$ curl -X POST -d '{"abc":[false,42]}' https://msgpack-json.appspot.com/json2msgpack > msgpack_data2
$ scala -e 'println(java.nio.file.Files.readAllBytes(new java.io.File("msgpack_data2").toPath).map("%02x" format _).mkString(","))'
81,a3,61,62,63,92,c2,2a
```
