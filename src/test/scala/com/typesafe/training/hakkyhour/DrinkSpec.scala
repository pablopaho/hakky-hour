/*
 * Copyright © 2014 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.hakkyhour

class DrinkSpec extends BaseSpec {

  import Drink._

  "drinks" should {
    "contain Akkarita, MaiPlay and PinaScalada" in {
      drinks should ===(Set[Drink](Akkarita, MaiPlay, PinaScalada))
    }
  }

  "Calling apply" should {
    "create the correct Drink for a known code" in {
      apply("A") should ===(Akkarita)
      apply("a") should ===(Akkarita)
      apply("M") should ===(MaiPlay)
      apply("m") should ===(MaiPlay)
      apply("P") should ===(PinaScalada)
      apply("p") should ===(PinaScalada)
    }
    "throw an IllegalArgumentException for an unknown code" in {
      an[IllegalArgumentException] should be thrownBy apply("1")
    }
  }

  "Calling anyOther" should {
    "return an other Drink than the given one" in {
      forAll(drinks) { drink => anyOther(drink) should !==(drink) }
    }
  }
}
