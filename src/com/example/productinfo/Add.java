package com.example.productinfo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Add extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
	}
	
	public void back(View v)
	{
		Intent I= new Intent(this,MainActivity.class);
		startActivity(I);
	}
	public class ASTask extends AsyncTask<String, Void, String> {
	    @Override
	    protected String doInBackground(String... urls) {
	      HttpClient client=new DefaultHttpClient();
	      for(int i=0;i<urls.length;i++)
	      {
	      HttpPost hp=new HttpPost(urls[i]);
	      EditText et=(EditText) findViewById(R.id.name);
	      String name=String.valueOf(et.getText());
	      EditText et2=(EditText) findViewById(R.id.description);
	      String desc=String.valueOf(et2.getText());
	      List<NameValuePair> pairs=new ArrayList<NameValuePair>();
	     pairs.add(new BasicNameValuePair("name", name));
	     pairs.add(new BasicNameValuePair("desc", desc));
  	     try {
			hp.setEntity(new UrlEncodedFormEntity(pairs,"UTF-8"));
			client.execute(hp);
			
			return "sucess";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	      }
	     return "failure";
	    }

	   
	    @Override
	    protected void onPostExecute(String result) 
	    {
	    	
                
	    }
	    
	  }
	public void add(View V)
	{
		ASTask task = new ASTask();
	    task.execute(new String[] { "http://young-thicket-8025.herokuapp.com/" });
	    /*EditText et=(EditText) findViewById(R.id.name);
	    EditText et2=(EditText) findViewById(R.id.description);
	    et.setText("");
		et2.setText("");*/
		
	}

	@Override 
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add, menu);
		return true;
	}

}
