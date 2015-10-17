package com.leosilvadev.mygoals.persistence

import io.vertx.groovy.core.Vertx
import io.vertx.groovy.ext.mongo.MongoClient

class MongoDB {

	private static MongoClient client
	
	static def configure(Vertx vertx, Map config){
		client = MongoClient.createShared(vertx, config)
	}
	
	static def execute(Closure function, onError=null){
		try {
			function(client)
			
		} catch(ex) {
			if(onError) onError(ex)
			
		}
	}

}
