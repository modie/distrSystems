package com.example.distrsystems;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class communication_activity extends Activity {
	
	Thread server ;
	Thread client ;
	Button sendButton;
	EditText et;
	static TextView chat;
	static String ipaddr;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		 
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chatlayout);
		
		server = new Thread(new Server());
		server.start();
		client = new Thread(new Client(ipaddr));
		client.start();
		chat = (TextView)findViewById(R.id.chatView);
		
		sendButton = (Button) findViewById(R.id.SendButton);
		sendButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				et = (EditText) findViewById(R.id.editText1);
				String str = et.getText().toString();
				try {
					PrintWriter out = new PrintWriter(new BufferedWriter(
							new OutputStreamWriter(Client.clientSocket.getOutputStream())),
							true);
					out.println(str);
					chat.setText(chat.getText()+"\n"+"ME:"+str);
					
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
			}
		});
		
	}
	
	public static class Server implements Runnable{
		
		static ServerSocket serverSocket;
		final int port = 5000;
		Socket clientSocket;
		BufferedReader reader;
		String input;
		
		
		@Override
		public void run() {
			
			try {
				serverSocket = new ServerSocket(port);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			while(true){
				
				try {
					clientSocket = serverSocket.accept();
					reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
					input = reader.readLine();
					communication_activity.chat.setText(communication_activity.chat.getText()+"\n"+"OTHER:"+input);
					clientSocket.close();
					} catch (IOException e) {
					e.printStackTrace();
				}
				
			 
			}			
		}
		
		
	}
	public static class Client implements Runnable{
		
		static  Socket clientSocket;
		final int serverport=5000;
		static String SERVER_IP ="10.0.2.15";
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		public Socket getClientSocket(){
			
			return clientSocket;
		}
	}
	
}