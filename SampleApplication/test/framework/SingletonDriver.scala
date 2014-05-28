package test.framework

import org.openqa.selenium.{WebDriver}
import org.openqa.selenium.firefox.{ FirefoxDriver, FirefoxProfile }
import org.openqa.selenium.remote.{RemoteWebDriver, DesiredCapabilities}
import scala.collection.JavaConversions._

object SingletonDriver extends Driver
class Driver {
  private val SAUCY = "saucy"
  private val ZAP = "zap"

  private var instance: WebDriver = null
  private var baseWindowHandle : String = null
  var javascriptEnabled: Boolean = true

  def setJavascript(enabled: Boolean) {
    javascriptEnabled = enabled
    if (instance != null) closeInstance()
  }

  def getInstance(): WebDriver = {
    if (instance == null) {
      initialiseBrowser()
    }
    instance
  }

  private def initialiseBrowser(){
    instance = createBrowser()
    instance.manage().window().maximize()
    baseWindowHandle = instance.getWindowHandle
  }

  def closeInstance() = {
    if (instance != null) {

      closeNewlyOpenedWindows()

      // TODO: This is not fully tested with saucy, we may need to do something else to close the test down after fininshing
      //      this also should should be done for local tests once we can find a hook to close down driver after all tests
      instance.close()
      instance.quit()
      instance = null
      baseWindowHandle = null
    }
  }

  def closeNewlyOpenedWindows() {
    instance.getWindowHandles.toList.foreach(w =>
      if (instance.getWindowHandle != baseWindowHandle) instance.switchTo().window(w).close()
    )

    instance.switchTo().window(baseWindowHandle)
  }

  private def createBrowser(): WebDriver = {
    def createFirefoxDriver: WebDriver = {
      val profile: FirefoxProfile = new FirefoxProfile
      profile.setPreference("javascript.enabled", javascriptEnabled)
      profile.setAcceptUntrustedCertificates(true)
      new FirefoxDriver(profile)
    }

  /*  def createPhantomJsDriver: WebDriver = {
      val cap = new DesiredCapabilities()
      cap.setJavascriptEnabled(javascriptEnabled)
      new PhantomJSDriver(cap)
    }*/

   /* def createSaucyDriver: WebDriver = {
      val capabilities = DesiredCapabilities.firefox()
      capabilities.setCapability("version", "22")
      capabilities.setCapability("platform", "OS X 10.9")
      capabilities.setCapability("name", "Frontend Integration") // TODO: should we add a timestamp here?

      new RemoteWebDriver(
        new java.net.URL("http://Optimus:3e4f3978-2b40-4965-a6b3-4fb7243bc1f2@ondemand.saucelabs.com:80/wd/hub"), //
        capabilities);
    }*/

    /*def createZapDriver: WebDriver = {
      val profile: FirefoxProfile = new FirefoxProfile
      profile.setPreference("javascript.enabled", true)
      profile.setAcceptUntrustedCertificates(true)
      profile.setPreference("network.proxy.type", 1)
      profile.setPreference("network.proxy.http", "localhost")
      profile.setPreference("network.proxy.http_port", 11000)
      profile.setPreference("network.proxy.share_proxy_settings", true)
      profile.setPreference("network.proxy.no_proxies_on", "")

      new FirefoxDriver(profile)
    }*/

   /* settings.BROWSER.toLowerCase match {
      case "firefox" => createFirefoxDriver
    //  case "phantomjs" => createPhantomJsDriver
    //  case SAUCY => createSaucyDriver
    //  case ZAP => createZapDriver
      case _ => throw new IllegalArgumentException(s"Browser type ${settings.BROWSER} not recognised")
    }*/
  createFirefoxDriver
  }
}
