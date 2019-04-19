
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

object action extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[play.mvc.Http.Request,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(request: play.mvc.Http.Request)(content:String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.50*/("""
"""),_display_(/*2.2*/main(request,"Welcome to use TweetAnalytics!")/*2.48*/{_display_(Seq[Any](format.raw/*2.49*/("""
 """),format.raw/*3.2*/("""<h2>Welcome to use TweetAnalytics!</h2>
 <form action=""""),_display_(/*4.17*/routes/*4.23*/.TwitterActionController.search),format.raw/*4.54*/("""" method="get">
        please enter key word: <input type="text" name="key">
        <input type="submit" value="search">
 </form>
        
        """),_display_(/*9.10*/Html(content)),format.raw/*9.23*/("""
""")))}),format.raw/*10.2*/("""

"""))
      }
    }
  }

  def render(request:play.mvc.Http.Request,content:String): play.twirl.api.HtmlFormat.Appendable = apply(request)(content)

  def f:((play.mvc.Http.Request) => (String) => play.twirl.api.HtmlFormat.Appendable) = (request) => (content) => apply(request)(content)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Thu Apr 12 21:56:27 EDT 2018
                  SOURCE: C:/Users/XieRong/Desktop/demo2/project2/TweetAnalytics/app/views/action.scala.html
                  HASH: f294745434be1565fb537c6813b17157f8ea204d
                  MATRIX: 971->1|1114->49|1142->52|1196->98|1234->99|1263->102|1346->159|1360->165|1411->196|1592->351|1625->364|1658->367
                  LINES: 28->1|33->1|34->2|34->2|34->2|35->3|36->4|36->4|36->4|41->9|41->9|42->10
                  -- GENERATED --
              */
          