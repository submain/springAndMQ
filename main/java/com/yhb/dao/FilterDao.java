package com.yhb.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yhb.service.FilterService;

@Component
public class FilterDao {
	@Autowired
	private FilterService filterService;

	@Override
	public String toString() {
		return "FilterDao [filterService=" + filterService + "]";
	}
	
	
}
