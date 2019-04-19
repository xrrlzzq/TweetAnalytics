
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/XieRong/Desktop/demo2/project2/TweetAnalytics/conf/routes
// @DATE:Thu Apr 12 21:56:27 EDT 2018

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseTwitterActionController TwitterActionController = new controllers.ReverseTwitterActionController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseHomeController HomeController = new controllers.ReverseHomeController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseTwitterActionController TwitterActionController = new controllers.javascript.ReverseTwitterActionController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseHomeController HomeController = new controllers.javascript.ReverseHomeController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
  }

}
