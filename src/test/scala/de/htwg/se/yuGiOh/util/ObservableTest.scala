package main.scala.de.htwg.se.yuGiOh
package util

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*

class ObservableTest extends AnyWordSpec {
  "An Observable" when {
    "new" should {
      val observable = new Observable {}
      val observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated
        override def update: Unit = { updated = true; updated }
      }
      "add an Observer" in {
        observable.add(observer)
        observable.subscribers should contain(observer)
      }
      //   "notify an Observer" in {
      //     observer.isUpdated should be(false)
      //     observable.notifyObservers
      //     observer.isUpdated should be(true)
      //   }
      "remove an Observer" in {
        observable.remove(observer)
        observable.subscribers should not contain (observer)
      }
    }
  }
}
