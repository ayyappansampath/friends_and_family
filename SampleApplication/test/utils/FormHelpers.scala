package utils

import org.openqa.selenium.{WebDriver, By}
import test.framework.Driver
/**
 * Created by user02 on 5/23/14.
 */
object FormHelpers {


  def fillFormFieldById(driver : WebDriver, id : String, value : String) = {
    driver.findElement(By.id(id)).clear()
    driver.findElement(By.id(id)).sendKeys(value)
  }

  def clickButtonById(driver : WebDriver, id : String) = {
    driver.findElement(By.id(id)).click()
  }

  def getTextFromFormFieldById(driver : WebDriver, id : String) = {
    driver.findElement(By.id(id)).getText
  }

}
