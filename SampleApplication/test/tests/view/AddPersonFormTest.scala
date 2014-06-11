package tests.view


import org.scalatest.Matchers
import utils.{SampleApplicationSeleniumBase,FormHelpers}
import pages.AddPersonPage
import controllers.routes

/**
 * Created by user02 on 5/23/14.
 */

class AddPersonFormTest extends SampleApplicationSeleniumBase with Matchers {


  "Adding a person page" should "navigate to correct page intended" in {

      //fullyQualifiedUrl("add")
      driver.getInstance().navigate().to(fullyQualifiedUrl(routes.PersonController.showPage().toString()))
      //driver.getInstance().navigate().to(fullyQualifiedUrl("add"))

      val addPerson = new AddPersonPage()

      driver.getInstance().getTitle shouldBe ("Add Person")
   }

  it should "take in person details" in {

    driver.getInstance().navigate().to(fullyQualifiedUrl(routes.PersonController.showPage().toString()))

    val addPerson = new AddPersonPage()

    addPerson.fillForm("Ayyappan","Sampath","ayyappan18@gmail.com")


    }
 }
