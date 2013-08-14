package com.duke.http;

import java.net.Socket;
import java.net.ServerSocket;
import java.net.InetAddress;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.IOException;
import java.io.File;

public class HttpServer {

	public static final String WEB_ROOT = System.getProperty("user.dir")
			+ File.separator + "webapps";
	public static final String IP_ADDR = "127.0.0.1";
	public static final int PORT = 8080;

	public static void main(String[] args) {
		HttpServer server = new HttpServer();
		server.await();
	}

	// suppress warning of unclosed ServerSocket
	@SuppressWarnings("resource")
	public void await() {
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(PORT, 1,
					InetAddress.getByName("127.0.0.1"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not listen on port: " + PORT);
			System.exit(-1);
		}

		// Loop waiting for a request
		Socket socket = null;
		BufferedReader input = null;
		OutputStream output = null;

		while (true) {
			try {
				socket = serverSocket.accept();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Accept failed: " + PORT);
				System.exit(-1);
			}

			try {
				//input = socket.getInputStream();
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				output = socket.getOutputStream();

				// create Request object and parse
				HttpRequest request = new HttpRequest(input);
				request.parse();

				// create Response object
				HttpResponse response = new HttpResponse(output);
				response.setRequest(request);
				response.sendStaticResource();

				// Close the socket
				output.close();
				
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Close failed");
				continue;
			}

		}
	}
}