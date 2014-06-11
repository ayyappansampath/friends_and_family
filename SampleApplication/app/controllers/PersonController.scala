package controllers

import play.api.data.Forms._
import model.Person
import play.api.data._
import controllers.services.{SampleApplicationService, PersonService}
import scala.concurrent.future
import play.api.mvc._

/**
 * Created by user02 on 5/27/14.
 */
class PersonController(personService : PersonService) extends Controller {


  def this() = this(new SampleApplicationService())

  def showPage =  Action.async { implicit request =>
      personService.showAddPersonPage(request)

  }

  def submit = Action { implicit request =>
    Ok(views.html.index("successful"))
  }

}
