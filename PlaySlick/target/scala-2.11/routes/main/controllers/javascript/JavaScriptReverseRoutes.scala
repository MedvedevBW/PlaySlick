
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/medvedev_vv/git/PlaySlick/conf/routes
// @DATE:Wed Jan 25 17:06:06 NOVT 2017

import play.api.routing.JavaScriptReverseRoute
import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset

// @LINE:6
package controllers.javascript {
  import ReverseRouteContext.empty

  // @LINE:6
  class ReverseAuthorsController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:9
    def update: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.AuthorsController.update",
      """
        function(id) {
          return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "authors/" + (""" + implicitly[PathBindable[Int]].javascriptUnbind + """)("id", id)})
        }
      """
    )
  
    // @LINE:8
    def create: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.AuthorsController.create",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "authors"})
        }
      """
    )
  
    // @LINE:10
    def delete: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.AuthorsController.delete",
      """
        function(id) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "authors/" + (""" + implicitly[PathBindable[Int]].javascriptUnbind + """)("id", id)})
        }
      """
    )
  
    // @LINE:7
    def findById: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.AuthorsController.findById",
      """
        function(id) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "authors/" + (""" + implicitly[PathBindable[Int]].javascriptUnbind + """)("id", id)})
        }
      """
    )
  
    // @LINE:6
    def findAll: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.AuthorsController.findAll",
      """
        function(p,l,s,f) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "authors" + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (l == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("l", l)), (s == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("s", s)), (f == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("f", f))])})
        }
      """
    )
  
  }

  // @LINE:13
  class ReverseBooksController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:16
    def update: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.BooksController.update",
      """
        function(id) {
          return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "books/" + (""" + implicitly[PathBindable[Int]].javascriptUnbind + """)("id", id)})
        }
      """
    )
  
    // @LINE:15
    def create: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.BooksController.create",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "books"})
        }
      """
    )
  
    // @LINE:17
    def delete: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.BooksController.delete",
      """
        function(id) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "books/" + (""" + implicitly[PathBindable[Int]].javascriptUnbind + """)("id", id)})
        }
      """
    )
  
    // @LINE:14
    def findById: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.BooksController.findById",
      """
        function(id) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "books/" + (""" + implicitly[PathBindable[Int]].javascriptUnbind + """)("id", id)})
        }
      """
    )
  
    // @LINE:13
    def findAll: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.BooksController.findAll",
      """
        function(p,l,s,f,a) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "books" + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (l == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("l", l)), (s == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("s", s)), (f == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("f", f)), (a == null ? null : (""" + implicitly[QueryStringBindable[Option[Int]]].javascriptUnbind + """)("a", a))])})
        }
      """
    )
  
  }


}