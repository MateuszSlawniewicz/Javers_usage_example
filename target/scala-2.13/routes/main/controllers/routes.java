// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/mateu/IdeaProjects/play-samples-play-java-jpa-example/conf/routes
// @DATE:Thu May 21 14:25:41 CEST 2020

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseGeneralController GeneralController = new controllers.ReverseGeneralController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseGeneralController GeneralController = new controllers.javascript.ReverseGeneralController(RoutesPrefix.byNamePrefix());
  }

}
