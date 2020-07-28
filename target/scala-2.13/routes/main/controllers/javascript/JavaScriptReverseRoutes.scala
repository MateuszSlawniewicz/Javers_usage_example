// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/mateu/IdeaProjects/play-samples-play-java-jpa-example/conf/routes
// @DATE:Thu May 21 14:25:41 CEST 2020

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers.javascript {

  // @LINE:17
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:17
    def at: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.at",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }

  // @LINE:6
  class ReverseGeneralController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:6
    def corsOk: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.GeneralController.corsOk",
      """
        function() {
        
          if (true) {
            return _wA({method:"OPTIONS", url:"""" + _prefix + """"})
          }
        
        }
      """
    )
  
    // @LINE:13
    def deleteQuote: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.GeneralController.deleteQuote",
      """
        function(id0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "quotes/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:14
    def getAllQuotes: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.GeneralController.getAllQuotes",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "quotes"})
        }
      """
    )
  
    // @LINE:11
    def getQuote: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.GeneralController.getQuote",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "quotes/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:12
    def addQuote: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.GeneralController.addQuote",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "quotes"})
        }
      """
    )
  
    // @LINE:9
    def corsOk2: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.GeneralController.corsOk2",
      """
        function(id0) {
          return _wA({method:"OPTIONS", url:"""" + _prefix + { _defaultPrefix } + """" + "quotes/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:10
    def login: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.GeneralController.login",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
        }
      """
    )
  
  }


}
