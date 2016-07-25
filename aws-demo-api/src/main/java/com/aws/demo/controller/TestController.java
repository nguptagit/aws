package com.aws.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "/dma")
public class TestController {

	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping( method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getAll(){
		List<String> list = new ArrayList<String>();
		list.add("test");
		list.add("test1");
		
		return list;
	}
}
