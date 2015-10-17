package com.leosilvadev.mygoals

import io.vertx.groovy.core.Vertx
import io.vertx.groovy.core.http.HttpServer

import java.text.DateFormat
import java.text.SimpleDateFormat

import com.leosilvadev.mygoals.persistence.MongoDB
import com.leosilvadev.mygoals.routers.GoalsRouter

class Application {

	static main(args){		
		Vertx vertx = Vertx.vertx()
		HttpServer server = vertx.createHttpServer()
		
		MongoDB.configure(vertx, [host:"localhost", port: 27017, db_name:'mygoals'])
		
		server.requestHandler(GoalsRouter.get(vertx).&accept)
		
		server.listen(8080)
	}
}
