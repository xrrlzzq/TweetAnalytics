
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

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[play.mvc.Http.Request,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(request: play.mvc.Http.Request):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.34*/("""

"""),_display_(/*3.2*/main(request,"Welcome to use TweetAnalytics!")/*3.48*/ {_display_(Seq[Any](format.raw/*3.50*/("""
 """),format.raw/*4.2*/("""<h2>Welcome to use TweetAnalytics!</h2>
 <form action=""""),_display_(/*5.17*/routes/*5.23*/.TwitterActionController.search),format.raw/*5.54*/("""" method="get">
        please enter key word: <input type="text" name="key">
        <input type="submit" value="search">
</form>
""")))}),format.raw/*9.2*/("""

"""))
      }
    }
  }

  def render(request:play.mvc.Http.Request): play.twirl.api.HtmlFormat.Appendable = apply(request)

  def f:((play.mvc.Http.Request) => play.twirl.api.HtmlFormat.Appendable) = (request) => apply(request)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Thu Apr 12 21:56:27 EDT 2018
                  SOURCE: C:/Users/XieRong/Desktop/demo2/project2/TweetAnalytics/app/views/index.scala.html
                  HASH: 03927bd565dc0af3a88b46fd9c857ea9aa027da3
                  MATRIX: 963->1|1090->33|1120->38|1174->84|1213->86|1242->89|1325->146|1339->152|1390->183|1555->319
                  LINES: 28->1|33->1|35->3|35->3|35->3|36->4|37->5|37->5|37->5|41->9
                  -- GENERATED --
              */
          