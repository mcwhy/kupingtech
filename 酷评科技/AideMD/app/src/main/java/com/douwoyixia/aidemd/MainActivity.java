package com.douwoyixia.aidemd;

import android.os.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.support.design.widget.*;
import android.view.*;
import android.widget.EditText;
import android.text.*;
import android.util.*;

public class MainActivity extends AppCompatActivity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

		final TextInputLayout inputLayout = (TextInputLayout) findViewById(R.id.textInput);
		inputLayout.setHint("请输入：");
        final EditText editText = inputLayout.getEditText();
        editText.addTextChangedListener(new TextWatcher() {
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, int after)
				{

				}

				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count)
				{
					Log.e("TAG", s.length() + "");
					if (s.length() > 4)
					{//字符超过5个时，出现EditText提示
						inputLayout.setError("字符不能超过5个");
						inputLayout.setErrorEnabled(true);
					}
					else
					{
						inputLayout.setErrorEnabled(false);
					}
				}

				@Override
				public void afterTextChanged(Editable s)
				{

				}
			});



		//悬浮按钮代码
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view)
				{
					Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
				}
			});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
	{
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
	{
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
		{
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

