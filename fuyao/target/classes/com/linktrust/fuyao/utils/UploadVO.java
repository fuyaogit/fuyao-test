package com.linktrust.fuyao.utils;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class UploadVO { 
	
	 

	@ApiModelProperty(value = "文件路径", required = false)
    private String resUrl;

	@ApiModelProperty(value = "文件大小", required = false)
    private String resSize;

	public String getResUrl() {
		return resUrl;
	}

	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}

	public String getResSize() {
		return resSize;
	}

	public void setResSize(String resSize) {
		this.resSize = resSize;
	}
   
}