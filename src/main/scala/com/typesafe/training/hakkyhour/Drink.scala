/*
 * Copyright © 2014 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.hakkyhour

import scala.util.Random

sealed trait Drink

object Drink {

  case object Akkarita extends Drink

  case object MaiPlay extends Drink

  case object PinaScalada extends Drink

  val drinks: Set[Drink] =
    Set(Akkarita, MaiPlay, PinaScalada)

  def apply(code: String): Drink =
    code.toLowerCase match {
      case "a" => Akkarita
      case "m" => MaiPlay
      case "p" => PinaScalada
      case _   => throw new IllegalArgumentException("""Unknown drink code "$code"!""")
    }

  def anyOther(drink: Drink): Drink = {
    val others = drinks - drink
    others.toVector(Random.nextInt(others.size))
  }
}
