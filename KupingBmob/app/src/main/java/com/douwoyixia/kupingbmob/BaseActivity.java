package com.douwoyixia.kupingbmob;
import android.app.Activity;
import android.os.Bundle;
import cn.bmob.v3.Bmob;
import android.widget.Toast;

public class BaseActivity extends Activity
{
    public static String TAG="BaseActivity";
    private String Bmon_AppId="换成你自己的Id";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO: Implement this method
        super.onCreate(savedInstanceState);
        Bmob.initialize(this,Bmon_AppId);
        showToast("初始化完成！");
    }

    //公有可以继承
    public void showToast(String string)
    {
        // TODO: Implement this method
        Toast.makeText(getApplicationContext(),string,Toast.LENGTH_SHORT).show();
    }



}
