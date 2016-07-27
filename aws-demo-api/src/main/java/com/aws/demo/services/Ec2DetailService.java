package com.aws.demo.services;

import java.util.List;

import com.aws.demo.bean.Ec2DetailsVO;

public interface Ec2DetailService {
	public List<Ec2DetailsVO> getEc2InstanceDetails(String team, String partner, String environment);
}
