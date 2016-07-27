package com.aws.demo.bean;

import java.util.List;

import com.amazonaws.services.ec2.model.Tag;

public class Ec2DetailsVO {

	private String privateIP;
	private String instanceId;
	private String state;
	private List<Tag> tags;
	public String getPrivateIP() {
		return privateIP;
	}
	public void setPrivateIP(String privateIP) {
		this.privateIP = privateIP;
	}
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
}
