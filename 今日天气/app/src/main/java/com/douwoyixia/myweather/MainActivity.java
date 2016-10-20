package com.douwoyixia.myweather;

import android.app.*;
import android.os.*;
import android.view.View;
import android.widget.Toast;
import android.util.Log;
import android.widget.TextView;
import java.util.HashMap;
import java.net.URLEncoder;
import java.net.URL;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Iterator;

public class MainActivity extends Activity 
{
    public static final String TAG="MainActivity";

    private TextView tv_weather;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tv_weather=(TextView)findViewById(R.id.mainTextView);
        setKeys();
        
    }
    

    public void onClickBtnGetJson(View view) 
    {
        //Toast.makeText(MainActivity.this,"获取天气按钮执行了！",Toast.LENGTH_LONG).show();
        //Log.d(TAG,"点击了获取天气按钮");
        
        //获取天气时先显示一个对话框提示
		configDialog(true);
        
        new Thread(){
            public void run(){
                //要在子线程中做的事。
                String city="北京";
                try
                {
                    String cityName=URLEncoder.encode(city, "UTF-8");
                    //将地址更改正确后，会返回正确值
                    
                    URL url=new URL("http://apis.baidu.com/apistore/weatherservice/cityname?cityname=" + cityName);
                    HttpURLConnection connection=(HttpURLConnection)url.openConnection();
                    
                   
                    复制完你的ID后注释掉此行//
                    connection.addRequestProperty("apikey","请把你自己申请得ID复制过来");

                    InputStream is=connection.getInputStream();
                    int x=0;
                    byte[] bys=new byte[1024];
                    String weatherJson="";
                    while((x=is.read(bys))!=-1){
                        weatherJson+=new String(bys,0,x);
                    }
                    //通过log可以知道已正确返回了
                    Log.e("天气解析返回的Json",weatherJson);
                    configDialog(false);
                    //解析天气Json数据函数
                    
                    //解析天气Json数据函数
					parseWeather(weatherJson);
                    
                    
                    

                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                catch (JSONException e)
				{}
                

            }

            
		}.start();
        
        
       
        
    }

    
    /**
     * 解析接收的天气Json数据
     */
    private void parseWeather(String weatherJson) throws JSONException
    {
        // TODO: Implement this method
        //接收的weatherJson是JsonObject对象
        JSONObject jsonObject=new JSONObject(weatherJson);

        //从jsonObject中获取 errMsg 字段
        String errMsg=jsonObject.getString("errMsg");

        //从 errMsg 字段值 判断获取的数据是否成功
        if("success".equals(errMsg)){
            //如果读取数据成功，则将retData中数据取出 该字段是所需数据
            //截取子JSONObject对象
            JSONObject retData=jsonObject.getJSONObject("retData");
            //retData是键值对数据 获取键值 指示器 迭代器
            Iterator<String> keys=retData.keys();

            //最终解析出来的字符串存储 用于存储最终字符串
            final StringBuilder sBuilder=new StringBuilder();

            while (keys.hasNext()) {
                String next = keys.next();
                //将数据存储到字符串容器中
                sBuilder.append(keysMean.get(next)+" : "+retData.getString(next)+"\n");
            }

            //更改UI线程界面必须在UI线程中操作
            runOnUiThread(new Runnable(){

                    @Override
                    public void run()
                    {
                        // TODO: Implement this method
                        //显示天气
                        tv_weather.setText(sBuilder.toString());
                    }
                });

        }
        //如果返回的 errMsg 中没有成功，则代表失败了，就不解析了
        //给用户一个提示
        else{
            configDialog(false);
        }


	}
    
    //对话框
    private AlertDialog dialog;
	
    /**
     * 显示提示对话框
     *
	 */
    private void configDialog(boolean p0)
    {
        // TODO: Implement this method
        if (p0) {
            if (dialog==null) {
                //对话框构造器
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                //设置标题
                builder.setTitle("网络连接中");
                //设置信息
                builder.setMessage("正在获取数据，请稍候。。。\n如果长时间不能获取，请按返回键取消，检查网络后重试！");
                //设置为不能取消 如果没有网络则进入死循环 BUG!
                //builder.setCancelable(false);
                dialog = builder.create();
            }
            dialog.show();
        }else{
            dialog.dismiss();
		}
       
        
        
    }
    
    //定义一个HashMap 用于存储 JSONObject中的字段对应的中文意思
    HashMap<String, String> keysMean=new HashMap<String, String>();
    
    //设置 keysMean 键值对内容
    private void setKeys(){
        keysMean.put("city", "城市");
        keysMean.put("pinyin", "城市拼音");
        keysMean.put("citycode", "城市编码");
        keysMean.put("date", "日期");
        keysMean.put("time", "发布时间");
        keysMean.put("postCode", "邮编");
        keysMean.put("longitude", "经度");
        keysMean.put("latitude", "维度");
        keysMean.put("altitude", "海拔");
        keysMean.put("weather", "天气情况");
        keysMean.put("temp", "气温");
        keysMean.put("l_tmp", "最低气温");
        keysMean.put("h_tmp", "最高气温");
        keysMean.put("WD", "风向");
        keysMean.put("WS", "风力");
        keysMean.put("sunrise", "日出时间");
        keysMean.put("sunset", "日落时间");
	}
    
    
    
    
    
}
