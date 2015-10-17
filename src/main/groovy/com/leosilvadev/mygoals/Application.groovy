package com.leosilvadev.mygoals

import io.vertx.groovy.core.Vertx
import io.vertx.groovy.core.http.HttpServer
import io.vertx.groovy.ext.web.Router;

import com.leosilvadev.mygoals.apis.GoalsApi
import com.leosilvadev.mygoals.persistence.MongoDB
import com.leosilvadev.mygoals.routers.GoalsRouter;

class Application {

	static main(args){
		Vertx vertx = Vertx.vertx()
		HttpServer server = vertx.createHttpServer()
		
		MongoDB.configure(vertx, [host:"localhost", port: 27017, db_name:'mygoals'])
		
		GoalsRouter.from(vertx).registerOn(server)
		
		server.listen(8080)
	}
}
