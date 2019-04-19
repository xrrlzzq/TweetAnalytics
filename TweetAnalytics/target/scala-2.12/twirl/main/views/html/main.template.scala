
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import scala.collection.JavaConverters._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import play.data._
import play.core.j.PlayFormsMagicForJava._

object main extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[play.mvc.Http.Request,String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /*
 * This template is called from the `index` template. This template
 * handles the rendering of the page header and body tags. It takes
 * two arguments, a `String` for the title of the page and an `Html`
 * object to insert into the body of the page.
 */
  def apply/*7.2*/(request: play.mvc.Http.Request,title:String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*7.62*/("""

"""),format.raw/*9.1*/("""<!DOCTYPE html>
<html lang="en">
    <head>
        """),format.raw/*12.62*/("""
        """),format.raw/*13.9*/("""<title>"""),_display_(/*13.17*/title),format.raw/*13.22*/("""</title>
        <script type='text/javascript' src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
        <script type='text/javascript' src='"""),_display_(/*15.46*/routes/*15.52*/.Assets.at("javascripts/index.js")),format.raw/*15.86*/("""'></script>  
    </head>
    <body data-ws-url=""""),_display_(/*17.25*/routes/*17.31*/.HomeController.ws.webSocketURL(request)),format.raw/*17.71*/("""">
        """),format.raw/*19.32*/("""
        """),_display_(/*20.10*/content),format.raw/*20.17*/("""
        """),format.raw/*21.9*/("""<div id="twitter" style="color:green"> </div>
    </body>
</html>
"""))
      }
    }
  }

  def render(request:play.mvc.Http.Request,title:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(request,title)(content)

  def f:((play.mvc.Http.Request,String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (request,title) => (content) => apply(request,title)(content)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Thu Apr 12 21:56:27 EDT 2018
                  SOURCE: C:/Users/XieRong/Desktop/demo2/project2/TweetAnalytics/app/views/main.scala.html
                  HASH: e9680d3e69c0f39e9bca42c4b5c0acfbff4f3436
                  MATRIX: 1233->266|1388->326|1418->330|1501->438|1538->448|1573->456|1599->461|1781->616|1796->622|1851->656|1930->708|1945->714|2006->754|2046->857|2084->868|2112->875|2149->885
                  LINES: 33->7|38->7|40->9|43->12|44->13|44->13|44->13|46->15|46->15|46->15|48->17|48->17|48->17|49->19|50->20|50->20|51->21
                  -- GENERATED --
              */
          