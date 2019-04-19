name := """TwitterTest"""
organization := "xr.com"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.4"

libraryDependencies += guice

// https://mvnrepository.com/artifact/org.twitter4j/twitter4j-core
libraryDependencies += "org.twitter4j" % "twitter4j-core" % "4.0.6"

// https://mvnrepository.com/artifact/org.twitter4j/twitter4j-stream
libraryDependencies += "org.twitter4j" % "twitter4j-stream" % "4.0.6"

// https://mvnrepository.com/artifact/org.mockito/mockito-core
libraryDependencies += "org.mockito" % "mockito-core" % "2.15.0" % Test

// https://mvnrepository.com/artifact/net.bytebuddy/byte-buddy
libraryDependencies += "net.bytebuddy" % "byte-buddy" % "1.7.9"

// https://mvnrepository.com/artifact/net.bytebuddy/byte-buddy-agent
libraryDependencies += "net.bytebuddy" % "byte-buddy-agent" % "1.7.9" % Test

// https://mvnrepository.com/artifact/org.objenesis/objenesis
libraryDependencies += "org.objenesis" % "objenesis" % "2.6" % Test

// https://mvnrepository.com/artifact/com.typesafe.akka/akka-testkit
libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.5.11" % Test

libraryDependencies += ws

libraryDependencies += "org.awaitility" % "awaitility" % "3.0.0" % Test