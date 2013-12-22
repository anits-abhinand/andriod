package com.example.productinfo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class utils 
{
public static String getString(String ...urls)
{
	String response = "";
    for (String url : urls) {
  	  
      DefaultHttpClient client = new DefaultHttpClient();
      HttpGet httpGet = new HttpGet(url);
      try
      {
        HttpResponse execute = client.execute(httpGet);
        InputStream content = execute.getEntity().getContent();
        
        BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
        String s = "";
        while ((s = buffer.readLine()) != null) 
        {
          response += s;
        }
}
      catch(Exception e)
      {
    	  
      }
}
    return response;
}
}
