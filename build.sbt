lazy val common_project = Seq(
  organization := "prg20",
  version := "0.1-SNAPSHOT",

  fork in run          := true,
  fork in Test         := true,
  connectInput in run  := true,
  cancelable in Global := true,
  )

lazy val scala_project = common_project ++ Seq(
  scalaVersion := "2.13.3",   // コンパイルに使う scalac のバージョン
  scalacOptions := Seq("-feature", "-unchecked", "-deprecation"),
  scalaSource in Compile := baseDirectory.value / "src",
  scalaSource in Test    := baseDirectory.value / "test",
  )

lazy val java_project = scala_project ++ Seq(
  javacOptions ++= Seq("-encoding", "UTF-8"),
  javaSource in Compile := baseDirectory.value / "java"
  )

// サブプロジェクト群の定義。
lazy val root = (project in file(".")).settings(common_project)
lazy val support = (project in file ("support")).settings(java_project)

lazy val hello = (project in file ("hello")).settings(scala_project)
lazy val poton = (project in file ("poton")).dependsOn(support).settings(scala_project)
