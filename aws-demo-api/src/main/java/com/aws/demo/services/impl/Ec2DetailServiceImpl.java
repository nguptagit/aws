package com.aws.demo.services.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.aws.demo.bean.Ec2DetailsVO;
import com.aws.demo.constant.AwsConstant;
import com.aws.demo.services.Ec2DetailService;

public class Ec2DetailServiceImpl implements Ec2DetailService {

	
    static AmazonEC2 amazonEC2Client;
    
    @Autowired
	Environment env;
    
	@Override
	public List<Ec2DetailsVO> getEc2InstanceDetails(String team, String partner, String environment) {
		String region = "us-west-2";
		
        List<Ec2DetailsVO> ec2DetailsVOList = new ArrayList<Ec2DetailsVO>();
		 try {
			init(region);
			DescribeInstancesResult result = getDescribeInstancesResult(team,partner,environment);
			List<Reservation> reservations = result.getReservations();

			for (Reservation reservation : reservations) {
			    List<Instance> instances = reservation.getInstances();

			    for (Instance instance : instances) {
			    	   Ec2DetailsVO ec2DetailsVO = new Ec2DetailsVO();
			    	   ec2DetailsVO.setInstanceId(instance.getInstanceId());
			    	   ec2DetailsVO.setPrivateIP(instance.getPrivateIpAddress());
			    	   ec2DetailsVO.setState(instance.getState().getName());
			    	   ec2DetailsVO.setTags(instance.getTags());
			    	   ec2DetailsVOList.add(ec2DetailsVO);
			    }
			}
		} catch (Exception e) {
			
		}	
	  return ec2DetailsVOList;
	}

	private void init(String region) throws Exception {

        AWSCredentials credentials = null;
        try {
            credentials = new BasicAWSCredentials(env.getProperty(AwsConstant.AWS_USER_PRIVATEKEY),env.getProperty(AwsConstant.AWS_USER_SECRETKEY) );
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                    "Please make sure that your credentials file is at the correct " +
                    "location (~/.aws/credentials), and is in valid format.",
                    e);
        }
        amazonEC2Client = new AmazonEC2Client(credentials);
        amazonEC2Client.setEndpoint("ec2."+region+".amazonaws.com");
    }
	
	private static DescribeInstancesResult getDescribeInstancesResult(String team,String partner,String environment) throws Exception {
		
		DescribeInstancesRequest request = new DescribeInstancesRequest();
		List<String> teamvalue = new ArrayList<String>();
		teamvalue.add(team);
		List<String> partnervalue = new ArrayList<String>();
		partnervalue.add(partner);
		List<String> environmentvalue = new ArrayList<String>();
		environmentvalue.add(environment);
		Filter teamFilter = new Filter("tag:Team", teamvalue);
		Filter partnerfilter = new Filter("tag:partner", partnervalue);
		Filter environmentfilter = new Filter("tag:environment", environmentvalue);
		
		return amazonEC2Client.describeInstances(request.withFilters(teamFilter,partnerfilter,environmentfilter));
    }
}
