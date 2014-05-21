/*
 * Copyright © 2014 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.hakkyhour

import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{ Inspectors, Matchers, WordSpec }

abstract class BaseSpec extends WordSpec with Matchers with TypeCheckedTripleEquals with Inspectors
