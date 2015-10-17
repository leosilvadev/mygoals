package com.leosilvadev.mygoals.routers

import groovy.stream.Stream
import groovy.transform.CompileStatic;
import groovy.transform.TypeChecked;
import io.vertx.groovy.core.Vertx
import io.vertx.groovy.core.http.HttpServer

@CompileStatic
@TypeChecked
class AppRouter {

	protected Vertx vertx
	protected List routes = []

	AppRouter(Vertx vertx){
		this.vertx = vertx
	}

	def registerOn(HttpServer server){
		Stream
			.from(routes)
			.map { Closure configurer ->  configurer(vertx) }
			.each { server.requestHandler(it.&accept) }
	}
}