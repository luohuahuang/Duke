package com.duke.moyan.connector;

import java.net.Socket;
import java.net.ServerSocket;
import java.net.InetAddress;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.IOException;

public class HttpServer_obsoleted {
    
/*	public static void main(String[] args) {
		HttpServer server = new HttpServer();
		server.await();
	}*/

	// suppress warning of unclosed ServerSocket
	@SuppressWarnings("resource")
	public void await() {
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
		BufferedReader input = null;
		OutputStream output = null;

		while (true) {
			try {
				socket = serverSocket.accept();
				System.out.println("Yes, accepted");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Accept failed: " + Constants.PORT);
				System.exit(-1);
			}

			try {
				input = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				output = socket.getOutputStream();

				HttpRequest request = new HttpRequest(input);
				request.parse();

				HttpResponse response = new HttpResponse(output);
				response.setRequest(request);

				if (request.getUri().startsWith("/" + Constants.SERVLET_DIR +  "/")) {
					ServletProcessor processor = new ServletProcessor();
					processor.process(request, response);
				} else {
					StaticResourceProcessor processor = new StaticResourceProcessor();
					processor.process(request, response);
				}

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