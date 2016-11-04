package com.douwoyixia.kupingbmob;
import android.widget.EditText;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.exception.BmobException;
import android.content.Intent;

public class LoginActivity extends BaseActivity
{
    //调试信息使用
    public static final String TAG="LoginActivity";

    private EditText loginEmailEdit;

    private EditText loginpasswordEdit;

    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO: Implement this method
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);

        //账号 密码 登陆
        loginEmailEdit=(EditText)findViewById(R.id.loginEmail);
        loginpasswordEdit=(EditText)findViewById(R.id.loginPassword);
        loginBtn=(Button)findViewById(R.id.loginBtn);

    }

    //电子邮件登陆按钮
    public void onClickBtnEmailLogin(View view) 
    {
        String strLogEmail    =    loginEmailEdit.getText().toString();
        String strLogPassword =   loginpasswordEdit.getText().toString();

        MyUser myUser=new MyUser();
        myUser.setEmail(strLogEmail);//电子邮件
        myUser.setPassword(strLogPassword);//密码
      
        myUser.loginByAccount(strLogEmail, strLogPassword, new LogInListener<MyUser>(){

                @Override
                public void done(MyUser user, BmobException e)
                {
                    // TODO: Implement this method
                    if(user!=null){
                        showToast("登陆成功");
                        //跳转到主程序界面
                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        showToast("登录失败！错误信息:  "+e.getMessage());
                    }

                }
            });




    }


}
