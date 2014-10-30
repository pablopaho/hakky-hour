package com.typesafe.training.hakkyhour

import akka.actor.{ Props, ActorLogging, Actor }
import com.typesafe.training.hakkyhour.HakkyHour.CreateGuest

/**
 * Created by PablPaHo on 30/10/14.
 */
class HakkyHour extends Actor with ActorLogging {

  log.debug("Hakky Hour is open!")
  override def receive: Actor.Receive = {
    case CreateGuest => {
      context.actorOf(Guest.props)
    }

    case _ =>
      log.info("Welcome to Hakky Hour!")
      sender() ! "Welcome to Hakky Hour!"
  }
}
object HakkyHour {
  def props: Props = {
    Props(new HakkyHour())
  }
  case object CreateGuest
}
