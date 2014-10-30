package com.typesafe.training.hakkyhour

import akka.actor.{ Actor, ActorLogging, Props }

/**
 * Created by PablPaHo on 30/10/14.
 */
class Guest extends Actor with ActorLogging {
  override def receive: Actor.Receive = {
    case _ => Actor.emptyBehavior
  }
}

object Guest {
  def props: Props = { Props(new Guest()) }
}