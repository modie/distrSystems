package com.example.distrsystems;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public  class Server implements Runnable{
	
	static ServerSocket serverSocket;
	final int port = 4321;
	Socket clientSocket;
	ObjectInputStream in ;
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
				this.clientSocket = serverSocket.accept();
				in = new ObjectInputStream(clientSocket.getInputStream()); 
				input = in.readUTF();
				communication_activity.updateText("OTHER: "+input);
				clientSocket.close();
				} catch (Exception e) {
				e.printStackTrace();
			}
			
		 
		}			
	}
	
	
	
}