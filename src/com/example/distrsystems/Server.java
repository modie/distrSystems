package com.example.distrsystems;

import java.net.ServerSocket;
import java.net.Socket;

public class Server
{

	void openServer()
	{
		ServerSocket providerSocket = null ;
		Socket connection = null ;
		String message = null ;
		
		try
		{
			providerSocket = new ServerSocket(4321);
			
			while(true)
			{
				
			}
		}
		
	}
}
