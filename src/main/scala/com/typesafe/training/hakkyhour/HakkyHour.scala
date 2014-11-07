package com.typesafe.training.hakkyhour

import akka.actor.{ ActorRef, Props, ActorLogging, Actor }
import com.typesafe.training.hakkyhour.HakkyHour.CreateGuest

/**
 * Created by PablPaHo on 30/10/14.
 */
class HakkyHour extends Actor with ActorLogging {

  log.debug("Hakky Hour is open!")

  val waiter = createWaiter()

  override def receive: Actor.Receive = {
    case c: CreateGuest => {
      context.actorOf(Guest.props(waiter, c.drink))
    }

    case _ =>
      log.info("Welcome to Hakky Hour!")
      sender() ! "Welcome to Hakky Hour!"
  }

  def createWaiter(): ActorRef = context.actorOf(Waiter.props, "waiter")

}
object HakkyHour {
  def props: Props = {
    Props(new HakkyHour)
  }
  case class CreateGuest(drink: Drink)
}
