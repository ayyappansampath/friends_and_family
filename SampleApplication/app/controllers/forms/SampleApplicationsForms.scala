package controllers.forms

import play.api.data.Forms._
import model.Person
import play.api.data._


import controllers.forms.AddPersonFormFields._
/**
 * Created by user02 on 6/2/14.
 */
object SampleApplicationsForms {

  val addPersonForm = Form(
    mapping(
      firstName -> nonEmptyText(),
      lastName -> nonEmptyText,
      emailAddress -> nonEmptyText
    ) (Person.apply)(Person.unapply)
  )

}



object AddPersonFormFields {

  val firstName = "firstName"
  val lastName = "lastName"
  val emailAddress = "email"

}

