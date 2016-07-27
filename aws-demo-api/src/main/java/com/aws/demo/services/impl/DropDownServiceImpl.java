package com.aws.demo.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.aws.demo.constant.AwsConstant;
import com.aws.demo.services.DropDownService;


public class DropDownServiceImpl implements DropDownService{
	
	@Autowired
	Environment env;

	@Override
	public Map<String, Map<String, String[]>> getDashBoardDropDownData() {

		String team = env.getProperty(AwsConstant.TEAM);
		Map<String, Map<String, String[]>> dropdownMap = new HashMap<String,Map<String, String[]>>();
		if (team != null ) {
			String teams [] = team.split(",");
			for (String teamName : teams) {
				String partner = env.getProperty(teamName);
				Map<String, String[]> partnerMap = new HashMap<String, String[]>();
				String partners[] = partner.split(",");
				for (String partnerName : partners ){
					String[] envrionment = env.getProperty(partnerName).split(",");
					partnerMap.put(partnerName, envrionment);
				}
				dropdownMap.put(teamName, partnerMap);
			}
		}
		return dropdownMap;
	}

}
