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
import com.aws.demo.constant.CharConstant;
import com.aws.demo.services.DropDownService;

@Configuration
@PropertySource("classpath:config.properties")
public class DropDownServiceImpl implements DropDownService{
	
	@Autowired
	Environment env;
	
	@Resource(name="awsProp")
	private Properties awsRegionProperty;

	private static final String DROP_DOWN_DATA = "dropDowndata";
	private static final String AWS_REGION = "awsRegion";
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getDashBoardDropDownData() {
		JSONObject jsonObject= new JSONObject();

		String team = env.getProperty(AwsConstant.TEAM);
		Map<String, Map<String, String[]>> dropdownMap = new HashMap<String,Map<String, String[]>>();
		if (team != null ) {
			String teams [] = team.split(CharConstant.COMMA);
			for (String teamName : teams) {
				String partner = env.getProperty(teamName);
				Map<String, String[]> partnerMap = new HashMap<String, String[]>();
				String partners[] = partner.split(CharConstant.COMMA);
				for (String partnerName : partners ){
					String[] envrionment = env.getProperty(partnerName).split(CharConstant.COMMA);
					partnerMap.put(partnerName, envrionment);
				}
				dropdownMap.put(teamName, partnerMap);
			}
		}
		jsonObject.put(DROP_DOWN_DATA, dropdownMap);
		
		Map<String,String> awsKeysSet=new HashMap<String,String>();
		if(awsRegionProperty!=null){
		Set<Object> awsKeys=awsRegionProperty.keySet();
		for (Object key : awsKeys) {
			awsKeysSet.put((String)key, awsRegionProperty.getProperty((String)key));
		 }
		}	
		jsonObject.put(AWS_REGION, awsKeysSet);	
		return jsonObject;
	}

}
