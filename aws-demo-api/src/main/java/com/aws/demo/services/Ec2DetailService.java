package com.aws.demo.services;

import java.util.List;

import com.amazonaws.services.ec2.model.Instance;
import com.aws.demo.bean.Ec2DetailsVO;

public interface Ec2DetailService {
	public List<Ec2DetailsVO> getEc2InstanceDetails(String team, String partner, String environment);
	
	public com.amazonaws.resources.ec2.Instance getInstanceByInstanceId(String instanceId);
}
