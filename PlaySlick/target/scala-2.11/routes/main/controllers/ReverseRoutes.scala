
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/medvedev_vv/git/PlaySlick/conf/routes
// @DATE:Wed Jan 25 17:06:06 NOVT 2017

import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset

// @LINE:6
package controllers {

  // @LINE:6
  class ReverseAuthorsController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:9
    def update(id:Int): Call = {
      import ReverseRouteContext.empty
      Call("PUT", _prefix + { _defaultPrefix } + "authors/" + implicitly[PathBindable[Int]].unbind("id", id))
    }
  
    // @LINE:8
    def create(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "authors")
    }
  
    // @LINE:10
    def delete(id:Int): Call = {
      import ReverseRouteContext.empty
      Call("DELETE", _prefix + { _defaultPrefix } + "authors/" + implicitly[PathBindable[Int]].unbind("id", id))
    }
  
    // @LINE:7
    def findById(id:Int): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "authors/" + implicitly[PathBindable[Int]].unbind("id", id))
    }
  
    // @LINE:6
    def findAll(p:Int = 0, l:Int = 10, s:Int = 1, f:String = ""): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "authors" + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(l == 10) None else Some(implicitly[QueryStringBindable[Int]].unbind("l", l)), if(s == 1) None else Some(implicitly[QueryStringBindable[Int]].unbind("s", s)), if(f == "") None else Some(implicitly[QueryStringBindable[String]].unbind("f", f)))))
    }
  
  }

  // @LINE:13
  class ReverseBooksController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:16
    def update(id:Int): Call = {
      import ReverseRouteContext.empty
      Call("PUT", _prefix + { _defaultPrefix } + "books/" + implicitly[PathBindable[Int]].unbind("id", id))
    }
  
    // @LINE:15
    def create(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "books")
    }
  
    // @LINE:17
    def delete(id:Int): Call = {
      import ReverseRouteContext.empty
      Call("DELETE", _prefix + { _defaultPrefix } + "books/" + implicitly[PathBindable[Int]].unbind("id", id))
    }
  
    // @LINE:14
    def findById(id:Int): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "books/" + implicitly[PathBindable[Int]].unbind("id", id))
    }
  
    // @LINE:13
    def findAll(p:Int = 0, l:Int = 10, s:Int = 1, f:String = "", a:Option[Int] = None): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "books" + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(l == 10) None else Some(implicitly[QueryStringBindable[Int]].unbind("l", l)), if(s == 1) None else Some(implicitly[QueryStringBindable[Int]].unbind("s", s)), if(f == "") None else Some(implicitly[QueryStringBindable[String]].unbind("f", f)), if(a == None) None else Some(implicitly[QueryStringBindable[Option[Int]]].unbind("a", a)))))
    }
  
  }


}