/**
 * 
 */
package com.aws.demo.services;

import com.amazonaws.services.ec2.model.Instance;
import com.jcraft.jsch.ChannelExec;

/**
 * @author rajeev.jha
 *
 */
public interface Ec2SSHService {
	
	public  ChannelExec getSSHChannel(Instance instance) ;
	
	public String executeCommand(String command,ChannelExec  channel);

}
