package com.douwoyixia.kupingbmob;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class PicAndTel extends BmobObject
{
    private String telNum;
    private BmobFile pic1;

    public void setTelNum(String telNum)
    {
        this.telNum = telNum;
    }

    public String getTelNum()
    {
        return telNum;
    }

    public void setPic1(BmobFile pic1)
    {
        this.pic1 = pic1;
    }

    public BmobFile getPic1()
    {
        return pic1;
    }
    
}
