package pages

import test.framework.Driver
import org.openqa.selenium.{WebElement, By}
import scala.collection.JavaConversions._
import java.net.URL

trait Paths {

  val rootResource = "/"

  def SampleApplicationPath(path:String) = s"$rootResource/$path"
}

object Paths extends Paths

trait IndexPage extends Page with Paths {

  def clickButtonNamed(buttonName:String) {
    buttonNamed(buttonName).click()
  }

  def buttonNamed(buttonName:String)(implicit driver: Driver): WebElement= {
    driver.getInstance().findElements(By.className("button")).find(_.getText.trim() == buttonName).getOrElse(throw new Exception(s"Didn't find button named '$buttonName'"))
  }

  def path(implicit driver: Driver) = new URL(driver.getInstance().getCurrentUrl).getPath

  def clickBack(implicit driver: Driver) {
    driver.getInstance().findElement(By.id("backLink")).click()
  }

  def clickNext(implicit driver: Driver) {
    clickButtonNamed("Next")
  }
}
