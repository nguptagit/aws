/**
 * 
 */
package com.aws.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.resources.ec2.Instance;
import com.aws.demo.bean.Ec2DetailsVO;
import com.aws.demo.services.Ec2DetailService;
import com.aws.demo.services.Ec2SSHService;
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
	@RequestMapping (value="/sshConnect/{id}/{command}",method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public String getDashBoardDropDownData(@PathVariable String id,@PathVariable String command ){
		
		Instance instance=  ec2DetailService.getInstanceByInstanceId(id);
		//ChannelExec channelExec= ec2sshService.getSSHChanneltest(instance, command);
		return ec2sshService.getSSHChanneltest(instance, command);
	}



}
