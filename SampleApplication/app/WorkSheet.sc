import controllers.routes

 def getUrl(port : Int) = "http://localhost" + port +controllers.routes.ref.Application.loadAddPersonPage()

getUrl(9000)