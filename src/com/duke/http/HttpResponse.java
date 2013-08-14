package com.duke.http;

import java.io.OutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;

public class HttpResponse {

	private static final int BUFFER_SIZE = 1024;
	public static final String ERROR_404 = "HTTP/1.1 404 File Not Found\r\n"
			+ "Content-Type: text/html\r\n" + "Content-Length: 23\r\n" + "\r\n"
			+ "<h1>File Not Found</h1>";
	HttpRequest request;
	OutputStream output;

	public HttpResponse(OutputStream output) {
		this.output = output;
	}

	public void setRequest(HttpRequest request) {
		this.request = request;
	}

	public void sendStaticResource() throws IOException {
		byte[] bytes = new byte[BUFFER_SIZE];
		FileInputStream fis = null;
		try {
			File file = new File(HttpServer.WEB_ROOT, request.getUri());
			if (file.exists()) {
				fis = new FileInputStream(file);
				int ch = fis.read(bytes, 0, BUFFER_SIZE);
				while (ch != -1) {
					output.write(bytes, 0, ch);
					ch = fis.read(bytes, 0, BUFFER_SIZE);
				}
			} else {
				// file not found
				String errorMessage = ERROR_404;
				output.write(errorMessage.getBytes());
			}
		} catch (Exception e) {
			// thrown if cannot instantiate a File object
			System.out.println(e.toString());
		} finally {
			if (fis != null)
				fis.close();
		}
	}
}