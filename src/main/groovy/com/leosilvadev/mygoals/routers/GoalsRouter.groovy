package com.leosilvadev.mygoals.routers

import static io.vertx.core.http.HttpMethod.*
import io.vertx.groovy.core.Vertx
import io.vertx.groovy.ext.web.Router
import io.vertx.groovy.ext.web.handler.BodyHandler

import com.leosilvadev.mygoals.apis.GoalsApi

class GoalsRouter {
	
	static Router get(Vertx vertx) {
		Router router = Router.router(vertx)
		router.route().handler(BodyHandler.create())
		router.get('/goals').handler(GoalsApi.listAll)
		router.post('/goals').consumes('application/json').handler(GoalsApi.save)
		router
	}
	
}