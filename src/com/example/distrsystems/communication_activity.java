package com.example.distrsystems;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class communication_activity extends Activity {
	static Socket clientSocket ;
	static Thread client ;
	static String ipaddr;
	static int port;
	//Graphical Units
	Button sendButton;
	EditText et;
	static TextView chat;
	//Read-Write streams 
	ObjectOutputStream out = null ;
	ObjectInputStream in = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		 
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chatlayout);
		chat = (TextView)findViewById(R.id.chatView);
		chat.setText("");
		chat.setMovementMethod(new ScrollingMovementMethod());
		sendButton = (Button) findViewById(R.id.SendButton);
		sendButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				et = (EditText) findViewById(R.id.editPort);
				String str = et.getText().toString();
				try {
					
					client = new Thread(new Client(ipaddr,port));
					client.start();	
					Thread.sleep(200);
					out = new ObjectOutputStream(clientSocket.getOutputStream());
					out.writeUTF(str);
					out.flush();
					updateText("ME:"+str);
					
					
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
				
			}
		});
		
	}
	
	public synchronized static void updateText(String input)
	{
		chat.setText(chat.getText()+"\n"+input);
	}
	
	
	
	
	
	
}