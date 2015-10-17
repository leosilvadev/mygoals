package com.leosilvadev.mygoals.domains

import java.time.LocalDate

import com.leosilvadev.mygoals.utils.MongoDate



class Goal {
	
	String id
	String title
	String description
	Integer donePercentage
	LocalDate deadline
	
	Map map() {
		[
			'id': id,
			'title': title,
			'description': description,
			'donePercentage': donePercentage,
			'deadline': MongoDate.format(deadline)
		]
	}
	
	static Goal map(Map map) {
		def deadline = MongoDate.parse(map.deadline)
		new Goal(id: map._id, title: map.title, description: map.description, deadline: deadline, donePercentage: map.donePercentage)
	}
	
	static Goal fromJson(Map map) {
		new Goal(id: map.id, title: map.title, description: map.description, deadline: map.deadline, donePercentage: map.done_percentage)
	} 
	
}