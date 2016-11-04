package com.douwoyixia.kupingbmob;

import android.app.*;
import android.os.*;

public class MainActivity extends BaseActivity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        showToast("已成功登录！");
    }
}
