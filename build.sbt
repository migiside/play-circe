import ReleaseTransformations._

organization := "com.dripower"
name := "play-circe"
scalaVersion := "2.13.10"

crossScalaVersions := Seq("2.12.17", "2.13.10", "3.3.0")

val playV  = "2.8.19"
val circeV = "0.14.5"

val circeDeps = Seq(
  "io.circe"               %% "circe-core"            % circeV,
  "io.circe"               %% "circe-parser"          % circeV,
  "io.circe"               %% "circe-generic"         % circeV  % Test
)

val playDeps = Seq(
  "com.typesafe.play"      %% "play"                  % playV   % Provided,
  "org.scalatestplus.play" %% "scalatestplus-play"    % "5.1.0" % Test,
  "com.typesafe.play"      %% "play-ws"               % playV   % Test,
  "com.typesafe.play"      %% "play-akka-http-server" % playV   % Test
).map(_.cross(CrossVersion.for3Use2_13))

libraryDependencies ++= (circeDeps ++ playDeps)

scalacOptions := {
  Seq(
    "-deprecation",
    "-encoding",
    "UTF-8",
    "-feature",
    "-unchecked",
    "-Xlint",
    "-Ywarn-dead-code",
    "-Ywarn-numeric-widen",
    "-Ywarn-value-discard"
  )
}

// POM settings for Sonatype
homepage := Some(url("https://github.com/jilen/play-circe"))

scmInfo := Some(ScmInfo(url("https://github.com/jilen/play-circe"), "git@github.com:jilen/play-circe.git"))

developers += Developer("jilen", "jilen", "jilen.zhang@gmail.com", url("https://github.com/jilen"))

licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  setNextVersion,
  commitNextVersion,
  pushChanges
)
