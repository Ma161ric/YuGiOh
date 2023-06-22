val scala3Version = "3.2.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "YuGiOh",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    coverageEnabled := true,
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.14",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.14" % "test",
    //libraryDependencies += "org.scalafx" %% "scalafx" % "20.0.0-R31"
    libraryDependencies += ("org.scala-lang.modules" %% "scala-swing" % "3.0.0").cross(CrossVersion.for3Use2_13),
    libraryDependencies += "net.codingwell" %% "scala-guice" % "7.0.0"
  )
