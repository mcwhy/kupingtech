package com.douwoyixia.dailyaide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;
import android.os.Build;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
	//抽屉布局
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
	//导航视图
    private NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		//判断是否支持透明状态栏 是否到顶部
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
		
        setSupportActionBar(toolbar=(Toolbar)findViewById(R.id.toolbar));
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        navigationView = (NavigationView) findViewById(R.id.navigationView);

		//ActionBarDrawerToggle是一个开关，用来打开关闭DrawerLayout
		//ActionBarDrawerToggle提供了一个方便的方式来配合DrawerLayout和ActionBar
		//以实现推荐的抽屉功能
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        //ActionBarDrawerToggle与DrawerLayout的状态同步
		//并将ActionBarDrawerToggle中的drawer图标，设置为Action的Home-Button的icon
		actionBarDrawerToggle.syncState();
		
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        // navigationView menu点击监听
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
				@Override
				public boolean onNavigationItemSelected(MenuItem menuItem) {
					selectDrawerItem(menuItem);
					return true;
				}
			});

    }


    public void  selectDrawerItem(MenuItem menuItem){
        switch (menuItem.getItemId()) {
			case R.id.navigation_item1:
				Toast.makeText(MainActivity.this, "点击了navigation_item1", Toast.LENGTH_SHORT).show();
				break;
			case R.id.navigation_item2:
				Toast.makeText(MainActivity.this, "点击了navigation_item1", Toast.LENGTH_SHORT).show();
				break;
			default:
				Toast.makeText(MainActivity.this,"点击了其他按钮",Toast.LENGTH_SHORT).show();
				break;
        }
        menuItem.setChecked(true);
        drawerLayout.closeDrawers();

    }
}
