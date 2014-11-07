package com.typesafe.training.hakkyhour

import akka.actor.{ ActorRef, Actor, ActorLogging, Props }
import com.typesafe.training.hakkyhour.Waiter.{ ServeDrink, DrinkServed }

/**
 * Created by PablPaHo on 30/10/14.
 */
class Guest(waiter: ActorRef, favoriteDrink: Drink) extends Actor with ActorLogging {
  import com.typesafe.training.hakkyhour.Guest.DrinkFinished

  var drinkCount: Int = 0

  override def receive: Actor.Receive = {
    case m: DrinkServed => {
      drinkCount += 1
      log.info(s"Enjoying my ${drinkCount}. yummy ${m.drink}!")
    }
    case DrinkFinished => {
      waiter ! ServeDrink(favoriteDrink)
    }

    case _ => Actor.emptyBehavior
  }
}

object Guest {
  def props(waiter: ActorRef, favoriteDrink: Drink): Props = {
    Props(new Guest(waiter, favoriteDrink))
  }
  private case object DrinkFinished
}