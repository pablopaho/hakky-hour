/*
 * Copyright © 2014 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.hakkyhour

import akka.testkit.{ EventFilter, TestProbe }

class HakkyHourSpec extends BaseAkkaSpec {

  "Creating HakkyHour" should {
    "result in logging a status message at debug" in {
      EventFilter.debug(pattern = ".*open.*", occurrences = 1) intercept {
        system.actorOf(HakkyHour.props)
      }
    }
  }

  "Sending CreateGuest to HakkyHour" should {
    "result in creating a Guest" in {
      val hakkyHour = system.actorOf(HakkyHour.props, "create-guest")
      hakkyHour ! HakkyHour.CreateGuest
      TestProbe().expectActor("/user/create-guest/$*")
    }
  }
}
