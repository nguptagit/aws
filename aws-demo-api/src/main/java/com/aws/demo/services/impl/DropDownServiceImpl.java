package com.aws.demo.services.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.aws.demo.constant.AwsConstant;
import com.aws.demo.services.DropDownService;

@Configuration
@PropertySource("classpath:config.properties")
public class DropDownServiceImpl implements DropDownService{
	
	@Autowired
	Environment env;
	
	@Resource(name="awsProp")
	private Properties awsRegionProperty;

	@Override
	public JSONObject getDashBoardDropDownData() {
		JSONObject jsonObject= new JSONObject();

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
		jsonObject.put("dropDowndata", dropdownMap);
		
		Map<String,String> awsKeysSet=new HashMap<String,String>();
		if(awsRegionProperty!=null){
		Set<Object> awsKeys=awsRegionProperty.keySet();
		
		for (Object key : awsKeys) {
			awsKeysSet.put((String)key, awsRegionProperty.getProperty((String)key));
		}
			
		}
		
		jsonObject.put("awsRegion", awsKeysSet);
		
		
		
		return jsonObject;
	}

}
