/**
 * 
 */
package com.aws.demo.services;

import com.amazonaws.resources.ec2.Instance;
import com.jcraft.jsch.ChannelExec;

/**
 * @author rajeev.jha
 *
 */
public interface Ec2SSHService {
	
	public  ChannelExec getSSHChannel(Instance instance) ;
	public String getSSHChanneltest(Instance instance,String command) ;
	
	public String executeCommand(String command,ChannelExec  channel);

}
