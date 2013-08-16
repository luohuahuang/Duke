package com.duke.moyan.connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class HttpAgent implements Runnable{
	
	private Socket socket = null;

	private void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	public HttpAgent(Socket socket) {
		this.setSocket(socket);
	}

	@Override
	public void run() {
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			OutputStream output = socket.getOutputStream();

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
		}
	}

}
