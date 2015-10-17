package com.leosilvadev.mygoals.domains

import java.text.DateFormat
import java.text.SimpleDateFormat



class Goal {
	
	final static DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
	
	String id
	String title
	String description
	Integer donePercentage
	Date deadline
	
	Map map() {
		[
			'title': title,
			'description': description,
			'donePercentage': donePercentage,
			'deadline': format.format(new Date())
		]
	}
	
	static Goal map(Map map) {
		def deadline = format.parse(map.deadline)
		new Goal(id: map._id, title: map.title, description: map.description, deadline: deadline, donePercentage: map.donePercentage)
	}
	
	static Goal fromJson(Map map) {
		new Goal(id: map.id, title: map.title, description: map.description, deadline: map.deadline, donePercentage: map.done_percentage)
	}
	
}