/*
 * Copyright © 2014 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.hakkyhour

import scala.reflect.runtime.{ universe => ru }

// Needed for FTTAJ!
trait GuestSpecExtra {

  def drinkFinished: Any = {
    // This freaking crazy code is needed to access the private DrinkFinished case object
    val drinkFinishedSymbol = ru.typeOf[Guest.type].decl(ru.TermName("DrinkFinished")).asModule
    ru.runtimeMirror(getClass.getClassLoader).reflectModule(drinkFinishedSymbol).instance
  }
}
