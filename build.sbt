lazy val hakkyHour = project in file(".")

name := "hakky-hour"

Common.settings

libraryDependencies ++= Dependencies.hakkyHour

initialCommands := """|import com.typesafe.training.hakkyhour._""".stripMargin
