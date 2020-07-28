// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/mateu/IdeaProjects/play-samples-play-java-jpa-example/conf/routes
// @DATE:Thu May 21 14:25:41 CEST 2020

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  GeneralController_0: controllers.GeneralController,
  // @LINE:17
  Assets_1: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    GeneralController_0: controllers.GeneralController,
    // @LINE:17
    Assets_1: controllers.Assets
  ) = this(errorHandler, GeneralController_0, Assets_1, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, GeneralController_0, Assets_1, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""OPTIONS""", this.prefix, """controllers.GeneralController.corsOk(req:Request)"""),
    ("""OPTIONS""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """login""", """controllers.GeneralController.corsOk(req:Request)"""),
    ("""OPTIONS""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """quotes""", """controllers.GeneralController.corsOk(req:Request)"""),
    ("""OPTIONS""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """quotes/""" + "$" + """id<[^/]+>""", """controllers.GeneralController.corsOk2(req:Request, id:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """login""", """controllers.GeneralController.login(req:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """quotes/""" + "$" + """id<[^/]+>""", """controllers.GeneralController.getQuote(re:Request, id:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """quotes""", """controllers.GeneralController.addQuote(req:Request)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """quotes/""" + "$" + """id<[^/]+>""", """controllers.GeneralController.deleteQuote(req:Request, id:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """quotes""", """controllers.GeneralController.getAllQuotes(req:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.at(path:String = "/public", file:String)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_GeneralController_corsOk0_route = Route("OPTIONS",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_GeneralController_corsOk0_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      GeneralController_0.corsOk(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.GeneralController",
      "corsOk",
      Seq(classOf[play.mvc.Http.Request]),
      "OPTIONS",
      this.prefix + """""",
      """ Main Page""",
      Seq()
    )
  )

  // @LINE:7
  private[this] lazy val controllers_GeneralController_corsOk1_route = Route("OPTIONS",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("login")))
  )
  private[this] lazy val controllers_GeneralController_corsOk1_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      GeneralController_0.corsOk(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.GeneralController",
      "corsOk",
      Seq(classOf[play.mvc.Http.Request]),
      "OPTIONS",
      this.prefix + """login""",
      """""",
      Seq()
    )
  )

  // @LINE:8
  private[this] lazy val controllers_GeneralController_corsOk2_route = Route("OPTIONS",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("quotes")))
  )
  private[this] lazy val controllers_GeneralController_corsOk2_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      GeneralController_0.corsOk(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.GeneralController",
      "corsOk",
      Seq(classOf[play.mvc.Http.Request]),
      "OPTIONS",
      this.prefix + """quotes""",
      """""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val controllers_GeneralController_corsOk23_route = Route("OPTIONS",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("quotes/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_GeneralController_corsOk23_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      GeneralController_0.corsOk2(fakeValue[play.mvc.Http.Request], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.GeneralController",
      "corsOk2",
      Seq(classOf[play.mvc.Http.Request], classOf[Long]),
      "OPTIONS",
      this.prefix + """quotes/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:10
  private[this] lazy val controllers_GeneralController_login4_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("login")))
  )
  private[this] lazy val controllers_GeneralController_login4_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      GeneralController_0.login(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.GeneralController",
      "login",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """login""",
      """""",
      Seq()
    )
  )

  // @LINE:11
  private[this] lazy val controllers_GeneralController_getQuote5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("quotes/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_GeneralController_getQuote5_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      GeneralController_0.getQuote(fakeValue[play.mvc.Http.Request], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.GeneralController",
      "getQuote",
      Seq(classOf[play.mvc.Http.Request], classOf[Long]),
      "GET",
      this.prefix + """quotes/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:12
  private[this] lazy val controllers_GeneralController_addQuote6_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("quotes")))
  )
  private[this] lazy val controllers_GeneralController_addQuote6_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      GeneralController_0.addQuote(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.GeneralController",
      "addQuote",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """quotes""",
      """""",
      Seq()
    )
  )

  // @LINE:13
  private[this] lazy val controllers_GeneralController_deleteQuote7_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("quotes/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_GeneralController_deleteQuote7_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      GeneralController_0.deleteQuote(fakeValue[play.mvc.Http.Request], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.GeneralController",
      "deleteQuote",
      Seq(classOf[play.mvc.Http.Request], classOf[Long]),
      "DELETE",
      this.prefix + """quotes/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:14
  private[this] lazy val controllers_GeneralController_getAllQuotes8_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("quotes")))
  )
  private[this] lazy val controllers_GeneralController_getAllQuotes8_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      GeneralController_0.getAllQuotes(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.GeneralController",
      "getAllQuotes",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """quotes""",
      """""",
      Seq()
    )
  )

  // @LINE:17
  private[this] lazy val controllers_Assets_at9_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_at9_invoker = createInvoker(
    Assets_1.at(fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "at",
      Seq(classOf[String], classOf[String]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_GeneralController_corsOk0_route(params@_) =>
      call { 
        controllers_GeneralController_corsOk0_invoker.call(
          req => GeneralController_0.corsOk(req))
      }
  
    // @LINE:7
    case controllers_GeneralController_corsOk1_route(params@_) =>
      call { 
        controllers_GeneralController_corsOk1_invoker.call(
          req => GeneralController_0.corsOk(req))
      }
  
    // @LINE:8
    case controllers_GeneralController_corsOk2_route(params@_) =>
      call { 
        controllers_GeneralController_corsOk2_invoker.call(
          req => GeneralController_0.corsOk(req))
      }
  
    // @LINE:9
    case controllers_GeneralController_corsOk23_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_GeneralController_corsOk23_invoker.call(
          req => GeneralController_0.corsOk2(req, id))
      }
  
    // @LINE:10
    case controllers_GeneralController_login4_route(params@_) =>
      call { 
        controllers_GeneralController_login4_invoker.call(
          req => GeneralController_0.login(req))
      }
  
    // @LINE:11
    case controllers_GeneralController_getQuote5_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_GeneralController_getQuote5_invoker.call(
          req => GeneralController_0.getQuote(req, id))
      }
  
    // @LINE:12
    case controllers_GeneralController_addQuote6_route(params@_) =>
      call { 
        controllers_GeneralController_addQuote6_invoker.call(
          req => GeneralController_0.addQuote(req))
      }
  
    // @LINE:13
    case controllers_GeneralController_deleteQuote7_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_GeneralController_deleteQuote7_invoker.call(
          req => GeneralController_0.deleteQuote(req, id))
      }
  
    // @LINE:14
    case controllers_GeneralController_getAllQuotes8_route(params@_) =>
      call { 
        controllers_GeneralController_getAllQuotes8_invoker.call(
          req => GeneralController_0.getAllQuotes(req))
      }
  
    // @LINE:17
    case controllers_Assets_at9_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at9_invoker.call(Assets_1.at(path, file))
      }
  }
}
