package com.douwoyixia.kupingbmob;
import cn.bmob.v3.BmobUser;

public class MyUser extends BmobUser
{
	//QQ号
	private String qqNumber;

	public void setQqNumber(String qqNumber)
	{
		this.qqNumber = qqNumber;
	}

	public String getQqNumber()
	{
		return qqNumber;
	}

}
