/*
 * Copyright © 2014 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.hakkyhour

import org.scalactic.ConversionCheckedTripleEquals // Needed for FTTAJ!
import scala.collection.JavaConversions._ // Needed for FTTAJ!

class HakkyHourAppSpec extends BaseSpec with ConversionCheckedTripleEquals {

  import HakkyHourApp._

  "Calling argsToOpts" should {
    "return the correct opts for the given args" in {
      argsToOpts(List("a=1", "b", "-Dc=2")) should ===(Map("a" -> "1", "-Dc" -> "2"))
    }
  }

  "Calling applySystemProperties" should {
    "apply the system properties for the given opts" in {
      System.setProperty("c", "")
      applySystemProperties(Map("a" -> "1", "-Dc" -> "2"))
      System.getProperty("c") should ===("2")
    }
  }
}
