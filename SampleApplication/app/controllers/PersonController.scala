package controllers

import play.api.mvc._
import play.api.data.Forms._
import model.Person
import play.api.data._

/**
 * Created by user02 on 5/27/14.
 */
object PersonController extends Controller {

  val unfilledPersonForm : Form[Person] = Form (

  mapping(
  "firstName" -> nonEmptyText(minLength = 12),
  "lastName" -> nonEmptyText,
  "email" -> email
  )

  {
    // Binding: Create a Person from the mapping result
    (firstName, lastName, email) => Person(firstName, lastName, email)
  }
  {
    // Unbinding: Create the mapping values from an existing Person value
    person => Some(person.firstName, person.firstName,person.email)
  }
  )

  def loadAddPersonPage = Action {
    Ok(views.html.add_person(unfilledPersonForm))
  }

  def submit = Action {
    Ok(views.html.index("successful"))
  }

}
