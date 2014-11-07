/*
 * Copyright © 2014 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.hakkyhour

import akka.testkit.{ EventFilter, TestProbe }

class GuestSpec extends BaseAkkaSpec with GuestSpecExtra {

  "Sending DrinkServed to Guest" should {
    "result in increasing the drinkCount and logging a status message at info" in {
      val guest = system.actorOf(Guest.props(system.deadLetters, Drink.Akkarita))
      EventFilter.info(pattern = """.*[Ee]njoy.*1\..*""", occurrences = 1) intercept {
        guest ! Waiter.DrinkServed(Drink.Akkarita)
      }
    }
  }

  "Sending DrinkFinished to Guest" should {
    "result in sending ServeDrink to Waiter" in {
      val waiter = TestProbe()
      val guest = system.actorOf(Guest.props(waiter.ref, Drink.Akkarita))
      guest ! drinkFinished
      waiter.expectMsg(Waiter.ServeDrink(Drink.Akkarita))
    }
  }
}
