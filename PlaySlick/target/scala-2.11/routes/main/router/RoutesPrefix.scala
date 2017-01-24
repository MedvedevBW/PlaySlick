
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/medvedev_vv/IdeaProjects/play-slick-master2/conf/routes
// @DATE:Tue Jan 24 11:37:16 NOVT 2017


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
