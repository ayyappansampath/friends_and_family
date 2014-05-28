package framework

import org.scalatest._
import java.io.{ IOException, File }
import org.openqa.selenium.{ TakesScreenshot, OutputType }
import org.apache.commons.io.FileUtils
import test.framework.{Driver, SingletonDriver}

trait SeleniumBase extends FlatSpec with Matchers with BeforeAndAfterAll with BeforeAndAfterEach {
//with CookieManagement {

  implicit val driver: Driver = SingletonDriver

 // override protected def beforeEach() = deleteBrowserCookies()

  override protected def afterEach() = driver.closeNewlyOpenedWindows()

  override protected def afterAll() = driver.closeInstance()

 // def isLocal() = environment.equals(Environment.Local)

 // def firefoxOnly() =  settings.BROWSER == "firefox"

  private def takeScreenShot(testMethodName: String) {

    println(s"******************* $testMethodName - FAILED *******************")
    println(s"Taking screenshot of '$testMethodName'")
    val srcFile: File = driver.getInstance().asInstanceOf[TakesScreenshot].getScreenshotAs(OutputType.FILE)
    val screenShot: String = "./target/screenshots/" + testMethodName + ".png"
    try {
      FileUtils.copyFile(srcFile, new File(screenShot))
    } catch {
      case e: IOException => e.printStackTrace()
    }
  }

  override def withFixture(test: NoArgTest) = {
    super.withFixture(test) match {
      case f: Failed => takeScreenShot(test.name); f
      case otherOutcome => otherOutcome
    }
  }
}