package com.leosilvadev.mygoals.apis

import groovy.stream.Stream
import io.vertx.core.Handler
import io.vertx.core.impl.FutureImpl
import io.vertx.core.json.Json
import io.vertx.groovy.ext.mongo.MongoClient
import io.vertx.groovy.ext.web.RoutingContext

import com.leosilvadev.mygoals.domains.Goal
import com.leosilvadev.mygoals.persistence.MongoDB as mongo

class GoalsApi {
	
	private static final COLLECTION_NAME = 'goals'
	
	static Handler listAll = { RoutingContext context ->
		def response = context.response().setChunked(true)
		mongo.execute { MongoClient client ->
			client.find(COLLECTION_NAME, [:], { FutureImpl res ->
				Stream
					.from(res.result())
					.map { Goal.map(it) }
					.each { response.write(Json.encode(it)) }
				response.end()
			})
		}
	} as Handler

	static Handler save = { RoutingContext context ->
		def response = context.response().setChunked(true)
		mongo.execute { MongoClient client ->
			Goal goal = Goal.fromJson(context.getBodyAsJson())
			client.insert(COLLECTION_NAME, goal.map(), { FutureImpl res ->
				if(res.succeeded()){
					response.setStatusCode(201).write(Json.encode([message:'Goal saved successfully'])).end()
					
				} else {
					res.cause().printStackTrace()
					response.setStatusCode(400).write(Json.encode([message:res.cause()])).end()
					
				}
			})
		}
	} as Handler

}
