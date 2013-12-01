package com.example.distrsystems;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.text.format.Formatter;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity
{

	TextView TvIp ;
	String ip ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try
		{
			WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
			ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
		}
		catch(Exception e)
		{
			Log.e("error","could not find ip adress");
		}
		TvIp = (TextView) findViewById(R.id.tvip);
		TvIp.setText(ip);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
