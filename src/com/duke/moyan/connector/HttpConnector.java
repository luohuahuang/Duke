package com.duke.moyan.connector;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpConnector implements Runnable{

	@SuppressWarnings("resource")
	@Override
	public void run() {
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(Constants.PORT, 1,
					InetAddress.getByName(Constants.IP_ADDR));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not listen on port: " + Constants.PORT);
			System.exit(-1);
		}

		Socket socket = null;

		while (true) {
			try {
				socket = serverSocket.accept();
				System.out.println("Yes, accepted");
				//Thread.sleep(10000);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Accept failed: " + Constants.PORT);
				System.exit(-1);
			}

			Runnable httpthread;
			httpthread = new HttpAgent(socket);
			Thread thread = new Thread(httpthread);
			thread.start();
		}
	}
	
	public void start(){
		Thread thread = new Thread(this);
		thread.start();
	}

}
