
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/medvedev_vv/git/PlaySlick/conf/routes
// @DATE:Wed Jan 25 17:06:06 NOVT 2017

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseAuthorsController AuthorsController = new controllers.ReverseAuthorsController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseBooksController BooksController = new controllers.ReverseBooksController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseAuthorsController AuthorsController = new controllers.javascript.ReverseAuthorsController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseBooksController BooksController = new controllers.javascript.ReverseBooksController(RoutesPrefix.byNamePrefix());
  }

}
