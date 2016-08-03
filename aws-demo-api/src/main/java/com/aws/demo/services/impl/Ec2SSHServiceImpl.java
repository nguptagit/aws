/**
 * 
 */
package com.aws.demo.services.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

import com.amazonaws.resources.ec2.Instance;
import com.aws.demo.constant.AwsConstant;
import com.aws.demo.services.Ec2SSHService;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * @author rajeev.jha
 *
 */
@Service
public class Ec2SSHServiceImpl implements Ec2SSHService {

	@Override
	public ChannelExec getSSHChannel(Instance instance) {
		final String cmsPerFile = System.getenv(AwsConstant.AWS_PEM_FILE);

		JSch jsch=new JSch();
		ChannelExec  channel =null;
		try {
			jsch.addIdentity(cmsPerFile+File.separator+instance.getKeyName()+".pem","rajeev");
			jsch.setConfig("StrictHostKeyChecking", "no");
			
			Session session=jsch.getSession(AwsConstant.AWS_DEFAULT_USER, instance.getPublicIpAddress(), 22);
			session.connect();
			
			channel = (ChannelExec) session.openChannel("exec");
			channel.connect();
			
		} catch (JSchException e) {
			e.printStackTrace();
		}
		return channel;
	}
	
	@Override
	public String getSSHChanneltest(Instance instance,String command) {
		final String cmsPerFile = System.getenv(AwsConstant.AWS_PEM_FILE);
		String commandExceutionResult="";
		JSch jsch=new JSch();
		ChannelExec  channel =null;
		try {
			jsch.addIdentity(cmsPerFile+File.separator+instance.getKeyName()+".pem","rajeev");
			jsch.setConfig("StrictHostKeyChecking", "no");
			
			Session session=jsch.getSession(AwsConstant.AWS_DEFAULT_USER, instance.getPublicIpAddress(), 22);
			session.connect();
			
			channel = (ChannelExec) session.openChannel("exec");
			
			
		
			channel.setCommand(command);
			channel.setErrStream(System.err);
			channel.connect();
			
			
			try {
				InputStream input = channel.getInputStream();
				commandExceutionResult=getStringFromInputStream(input);
				
				System.out.println(commandExceutionResult);
			} catch (Exception e) {
				e.printStackTrace();
			}
			channel.disconnect();
	    	session.disconnect();
		} catch (JSchException e) {
			e.printStackTrace();
		}
		return commandExceutionResult;
	}

	@Override
	public String executeCommand(String command,ChannelExec  channel) {

		String commandExceutionResult="";
		channel.setCommand(command);
		channel.setErrStream(System.err);
		try {
			InputStream input = channel.getInputStream();
			commandExceutionResult=getStringFromInputStream(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(commandExceutionResult);
		return commandExceutionResult;
	}

	private  String getStringFromInputStream(InputStream is) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}

}
