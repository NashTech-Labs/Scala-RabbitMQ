ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "ProducerApp"
  )

coverageExcludedPackages := ".*DriverApp*"

libraryDependencies ++= Seq("com.rabbitmq" % "amqp-client" % "5.14.2",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4",
  "com.typesafe" % "config" % "1.4.2",
  "org.slf4j" % "slf4j-api" % "1.7.36",
  "org.slf4j" % "slf4j-simple" % "1.7.36",
  "org.scoverage" %% "scalac-scoverage-runtime" % "1.4.11",
  "org.scalatest" %% "scalatest" % "3.2.12" % Test,
  "org.mockito" %% "mockito-scala" % "1.17.5" % Test,
)


