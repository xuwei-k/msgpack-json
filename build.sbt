// https://github.com/unfiltered/unfiltered/blob/v0.8.1/project/common.scala#L6
// https://github.com/unfiltered/unfiltered/blob/v0.8.2/project/common.scala#L6
// https://code.google.com/p/googleappengine/issues/detail?id=3091
val unfilteredVersion = "0.8.1"

name := "msgpack-json"

organization := "com.github.xuwei-k"

licenses := Seq("MIT License" -> url("http://www.opensource.org/licenses/mit-license.php"))

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.11.5"

scalacOptions ++= (
  "-language:postfixOps" ::
  "-language:implicitConversions" ::
  "-language:higherKinds" ::
  "-language:existentials" ::
  "-deprecation" ::
  "-unchecked" ::
  "-Xlint" ::
  "-Ywarn-unused-import" ::
  "-Ywarn-unused" ::
  Nil
)

resolvers += "typesafe" at "http://repo.typesafe.com/typesafe/maven-releases"

libraryDependencies ++= (
  ("net.databinder" %% "unfiltered-filter" % unfilteredVersion) ::
  ("javax.servlet" % "servlet-api" % "2.3" % "provided") ::
  ("com.github.xuwei-k" %% "msgpack4z-play" % "0.1.0") ::
  ("com.github.xuwei-k" % "msgpack4z-java06" % "0.1.0") ::
  Nil
)