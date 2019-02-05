
crossScalaVersions in ThisBuild := Seq("2.12.8", "2.11.12")
scalaVersion in ThisBuild := crossScalaVersions.value.head

val commonSettings = Seq(
  organization := "com.lightbend.lagom",

  scalacOptions ++= List(
    "-unchecked",
    "-deprecation",
    "-language:_",
    "-encoding", "UTF-8"
  ),
  javacOptions ++= List(
    "-Xlint:unchecked",
    "-Xlint:deprecation"
  )
)

lazy val root = (project in file("."))
  .enablePlugins(NoPublish)
  .settings(
    name := "lagom-akka-discovery-service-locator-root"
  )
  .aggregate(serviceLocatorCore, serviceLocatorJavadsl, serviceLocatorScaladsl)

lazy val serviceLocatorCore = (project in file("service-locator/core"))
  .enablePlugins(AutomateHeaderPlugin)
  .settings(commonSettings)
  .settings(
    name := "lagom-akka-discovery-service-locator-core",
    libraryDependencies ++= Dependencies.serviceLocatorCore
  )

lazy val serviceLocatorJavadsl = (project in file("service-locator/javadsl"))
  .enablePlugins(AutomateHeaderPlugin)
  .settings(commonSettings)
  .settings(
    name := "lagom-javadsl-akka-discovery-service-locator",
    libraryDependencies ++= Dependencies.serviceLocatorJavadsl
  ).dependsOn(serviceLocatorCore)

lazy val serviceLocatorScaladsl = (project in file("service-locator/scaladsl"))
  .enablePlugins(AutomateHeaderPlugin)
  .settings(commonSettings)
  .settings(
    name := "lagom-scaladsl-akka-discovery-service-locator",
    libraryDependencies ++= Dependencies.serviceLocatorScaladsl
  ).dependsOn(serviceLocatorCore)

