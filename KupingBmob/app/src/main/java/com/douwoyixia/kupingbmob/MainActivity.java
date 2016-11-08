package com.douwoyixia.kupingbmob;

import android.app.*;
import android.os.*;
import android.view.View;
import cn.bmob.v3.datatype.BmobFile;
import java.io.File;
import cn.bmob.v3.listener.UploadFileListener;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import java.io.IOException;
import android.net.Uri;
import android.content.Intent;
import android.provider.MediaStore;

public class MainActivity extends BaseActivity 
{
    PicAndTel picAndTel=new PicAndTel();
    BmobFile bmobPic1;
    

    private Uri imageUri1;//照片1Url
    public static final int TAKE_PHOTO=1;
	public static final int CROP_PHOTO=2;
    
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        showToast("已成功登录！");
    }
    
    public void onClickBtnUploadPic(View view){
        
        String picPath="sdcard/1.jpg";
        String strTelNumber="13800138000";
        
        picAndTel.setTelNum(strTelNumber);
        
        bmobPic1=new BmobFile(new File(picPath));
        
        bmobPic1.uploadblock(new UploadFileListener(){
                @Override
                public void done(BmobException e)
                {
                    // TODO: Implement this method
                    if(e==null){
                        showToast("上传文件成功");
                        picAndTel.setPic1(bmobPic1);
                        
                        picAndTel.save(new SaveListener<String>(){

                                @Override
                                public void done(String p1, BmobException e)
                                {
                                    // TODO: Implement this method
                                    if (e == null)
                                    {
                                        showToast("订单上传成功" + p1);
                                    }
                                    else
                                    {
                                        showToast("订单上传失败，错误类型为" + e.getMessage());
                                    }

                                }
                            });

                    }
                    else{
                        showToast("上传文件错误，错误类型为"+e.getMessage());
                    }
                }
            });
        
    }
    
    
//    public void onClickImageFirst(View view) 
//    {
//        File outputImage=new File(Environment.getExternalStorageDirectory(),"output_image.jpg");
//        try{
//            if(outputImage.exists()){
//                outputImage.delete();
//            }
//            outputImage.createNewFile();
//        }
//        catch(IOException e){
//            e.printStackTrace();
//        }
//        imageUri1=Uri.fromFile(outputImage);
//        Intent intent=new Intent("android.intent.action.GET_CONTENT");
//        intent.setType("image/*");
//        intent.putExtra("crop",true);
//        intent.putExtra("scale",true);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri1);
//        startActivityForResult(intent,CROP_PHOTO);
//
//    }
	
    
    
}
