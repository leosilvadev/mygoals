package com.leosilvadev.mygoals.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MongoDate {
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern('dd/MM/yyyy')
	
	static LocalDate parse(String text){
		if(text) LocalDate.parse(text, formatter)
		else null
	}
	
	static String format(LocalDate date){
		if(date) date.format(formatter)
		else null
	}
	
}