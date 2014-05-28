package utils

import framework.SeleniumBase

/**
 * Created by user02 on 5/27/14.
 */
trait SampleApplicationSeleniumBase extends SeleniumBase {
  val host = "localhost"
  val port = 9000
  val server = s"http://$host:$port"

  val landingPage = s"$server/"

  def fullyQualifiedUrl(path:String) = s"$landingPage$path"
}
