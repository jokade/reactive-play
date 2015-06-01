import sbt.Project.projectToRef

lazy val clients = Seq(client)
lazy val scalaV = "2.11.6"

lazy val root = (project in file(".")).settings(
  scalaVersion := scalaV,
  scalaJSProjects := clients,
  pipelineStages := Seq(scalaJSProd, gzip),
  libraryDependencies ++= Seq(
    "com.vmunier" %% "play-scalajs-scripts" % "0.2.2"
  )
).enablePlugins(PlayScala).
  aggregate(clients.map(projectToRef): _*)

lazy val client = (project in file("modules/client")).settings(
  scalaVersion := scalaV,
  persistLauncher := true,
  persistLauncher in Test := false,
  resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
  unmanagedSourceDirectories in Compile := Seq((scalaSource in Compile).value),
  libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.8.0",
    "biz.enef" %%% "scalajs-angulate" % "0.2"
  ),
  jsDependencies ++= Seq(
    "org.webjars" % "angularjs" % "1.3.15" / "angular.min.js",
    "org.webjars" % "angularjs" % "1.3.15" / "angular-route.min.js" dependsOn "angular.min.js"
  ),
  skip in packageJSDependencies := false

).enablePlugins(ScalaJSPlugin, ScalaJSPlay)
