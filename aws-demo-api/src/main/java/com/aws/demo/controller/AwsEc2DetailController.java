package com.aws.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aws.demo.bean.Ec2DetailsVO;
import com.aws.demo.services.Ec2DetailService;

@RestController
@RequestMapping("/static/ec2/")
public class AwsEc2DetailController {
	
	@Autowired 
	private Ec2DetailService ec2DetailService;

	 @ResponseStatus(value = org.springframework.http.HttpStatus.OK)
	 @RequestMapping (value="/ec2instancedetails/{team}/{partner}/{environment}",method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
     public List<Ec2DetailsVO> getDashBoardDropDownData(@PathVariable String team, @PathVariable String partner, @PathVariable String environment){
		
		return ec2DetailService.getEc2InstanceDetails(team,partner,environment);
	}
}
