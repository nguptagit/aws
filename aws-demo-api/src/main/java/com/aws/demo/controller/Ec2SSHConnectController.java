/**
 * 
 */
package com.aws.demo.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.resources.ec2.Instance;
import com.aws.demo.bean.Ec2DetailsVO;
import com.aws.demo.services.Ec2DetailService;
import com.aws.demo.services.Ec2SSHService;
import com.google.gson.JsonObject;
import com.jcraft.jsch.ChannelExec;

/**
 * @author rajeev.jha
 *
 */

@RestController
@RequestMapping("/static/ec2/")
public class Ec2SSHConnectController {


	@Autowired 
	private Ec2DetailService ec2DetailService;

	
	@Autowired
	private Ec2SSHService ec2sshService;

	
	@ResponseStatus(value = org.springframework.http.HttpStatus.OK)
	@RequestMapping (value="/sshConnect",method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
	public JSONObject getSSHConnect(@RequestParam(value="id") String id,@RequestParam(value="command",required=false) String command ){
		String commandLinux="";
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject= new JSONObject();
		
		try {
		 jsonObject=	(JSONObject) jsonParser.parse(command);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	 
		
		commandLinux=(String) jsonObject.get("linuxCommand");
		
		/*try {
			commandLinux=URLDecoder.decode(command, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		Instance instance=  ec2DetailService.getInstanceByInstanceId(id);
		//ChannelExec channelExec= ec2sshService.getSSHChanneltest(instance, command);
		String data=ec2sshService.getSSHChanneltest(instance, commandLinux);
		
		jsonObject.put("data", data);
		
		return jsonObject;
	}



}
