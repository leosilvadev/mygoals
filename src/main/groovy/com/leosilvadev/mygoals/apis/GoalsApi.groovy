package com.leosilvadev.mygoals.apis

import io.vertx.core.Handler
import io.vertx.core.json.Json
import io.vertx.groovy.core.Future
import io.vertx.groovy.ext.mongo.MongoClient
import io.vertx.groovy.ext.web.RoutingContext

import com.leosilvadev.mygoals.persistence.MongoDB

class GoalsApi {

	static Handler listAll = { RoutingContext context ->
		def response = context.response().setChunked(true)
		MongoDB.execute { MongoClient client ->
			client.find('goals', [:], { res ->
				response.write(Json.encode(res.result())).end()
			})
		}
	} as Handler
}
