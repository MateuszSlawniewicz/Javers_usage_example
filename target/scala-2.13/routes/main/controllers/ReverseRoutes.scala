// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/mateu/IdeaProjects/play-samples-play-java-jpa-example/conf/routes
// @DATE:Thu May 21 14:25:41 CEST 2020

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers {

  // @LINE:17
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:17
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

  
    // @LINE:6
    def corsOk(): Call = {
    
      () match {
      
        // @LINE:6
        case ()  =>
          
          Call("OPTIONS", _prefix)
      
      }
    
    }
  
    // @LINE:13
    def deleteQuote(id:Long): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "quotes/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
    // @LINE:14
    def getAllQuotes(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "quotes")
    }
  
    // @LINE:11
    def getQuote(id:Long): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "quotes/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
    // @LINE:12
    def addQuote(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "quotes")
    }
  
    // @LINE:9
    def corsOk2(id:Long): Call = {
      
      Call("OPTIONS", _prefix + { _defaultPrefix } + "quotes/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
    // @LINE:10
    def login(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "login")
    }
  
  }


}
