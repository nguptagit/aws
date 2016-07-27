package com.aws.demo.controller;


import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aws.demo.services.DropDownService;

@RestController
@RequestMapping("/static/ec2/")
public class DropDownController {
	
	
	@Autowired 
	private DropDownService dropDownService;

	@ResponseStatus(value = org.springframework.http.HttpStatus.OK)
	@RequestMapping (value="/dropdowndata",method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
     public JSONObject  getDashBoardDropDownData(){
		
		return dropDownService.getDashBoardDropDownData();
	}
}
