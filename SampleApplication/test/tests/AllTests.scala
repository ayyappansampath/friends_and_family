package tests

import org.scalatest.{DoNotDiscover, BeforeAndAfterAll, Suites}
import test.framework.SingletonDriver
import tests.view.AddPersonFormTest

@DoNotDiscover
class AllTests extends Suites (

  new AddPersonFormTest

) with BeforeAndAfterAll {

  override protected def afterAll() = SingletonDriver.closeInstance()

}
