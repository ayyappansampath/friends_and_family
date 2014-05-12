package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    
    val myList = "This" :: "is" :: "immutable" :: Nil
    
    Ok(views.html.index("Your new " + myList(2) +" is ready."))
  }

}