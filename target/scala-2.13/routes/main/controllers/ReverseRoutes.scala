// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/mateu/IdeaProjects/play-samples-play-java-jpa-example/conf/routes
// @DATE:Tue May 19 17:13:49 CEST 2020

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers {

  // @LINE:13
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:13
    def at(file:String): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[String]].unbind("file", file))
    }
  
  }

  // @LINE:6
  class ReverseGeneralController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:9
    def deleteQuote(id:Long): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "quotes/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
    // @LINE:10
    def getAllQuotes(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "quotes")
    }
  
    // @LINE:7
    def getQuote(id:Long): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "quotes/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
    // @LINE:8
    def addQuote(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "quotes")
    }
  
    // @LINE:6
    def login(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "login")
    }
  
  }


}
