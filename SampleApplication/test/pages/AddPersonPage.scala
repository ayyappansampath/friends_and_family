package pages

import org.openqa.selenium.{By, WebDriver}
import utils.FormHelpers._
import org.scalatest.Matchers
import test.framework.Driver
import org.openqa.selenium.By.{ById, ByName}

/**
 * Created by user02 on 5/23/14.
 */
class AddPersonPage(implicit val webDriver : Driver) extends IndexPage with Matchers{

  def getUrl= "http://localhost:9000" + "/add"

  def goToPage() = {

    driver.get(getUrl)
  }


  def fillAndSubmitForm(firstName : String , lastName : String, email : String ) = {
    fillFormFieldById(driver, "firstName",firstName)
    fillFormFieldById(driver, "lastName",lastName)
    fillFormFieldById(driver, "email",email)
    clickButtonById(driver,"submit")
  }

  def fillForm(firstName : String , lastName : String, email : String ) = {
    fillFormFieldById(driver, "firstName",firstName)
    fillFormFieldById(driver, "lastName",lastName)
    fillFormFieldById(driver, "email",email)
  }


}
