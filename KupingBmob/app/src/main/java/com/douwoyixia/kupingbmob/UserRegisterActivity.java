package com.douwoyixia.kupingbmob;
import android.widget.EditText;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.app.AlertDialog;
import android.content.DialogInterface;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.exception.BmobException;
import android.content.Intent;

public class UserRegisterActivity extends BaseActivity
{
    //注册用户名
    private EditText regNameEdit;
    //注册QQ号
    private EditText regQQEdit;
    //注册密码
    private EditText regPasswordEdit;
    //注册密码复核
    private EditText regPasswordCheckEdit;
    //电子邮箱地址
    private EditText regEmailEdit;
    //电话号码
    private EditText regTelNumberEdit;
    //电子邮箱注册按钮
    private Button emailRegisterBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO: Implement this method
        super.onCreate(savedInstanceState);
        //显示注册页面
        setContentView(R.layout.user_register);

        regNameEdit=(EditText)findViewById(R.id.regName);//用户名
        regQQEdit=(EditText)findViewById(R.id.regQQ);//注册QQ号
        regEmailEdit=(EditText)findViewById(R.id.regEmail);//电子邮箱
        regTelNumberEdit=(EditText)findViewById(R.id.regTelNumber);//注册电话号码
        regPasswordEdit=(EditText)findViewById(R.id.regPassword);//注册密码框
        regPasswordCheckEdit=(EditText)findViewById(R.id.regPasswordCheck);//复核密码框
        emailRegisterBtn=(Button)findViewById(R.id.emailRegisterBtn);//电子邮件注册按钮

    }

    //点击电子邮件注册
    public void onClickBtnEmailRegister(View view) 
    {
        //将用户填写的信息读取
        String strRegQQ          =    regQQEdit.getText().toString();
        String strRegName          =    regNameEdit.getText().toString();
        String strRegEmail         =   regEmailEdit.getText().toString();
        String strRegTelNumber     =   regTelNumberEdit.getText().toString();
        String strRegPassword      =regPasswordEdit.getText().toString();
        String strRegPasswordCheck =regPasswordCheckEdit.getText().toString();

        //判断两次密码是否相同，不相同则提示
        if(!strRegPassword.equals(strRegPasswordCheck)){
            showToast("前后两次密码输入不一致，请重新输入！");

            //对话框，强提示
            AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(UserRegisterActivity.this);
            dialogBuilder.setTitle("提示");
            dialogBuilder.setMessage("两次密码输入不一致！");
            dialogBuilder.setCancelable(false);
            dialogBuilder.setPositiveButton("确认",
                new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        // TODO: Implement this method

                    }
                });

        }
        //判断密码长度是否相同
        else if(strRegPassword.length()<6&&strRegPassword.length()>16){
            showToast("密码长度不符合要求!");
        }
        //  else if(手机号格式不正确){}
        //  else if(电子邮箱格式不正确){}

        //没有错误则执行注册
        else{
            //创建BmobUser对象
            MyUser myUser=new MyUser();
            
            //设置对应的值
            myUser.setUsername(strRegName);//用户名
            myUser.setQqNumber(strRegQQ);//QQ号
            myUser.setEmail(strRegEmail);//电子邮件
            myUser.setMobilePhoneNumber(strRegTelNumber);//电话号码
            myUser.setPassword(strRegPassword);//密码

            //提交用户注册信息
            myUser.signUp(new SaveListener<MyUser>(){
                    @Override
                    public void done(MyUser p1, BmobException e)
                    {
                        // TODO: Implement this method
                        if(e==null){
                            //弹出提示消息
                            showToast("恭喜您使用邮箱注册成功，请登陆邮箱进行激活！");
                            //跳转到APP内部
                            Intent intent=new Intent(UserRegisterActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            showToast("注册失败！错误信息:  "+e.getMessage());}
                    }
                });
        }

    }

}
