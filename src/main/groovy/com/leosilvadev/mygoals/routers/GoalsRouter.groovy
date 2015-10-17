package com.leosilvadev.mygoals.routers

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import io.vertx.groovy.core.Vertx
import io.vertx.groovy.ext.web.Router

import com.leosilvadev.mygoals.apis.GoalsApi

class GoalsRouter extends AppRouter {
	
	static GoalsRouter from(Vertx vertx){
		new GoalsRouter(vertx)
	}
	
	private GoalsRouter(Vertx vertx) {
		super(vertx)
		routes = routes + listAll
	}
	
	private def listAll = { Vertx vertx ->
		Router router = Router.router(vertx)
		router.route("/").handler(GoalsApi.listAll)
		router
	}
	
}