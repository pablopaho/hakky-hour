/*
 * Copyright © 2014 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.hakkyhour

import akka.testkit.TestProbe

class WaiterSpec extends BaseAkkaSpec {

  "Sending ServeDrink to Waiter" should {
    "result in sending a DrinkServed response to the sender" in {
      val sender = TestProbe()
      implicit val _ = sender.ref
      val waiter = system.actorOf(Waiter.props)
      waiter ! Waiter.ServeDrink(Drink.Akkarita)
      sender.expectMsg(Waiter.DrinkServed(Drink.Akkarita))
    }
  }
}
