package com.typesafe.training.hakkyhour

import akka.actor.{ Props, Actor, ActorLogging }
import com.typesafe.training.hakkyhour.Waiter.{ DrinkServed, ServeDrink }

/**
 * Created by PablPaHo on 6/11/14.
 */
class Waiter extends Actor with ActorLogging {
  override def receive: Actor.Receive = {
    case m: ServeDrink => {
      sender ! DrinkServed(m.drink)
    }
  }
}

object Waiter {
  case class ServeDrink(drink: Drink)
  case class DrinkServed(drink: Drink)

  def props: Props = {
    Props(new Waiter())
  }

}
