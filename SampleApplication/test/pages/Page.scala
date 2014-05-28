package pages

import org.openqa.selenium._
import scala.collection.JavaConversions._
import test.framework.Driver


trait Page {

  implicit val webDriver: Driver

  //TODO rename to 'instance'
  val driver = webDriver.getInstance()

  //def isLocal() = environment.equals(Environment.Local)

  def isOnPage: Boolean = false

  def elementForId(id:String) = driver.findElement(By.id(id))

  def errors = driver.findElements(By.cssSelector(".error-notification")).filter(e => e.isDisplayed).toList.map(_.getText)

  def textIsPresentOnPage(expectedText: String): Boolean = driver.getPageSource.contains(expectedText)

  def doesElementExistById(id: String): Boolean = !driver.findElements(By.id(id)).isEmpty

  def doesElementExistByClass(className: String): Boolean = !driver.findElements(By.className(className)).isEmpty

  def getCurrentUrlInBrowser: String = driver.getCurrentUrl

  def pageTitle() = driver.getTitle

 /* def generatePageObjectCodeInConsole() = PageObjectCodeGenerator.generatePageInConsole(driver)

  def navigateDirectlyTo[T : NotNothing: Manifest](url: String): T = {
    doActionAndWaitForPageLoad[T](driver.navigate().to(url), false)
  }

  def goBackTo[T : NotNothing: Manifest]: T = {
    doActionAndWaitForPageLoad[T](driver.navigate().back(), false)
  }

  def goBack() = goBackTo[Page]
*/
  def getCurrentWindowHandle = driver.getWindowHandle


  def closeCurrentWindowInFocus() = driver.close()

  def executeJavascript(command: String): Unit = driver.asInstanceOf[JavascriptExecutor].executeScript(command)

  protected def waitForPageToBeLoaded(condition: => Boolean, exceptionMessage: String, timeoutInSeconds: Int = 30) {
    val endTime = System.currentTimeMillis + timeoutInSeconds * 1000
    while (System.currentTimeMillis < endTime) {
      try {
        if (condition) {
          return
        }
      } catch {
        case _: RuntimeException =>
        // ignore exceptions during the timeout period because the condition
        // is throwing exceptions and we DO want to try the condition again until the timeout expires
      }


      // The following is to avoid to wait until timeout in case of well known errors
      val source: String = driver.getPageSource

    }

  //  throw new HmrcPageWaitException(exceptionMessage + "\nThe current page was:\n" + driver.getPageSource)
  }
/*

  def clickOn[T : NotNothing : Manifest](selector: By, isNewWindow: Boolean = false): T = {
    doActionAndWaitForPageLoad[T](driver.findElement(selector).click(), isNewWindow)
  }

  def clickOn[T : NotNothing: Manifest](webElement: WebElement, selector: By, isNewWindow: Boolean = false): T = {
    doActionAndWaitForPageLoad[T](webElement.findElement(selector).click(), isNewWindow)
  }

  def clickOnElement[T : NotNothing: Manifest](webElement: WebElement, isNewWindow: Boolean = false): T = {
    doActionAndWaitForPageLoad[T](webElement.click(), isNewWindow)
  }

  private def doActionAndWaitForPageLoad[T : NotNothing: Manifest](clickAction : => Unit, isNewWindow : Boolean): T = {
    if (!isNewWindow) {
      val body = driver.findElement(By.tagName("body"))
      clickAction
      waitForPageToBeLoaded(isElementStale(body), "Waiting for new page to be loaded failed")
    }
    else clickAction

    Page.instantiate[T]
  }
*/

  private def isElementStale(element: WebElement) : Boolean ={
    try{
      element.getAttribute("hello")
      false
    } catch{
      case x : StaleElementReferenceException =>  true
    }
  }

  /*def refreshPage() {
    doActionAndWaitForPageLoad[Page](driver.navigate().refresh(), false)
  }

  class HmrcPageWaitException(exceptionMessage: String) extends Exception(exceptionMessage)
*/
}

object Page {

  def getWindowHandleByTitle(title: String)(implicit webDriver: Driver): String = {
    val driver = webDriver.getInstance()
    val currentPageHandle = driver.getWindowHandle

    //TestHelper.waitForCondition(driver.getWindowHandles.size > 1, "Window count should be more than 1", 30)

    for (handle <- driver.getWindowHandles) {
      val screen = driver.switchTo().window(handle)
    //  TestHelper.waitForCondition(driver.getWindowHandle == handle, "Switching to window failed", 10)
      if (screen.getTitle == title) {
        return handle
      }
    }

    // Switching back to the original page if the searched title is not found
    driver.switchTo().window(currentPageHandle)

    throw new Exception(s"Could not find window with title '$title'")
  }

  /*def instantiate[T : NotNothing](implicit webDriver: Driver, manifest: Manifest[T]): T = {
    var r: Class[_] = manifest.runtimeClass

    try {
      r.getConstructor(classOf[Driver]).newInstance(webDriver).asInstanceOf[T]
    } catch {
      case e: NoSuchMethodException =>
        try {
          r.newInstance().asInstanceOf[T]
        } catch {
          case e: Exception => throw new IllegalArgumentException(s"Could not instantiate $r")
        }
    }
  }*/

}