val unfilteredVersion = "0.10.0"

name := "msgpack-json"

organization := "com.github.xuwei-k"

licenses := Seq("MIT License" -> url("http://www.opensource.org/licenses/mit-license.php"))

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.13.4"

val unusedWarnings = (
  "-Ywarn-unused" ::
  Nil
)

scalacOptions ++= (
  "-language:postfixOps" ::
  "-language:implicitConversions" ::
  "-language:higherKinds" ::
  "-language:existentials" ::
  "-deprecation" ::
  "-unchecked" ::
  Nil
) ::: unusedWarnings

Seq(Compile, Test).flatMap(c =>
  scalacOptions in (c, console) ~= {_.filterNot(unusedWarnings.toSet)}
)

fullResolvers ~= {_.filterNot(_.name == "jcenter")}

libraryDependencies ++= (
  ("ws.unfiltered" %% "unfiltered-filter" % unfilteredVersion) ::
  ("javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided") ::
  ("com.github.xuwei-k" %% "msgpack4z-jawn" % "0.7.0") ::
  ("com.github.xuwei-k" % "msgpack4z-java" % "0.3.6") ::
  Nil
)
