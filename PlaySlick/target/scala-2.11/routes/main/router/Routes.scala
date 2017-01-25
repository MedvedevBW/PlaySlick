
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/medvedev_vv/git/PlaySlick/conf/routes
// @DATE:Wed Jan 25 17:06:06 NOVT 2017

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  AuthorsController_0: controllers.AuthorsController,
  // @LINE:13
  BooksController_1: controllers.BooksController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    AuthorsController_0: controllers.AuthorsController,
    // @LINE:13
    BooksController_1: controllers.BooksController
  ) = this(errorHandler, AuthorsController_0, BooksController_1, "/")

  import ReverseRouteContext.empty

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, AuthorsController_0, BooksController_1, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """authors""", """controllers.AuthorsController.findAll(p:Int ?= 0, l:Int ?= 10, s:Int ?= 1, f:String ?= "")"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """authors/$id<[^/]+>""", """controllers.AuthorsController.findById(id:Int)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """authors""", """controllers.AuthorsController.create"""),
    ("""PUT""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """authors/$id<[^/]+>""", """controllers.AuthorsController.update(id:Int)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """authors/$id<[^/]+>""", """controllers.AuthorsController.delete(id:Int)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """books""", """controllers.BooksController.findAll(p:Int ?= 0, l:Int ?= 10, s:Int ?= 1, f:String ?= "", a:Option[Int] ?= None)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """books/$id<[^/]+>""", """controllers.BooksController.findById(id:Int)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """books""", """controllers.BooksController.create"""),
    ("""PUT""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """books/$id<[^/]+>""", """controllers.BooksController.update(id:Int)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """books/$id<[^/]+>""", """controllers.BooksController.delete(id:Int)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_AuthorsController_findAll0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("authors")))
  )
  private[this] lazy val controllers_AuthorsController_findAll0_invoker = createInvoker(
    AuthorsController_0.findAll(fakeValue[Int], fakeValue[Int], fakeValue[Int], fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AuthorsController",
      "findAll",
      Seq(classOf[Int], classOf[Int], classOf[Int], classOf[String]),
      "GET",
      """ authors""",
      this.prefix + """authors"""
    )
  )

  // @LINE:7
  private[this] lazy val controllers_AuthorsController_findById1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("authors/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_AuthorsController_findById1_invoker = createInvoker(
    AuthorsController_0.findById(fakeValue[Int]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AuthorsController",
      "findById",
      Seq(classOf[Int]),
      "GET",
      """""",
      this.prefix + """authors/$id<[^/]+>"""
    )
  )

  // @LINE:8
  private[this] lazy val controllers_AuthorsController_create2_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("authors")))
  )
  private[this] lazy val controllers_AuthorsController_create2_invoker = createInvoker(
    AuthorsController_0.create,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AuthorsController",
      "create",
      Nil,
      "POST",
      """""",
      this.prefix + """authors"""
    )
  )

  // @LINE:9
  private[this] lazy val controllers_AuthorsController_update3_route = Route("PUT",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("authors/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_AuthorsController_update3_invoker = createInvoker(
    AuthorsController_0.update(fakeValue[Int]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AuthorsController",
      "update",
      Seq(classOf[Int]),
      "PUT",
      """""",
      this.prefix + """authors/$id<[^/]+>"""
    )
  )

  // @LINE:10
  private[this] lazy val controllers_AuthorsController_delete4_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("authors/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_AuthorsController_delete4_invoker = createInvoker(
    AuthorsController_0.delete(fakeValue[Int]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AuthorsController",
      "delete",
      Seq(classOf[Int]),
      "DELETE",
      """""",
      this.prefix + """authors/$id<[^/]+>"""
    )
  )

  // @LINE:13
  private[this] lazy val controllers_BooksController_findAll5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("books")))
  )
  private[this] lazy val controllers_BooksController_findAll5_invoker = createInvoker(
    BooksController_1.findAll(fakeValue[Int], fakeValue[Int], fakeValue[Int], fakeValue[String], fakeValue[Option[Int]]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.BooksController",
      "findAll",
      Seq(classOf[Int], classOf[Int], classOf[Int], classOf[String], classOf[Option[Int]]),
      "GET",
      """ books""",
      this.prefix + """books"""
    )
  )

  // @LINE:14
  private[this] lazy val controllers_BooksController_findById6_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("books/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_BooksController_findById6_invoker = createInvoker(
    BooksController_1.findById(fakeValue[Int]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.BooksController",
      "findById",
      Seq(classOf[Int]),
      "GET",
      """""",
      this.prefix + """books/$id<[^/]+>"""
    )
  )

  // @LINE:15
  private[this] lazy val controllers_BooksController_create7_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("books")))
  )
  private[this] lazy val controllers_BooksController_create7_invoker = createInvoker(
    BooksController_1.create,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.BooksController",
      "create",
      Nil,
      "POST",
      """""",
      this.prefix + """books"""
    )
  )

  // @LINE:16
  private[this] lazy val controllers_BooksController_update8_route = Route("PUT",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("books/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_BooksController_update8_invoker = createInvoker(
    BooksController_1.update(fakeValue[Int]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.BooksController",
      "update",
      Seq(classOf[Int]),
      "PUT",
      """""",
      this.prefix + """books/$id<[^/]+>"""
    )
  )

  // @LINE:17
  private[this] lazy val controllers_BooksController_delete9_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("books/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_BooksController_delete9_invoker = createInvoker(
    BooksController_1.delete(fakeValue[Int]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.BooksController",
      "delete",
      Seq(classOf[Int]),
      "DELETE",
      """""",
      this.prefix + """books/$id<[^/]+>"""
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_AuthorsController_findAll0_route(params) =>
      call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[Int]("l", Some(10)), params.fromQuery[Int]("s", Some(1)), params.fromQuery[String]("f", Some(""))) { (p, l, s, f) =>
        controllers_AuthorsController_findAll0_invoker.call(AuthorsController_0.findAll(p, l, s, f))
      }
  
    // @LINE:7
    case controllers_AuthorsController_findById1_route(params) =>
      call(params.fromPath[Int]("id", None)) { (id) =>
        controllers_AuthorsController_findById1_invoker.call(AuthorsController_0.findById(id))
      }
  
    // @LINE:8
    case controllers_AuthorsController_create2_route(params) =>
      call { 
        controllers_AuthorsController_create2_invoker.call(AuthorsController_0.create)
      }
  
    // @LINE:9
    case controllers_AuthorsController_update3_route(params) =>
      call(params.fromPath[Int]("id", None)) { (id) =>
        controllers_AuthorsController_update3_invoker.call(AuthorsController_0.update(id))
      }
  
    // @LINE:10
    case controllers_AuthorsController_delete4_route(params) =>
      call(params.fromPath[Int]("id", None)) { (id) =>
        controllers_AuthorsController_delete4_invoker.call(AuthorsController_0.delete(id))
      }
  
    // @LINE:13
    case controllers_BooksController_findAll5_route(params) =>
      call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[Int]("l", Some(10)), params.fromQuery[Int]("s", Some(1)), params.fromQuery[String]("f", Some("")), params.fromQuery[Option[Int]]("a", Some(None))) { (p, l, s, f, a) =>
        controllers_BooksController_findAll5_invoker.call(BooksController_1.findAll(p, l, s, f, a))
      }
  
    // @LINE:14
    case controllers_BooksController_findById6_route(params) =>
      call(params.fromPath[Int]("id", None)) { (id) =>
        controllers_BooksController_findById6_invoker.call(BooksController_1.findById(id))
      }
  
    // @LINE:15
    case controllers_BooksController_create7_route(params) =>
      call { 
        controllers_BooksController_create7_invoker.call(BooksController_1.create)
      }
  
    // @LINE:16
    case controllers_BooksController_update8_route(params) =>
      call(params.fromPath[Int]("id", None)) { (id) =>
        controllers_BooksController_update8_invoker.call(BooksController_1.update(id))
      }
  
    // @LINE:17
    case controllers_BooksController_delete9_route(params) =>
      call(params.fromPath[Int]("id", None)) { (id) =>
        controllers_BooksController_delete9_invoker.call(BooksController_1.delete(id))
      }
  }
}