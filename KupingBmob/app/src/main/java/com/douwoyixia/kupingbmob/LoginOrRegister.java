package com.douwoyixia.kupingbmob;
import android.app.Activity;
import android.os.Bundle;
import cn.bmob.v3.BmobUser;
import android.content.Intent;
import android.view.View;

public class LoginOrRegister extends BaseActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO: Implement this method
        super.onCreate(savedInstanceState);

        //获取当前用户，判断是否已登陆过
        BmobUser bmobUser=BmobUser.getCurrentUser();
        
        if(bmobUser!=null){
            //如果存在登陆信息，直接登陆到主界面
            Intent intent=new Intent(LoginOrRegister.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            //如果没有登陆信息，说明需要注册或者登陆，跳转到登陆和注册界面
            setContentView(R.layout.login_or_register);

        }

    }

    //登陆按钮
    public void onClickBtnLogin(View view) 
    {
        Intent intent=new Intent(LoginOrRegister.this,LoginActivity.class);
        startActivity(intent);
        finish();

    }

    public void onClickBtnUserRegister(View view) 
    {
        Intent intent=new Intent(LoginOrRegister.this,UserRegisterActivity.class);
        startActivity(intent);
        finish();
    }
    
    
}
