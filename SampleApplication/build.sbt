name := "SampleApplication"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "org.seleniumhq.selenium" % "selenium-java" % "2.41.0" % "test",
  "org.fluentlenium" % "fluentlenium-core" % "0.9.2" % "test",
  "org.fluentlenium" % "fluentlenium-festassert" % "0.9.2" % "test"
)    
 
libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"

play.Project.playScalaSettings
