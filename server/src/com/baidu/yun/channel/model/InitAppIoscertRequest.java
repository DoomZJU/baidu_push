package com.baidu.yun.channel.model;

import com.baidu.yun.channel.constants.BaiduChannelConstants;
import com.baidu.yun.core.annotation.HttpParamKeyName;
import com.baidu.yun.core.annotation.R;

public class InitAppIoscertRequest extends ChannelRequest {

	@HttpParamKeyName(name=BaiduChannelConstants.NAME, param=R.REQUIRE)
	private String name;

	@HttpParamKeyName(name=BaiduChannelConstants.DESCRIPTION, param=R.REQUIRE)
	private String description;

	@HttpParamKeyName(name=BaiduChannelConstants.RELEASE_CERT, param=R.REQUIRE)
	private String releaseCert;

	@HttpParamKeyName(name=BaiduChannelConstants.DEV_CERT, param=R.REQUIRE)
	private String devCert;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReleaseCert() {
		return releaseCert;
	}

	public void setReleaseCert(String releaseCert) {
		this.releaseCert = releaseCert;
	}

	public String getDevCert() {
		return devCert;
	}

	public void setDevCert(String devCert) {
		this.devCert = devCert;
	}
	
}
