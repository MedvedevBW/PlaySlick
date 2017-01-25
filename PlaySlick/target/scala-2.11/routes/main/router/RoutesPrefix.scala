
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/medvedev_vv/git/PlaySlick/conf/routes
// @DATE:Wed Jan 25 17:06:06 NOVT 2017


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
