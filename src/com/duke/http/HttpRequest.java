package com.duke.http;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpRequest {

	private BufferedReader input;
	private String uri;

	public HttpRequest(BufferedReader input) {
		this.input = input;
	}

	public void parse() {
		// Read a set of characters from the socket
        // read first line of request (ignore the rest)
        String request = "";
		try {
			request = input.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.print(request);
		uri = parseUri(request);
	}

	private String parseUri(String requestString) {
		int index1, index2;
		index1 = requestString.indexOf(' ');
		if (index1 != -1) {
			index2 = requestString.indexOf(' ', index1 + 1);
			if (index2 > index1)
				return requestString.substring(index1 + 1, index2);
		}
		return null;
	}

	public String getUri() {
		return uri;
	}

}