package com.example.simplenotes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	private EditText edit;
	private Button button;
	private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        edit = (EditText) findViewById(R.id.edit);
        
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		ClipboardManager cmb = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        		cmb.setText(edit.getText().toString());
        		Toast.makeText(MainActivity.this, "�Ѹ��Ƶ����а�", Toast.LENGTH_SHORT).show();
        	}
        });
        
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		edit.setText("");
        	}
        });
        
        
        String inputText = load();
        if (!TextUtils.isEmpty(inputText)) {
        	edit.setText(inputText);
        	edit.setSelection(inputText.length());
        }
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	String inputText = edit.getText().toString();
    	save(inputText);
    }
    
    public void save(String inputText) {
    	SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
    	editor.putString("text", inputText);
    	editor.commit();
    }
    
    public String load() {
    	SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
    	String text = pref.getString("text", "");
    	return text;
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    */
}
