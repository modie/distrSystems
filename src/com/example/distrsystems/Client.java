package com.example.distrsystems;

import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client implements Runnable{
	
	boolean delivered = false ;
	int serverport=4321;
	static String SERVER_IP ;
	OutputStream write;
	String input;
	
	public Client(String ip,int port){
		SERVER_IP = ip;
		serverport=port;
	}
	@Override
	public void run() {
		try {
			
			InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
			communication_activity.clientSocket = new Socket(serverAddr, serverport);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}