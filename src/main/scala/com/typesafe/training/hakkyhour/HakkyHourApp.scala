/*
 * Copyright © 2014 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.hakkyhour

import akka.actor.{ Props, Actor, ActorRef, ActorSystem }
import akka.event.Logging
import scala.annotation.tailrec
import scala.collection.breakOut
import scala.io.StdIn

object HakkyHourApp {

  private val opt = """(\S+)=(\S+)""".r

  def main(args: Array[String]): Unit = {
    val opts = argsToOpts(args.toList)
    applySystemProperties(opts)
    val name = opts.getOrElse("name", "hakky-hour")

    val system = ActorSystem(s"$name-system")
    val hakkyHourApp = new HakkyHourApp(system)
    hakkyHourApp.run()
  }

  private[hakkyhour] def argsToOpts(args: Seq[String]): Map[String, String] =
    args.collect { case opt(key, value) => key -> value }(breakOut)

  private[hakkyhour] def applySystemProperties(opts: Map[String, String]): Unit =
    for ((key, value) <- opts if key startsWith "-D")
      System.setProperty(key substring 2, value)
}

class HakkyHourApp(system: ActorSystem) extends Terminal {

  private val log = Logging(system, getClass.getName)

  private val hakkyHour = createHakkyHour()

  system.actorOf(Props(new Actor {
    hakkyHour ! "Nice bar!"
    override def receive: Actor.Receive = {
      case _ => log.info("response")
    }
  }))

  def run(): Unit = {
    log.warning(f"{} running%nEnter commands into the terminal, e.g. `q` or `quit`", getClass.getSimpleName)
    commandLoop()
    system.awaitTermination()
  }

  protected def createHakkyHour(): ActorRef = {
    system.actorOf(HakkyHour.props, "hakky-hour")
  }

  @tailrec
  private def commandLoop(): Unit =
    Command(StdIn.readLine()) match {
      case Command.Guest(count, drink, maxDrinkCount) =>
        createGuest(count, drink, maxDrinkCount)
        commandLoop()
      case Command.Status =>
        getStatus()
        commandLoop()
      case Command.Quit =>
        system.shutdown()
      case Command.Unknown(command) =>
        log.warning("Unknown command {}!", command)
        commandLoop()
    }

  protected def createGuest(count: Int, drink: Drink, maxDrinkCount: Int): Unit =
    () // TODO Send CreateGuest to HakkyHour count number of times

  protected def getStatus(): Unit =
    () // TODO Ask HakkyHour for the status and log the result on completion
}
