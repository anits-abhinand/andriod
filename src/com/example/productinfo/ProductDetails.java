package com.example.productinfo;



//import com.example.productinfo.MainActivity.ASTask;


import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import android.widget.TextView;

public class ProductDetails extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_details);
		Bundle extras = getIntent().getExtras();
	    String pname=extras.getString("source");
		ASTask task = new ASTask();
	    task.execute(new String[] { "http://young-thicket-8025.herokuapp.com//product/"+pname });
	    

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public class ASTask extends AsyncTask<String, Void, String> {
	    @Override
	    protected String doInBackground(String... urls) {
	      
	      return utils.getString(urls);
	    }

	   
	    @Override
	    protected void onPostExecute(String result) 
	    {
	    	try {
				JSONObject j=new JSONObject(result);
				String name=j.getString("name");
				TextView t=(TextView)findViewById(R.id.textView1);
				t.setText(name);
				String desc=j.getString("desc");
				TextView t2=(TextView)findViewById(R.id.textView2);
				t2.setText(desc);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                
	    }
	    
	  }
	}

