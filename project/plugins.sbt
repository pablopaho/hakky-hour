addSbtPlugin("com.danieltrinh" % "sbt-scalariform" % "1.3.0")

addSbtPlugin("com.typesafe.training" % "sbt-koan" % "2.4.0")

// Actually IDE specific settings belong into ~/.sbt/,
// but in order to ease the setup for the training, we put the following here:

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.5.0")
