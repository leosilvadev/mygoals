package com.leosilvadev.mygoals.persistence

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import io.vertx.groovy.core.Vertx
import io.vertx.groovy.ext.mongo.MongoClient

@CompileStatic
@TypeChecked
class MongoDB {

	private static MongoClient client
	
	static def configure(Vertx vertx, Map config){
		client = MongoClient.createShared(vertx, config)
	}
	
	static def execute(Closure function, Closure onError=null){
		try {
			function(client)
			
		} catch(ex) {
			if(onError) onError(ex)
			
		}
	}

}
