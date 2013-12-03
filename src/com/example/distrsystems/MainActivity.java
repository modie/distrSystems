package com.example.distrsystems;

import android.app.Activity;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity
{

	TextView TvIp ;
	String ip;
	String DestinationIP;
	EditText destIPText;
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
		destIPText = (EditText)findViewById(R.id.ipaddr);
		
		Button button = (Button)findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent comm= new Intent(v.getContext(),communication_activity.class);
				DestinationIP = destIPText.getText().toString();
				communication_activity.ipaddr= DestinationIP;
				
				startActivity(comm);
				
				
			}
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
