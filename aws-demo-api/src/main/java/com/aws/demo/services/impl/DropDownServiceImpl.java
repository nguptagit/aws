package com.aws.demo.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.aws.demo.services.DropDownService;

@Configuration
@PropertySource("classpath:config.properties")
public class DropDownServiceImpl implements DropDownService{
	
	@Autowired
	Environment env;

	@Override
	public Map<String, Map<String, String[]>> getDashBoardDropDownData() {
		
	//	System.out.println("hiiiiiiiii");
		String team = env.getProperty("Team");
		
		Map<String, Map<String, String[]>> finalMap = new HashMap<String,Map<String, String[]>>();
		
		if (team != null ) {
			System.out.println("inside team : " +team);
			String teams [] = team.split(",");
			
			for (String teamName : teams) {
				String partner = env.getProperty(teamName);
				Map<String, String[]> innerMap = new HashMap<String, String[]>();
				
				String partners[] = partner.split(",");
				
				for (String partnerName : partners ){
					String[] envrionment = env.getProperty(partnerName).split(",");
				            
				
					innerMap.put(partnerName, envrionment);
				}
				finalMap.put(teamName, innerMap);
			}
		}
		
		return finalMap;
	}

}
