package com.example.distrsystems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class communication_activity extends Activity {
	static Socket clientSocket ;
	static Thread server ;
	static Thread client ;
	Button sendButton;
	EditText et;
	static TextView chat;
	static String ipaddr;
	ObjectOutputStream out = null ;
	ObjectInputStream in = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		 
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chatlayout);
		chat = (TextView)findViewById(R.id.chatView);
		chat.setText("");
		//server = new Thread(new Server());
		//server.start();
		
		sendButton = (Button) findViewById(R.id.SendButton);
		
		sendButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				et = (EditText) findViewById(R.id.editText1);
				String str = et.getText().toString();
				try {
					client = new Thread(new Client(ipaddr));
					client.start();
					
					out = new ObjectOutputStream(clientSocket.getOutputStream());
					
					out.writeUTF(str);
					out.flush();
					
					Log.e("it did write",""+str);
					updateText("ME:"+str);
					
					
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
				
			}
		});
		
	}
	public static synchronized void updateText(String input)
	{
		chat.setText(chat.getText()+"\n"+input);
	}
	
	public static class Server implements Runnable{
		static ServerSocket serverSocket;
		final int port = 4321;
		Socket clientSocket;
		
		
		ObjectInputStream in ;
		String input;
		
		
		@Override
		public void run() {
			
			try {
				serverSocket = new ServerSocket(port);
				Log.e("part","1");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			while(true){
				
				try {
					Log.e("part","2");
					this.clientSocket = serverSocket.accept();
					Log.e("part","3");
					in = new ObjectInputStream(clientSocket.getInputStream()); 
					Log.e("input is",""+input);
					
					
					input = in.readUTF();
					communication_activity.updateText("OTHER: "+input);
					Log.e("wtf","it reached here ,right ?");
					clientSocket.close();
					Log.e("e","closed socket");
					} catch (Exception e) {
					e.printStackTrace();
				}
				
			 
			}			
		}
		
		
	}
	
	
	public static class Client implements Runnable{
		
		boolean delivered = false ;
		final int serverport=4321;
		static String SERVER_IP ="192.168.1.64";
		OutputStream write;
		String input;
		
		public Client(String ip){
			SERVER_IP = ip;
		}
		@Override
		public void run() {
			try {
				
				InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
				clientSocket = new Socket(serverAddr, serverport);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
}