/*
 * Copyright © 2014 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.hakkyhour

import scala.util.parsing.combinator.RegexParsers

trait Terminal {

  protected sealed trait Command

  protected object Command {

    case class Guest(count: Int, drink: Drink, maxDrinkCount: Int) extends Command

    case object Status extends Command

    case object Quit extends Command

    case class Unknown(command: String) extends Command

    def apply(command: String): Command =
      CommandParser.parseAsCommand(command)
  }

  private object CommandParser extends RegexParsers {

    def parseAsCommand(s: String): Command =
      parseAll(parser, s) match {
        case Success(command, _) => command
        case _                   => Command.Unknown(s)
      }

    def createGuest: Parser[Command.Guest] =
      opt(int) ~ ("guest|g".r ~> opt(drink) ~ opt(int)) ^^ {
        case count ~ (drink ~ maxDrinkCount) =>
          Command.Guest(
            count getOrElse 1,
            drink getOrElse Drink.Akkarita,
            maxDrinkCount getOrElse Int.MaxValue
          )
      }

    def getStatus: Parser[Command.Status.type] =
      "status|s".r ^^ (_ => Command.Status)

    def quit: Parser[Command.Quit.type] =
      "quit|q".r ^^ (_ => Command.Quit)

    def drink: Parser[Drink] =
      "A|a|M|m|P|p".r ^^ Drink.apply

    def int: Parser[Int] =
      """\d+""".r ^^ (_.toInt)
  }

  private val parser: CommandParser.Parser[Command] =
    CommandParser.createGuest | CommandParser.getStatus | CommandParser.quit
}
