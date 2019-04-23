package com.gp.usernote.model;

import java.util.Date;

public interface ISystemDate {
	
	Date getCreateTime();
	void setCreateTime(Date createTime);
	
	Date getUpdateTime();
	void setUpdateTime(Date updateTime);
	
}
