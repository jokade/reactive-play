package dashboard

import biz.enef.angulate.ext.{Route, RouteProvider}

import biz.enef.angulate._
import scala.scalajs.js.JSApp

object DashboardApp extends JSApp {

  def main(): Unit = {
    val module = angular.createModule("twitter", Seq("ngRoute"))

    module.controllerOf[DashboardCtrl]

    module.config { ($routeProvider: RouteProvider) =>
      $routeProvider
        .when("/dashboard", Route(templateUrl = "/dashboard.html", controller = "dashboard.DashboardCtrl"))
        .otherwise(Route(redirectTo = "/dashboard"))
    }

  }
}
