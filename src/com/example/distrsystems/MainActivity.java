package com.example.distrsystems;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

	TextView phoneIp ;
	String ip;
	EditText portText;
	EditText destIPText;
	static Thread server ;
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
		phoneIp = (TextView) findViewById(R.id.tvip);
		phoneIp.setText(ip);
		destIPText = (EditText)findViewById(R.id.editIPAddr);
		portText = (EditText) findViewById(R.id.editPort);
		server = new Thread(new Server());
		server.start();
		Button button = (Button)findViewById(R.id.connectButton);
		button.setOnClickListener(new OnClickListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				
				String destinationIP;
				int port;
				Intent comm= new Intent(v.getContext(),communication_activity.class);
				/////set addr and port that we are going to connect
				try{
				destinationIP = destIPText.getText().toString();
				port = Integer.parseInt(portText.getText().toString());
				communication_activity.ipaddr= destinationIP;
				communication_activity.port=port;
				//start communication activity
				startActivity(comm);
				}catch(Exception e){
					AlertDialog alertDialog = new AlertDialog.Builder(v.getContext()).create();
					alertDialog.setTitle("Error");
					alertDialog.setMessage("IP and port must be filled with proper info");
					alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
					   public void onClick(DialogInterface dialog, int which) {
					      // TODO Add your code for the button here.
					   }
					});
					// Set the Icon for the Dialog
					alertDialog.show();
				}
				
				
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
