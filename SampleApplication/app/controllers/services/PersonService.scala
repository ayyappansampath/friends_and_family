package controllers.services

import play.api.mvc.{SimpleResult, AnyContent, Request}
import scala.concurrent.Future
import play.api.mvc._
import play.api.mvc.Results._


import controllers.forms.SampleApplicationsForms._
import sun.awt.X11.XConstants

/**
 * Created by user02 on 6/2/14.
 */
trait PersonService extends BaseSampleApplicationService{

  def showAddPersonPage(implicit request : Request[AnyContent]) : Future[SimpleResult] = {
    Future.successful(Ok(views.html.add_person(addPersonForm)))
  }

  def processAddPerson(implicit request : Request[AnyContent]) : Future[SimpleResult] = {
    addPersonForm.bindFromRequest.fold(
    errors => Future.successful(BadRequest(views.html.add_person(errors))),
    person => Future.successful(Ok(views.html.add_person(addPersonForm.fill(person))))
    )
  }
}
