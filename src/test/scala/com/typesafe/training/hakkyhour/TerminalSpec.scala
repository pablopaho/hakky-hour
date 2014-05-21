/*
 * Copyright © 2014 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.hakkyhour

class TerminalSpec extends BaseSpec with Terminal {

  "Calling Command.apply" should {
    "create the correct CreateGuest command for the given input" in {
      Command("guest") should ===(Command.Guest(1, Drink.Akkarita, Int.MaxValue))
      Command("2 g") should ===(Command.Guest(2, Drink.Akkarita, Int.MaxValue))
      Command("g m") should ===(Command.Guest(1, Drink.MaiPlay, Int.MaxValue))
      Command("g 1") should ===(Command.Guest(1, Drink.Akkarita, 1))
      Command("2 g m 1") should ===(Command.Guest(2, Drink.MaiPlay, 1))
    }
    "create the GetStatus command for the given input" in {
      Command("status") should ===(Command.Status)
      Command("s") should ===(Command.Status)
    }
    "create the Quit command for the given input" in {
      Command("quit") should ===(Command.Quit)
      Command("q") should ===(Command.Quit)
    }
    "create the Unknown command for illegal input" in {
      Command("foo") should ===(Command.Unknown("foo"))
    }
  }
}
