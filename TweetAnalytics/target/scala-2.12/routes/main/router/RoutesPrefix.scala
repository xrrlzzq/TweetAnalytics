
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/XieRong/Desktop/demo2/project2/TweetAnalytics/conf/routes
// @DATE:Thu Apr 12 21:56:27 EDT 2018


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
