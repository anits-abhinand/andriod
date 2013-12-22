package com.example.productinfo;


import java.util.StringTokenizer;




import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


public class MainActivity extends Activity {

//	private TextView textView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ASTask task = new ASTask();
	    task.execute(new String[] { "http://young-thicket-8025.herokuapp.com/" });
	} 
	
	View.OnClickListener getOnClickDoSomething(final Button button)  {
	    return new View.OnClickListener() {
	        public void onClick(View v) {
	        	Button B=(Button)v;
	            Intent intent=new Intent(getBaseContext(),ProductDetails.class);
	          intent.putExtra("source",B.getText());
	            startActivity(intent);
	        }
	    };
	}
	
	public void onClickAdd(View v)
	{
		Intent intent=new Intent(this,Add.class);
		startActivity(intent);
	}
	
	public class ASTask extends AsyncTask<String, Void, String> {
	    @Override
	    protected String doInBackground(String... urls) {
	      
	      return utils.getString(urls);
	    }

	   
	    @Override
	    protected void onPostExecute(String result) {
	    	StringTokenizer st=new StringTokenizer(result," ");
	    	while(st.hasMoreTokens())
	    	{
	    		Button myButton = new Button(getBaseContext());
	    		String product=st.nextToken();
                myButton.setText(product);
               
                myButton.setOnClickListener(getOnClickDoSomething(myButton));
                LinearLayout ll = (LinearLayout)findViewById(R.id.buttonLayout);
                //LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                ll.addView(myButton);
                
	    	}
	    
	  }
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
