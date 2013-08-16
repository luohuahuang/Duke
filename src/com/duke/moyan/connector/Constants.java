package com.duke.moyan.connector;

import java.io.File;

public class Constants {
	public static final String Package = "com.duke.moyan.connector";
	public static final int MAJOR_VERSION = 1;
	public static final int MINOR_VERSION = 0;

	public static final String WEB_ROOT = System.getProperty("user.dir")
			+ File.separator + "webapps";
	public static final String IP_ADDR = "127.0.0.1";
	public static final int PORT = 8080;
	public static final String ERROR_404 = "HTTP/1.1 404 File Not Found\r\n"
			+ "Content-Type: text/html\r\n" + "Content-Length: 23\r\n" + "\r\n"
			+ "<h1>File Not Found</h1>";
	public static final int BUFFER_SIZE = 4096;
	public static final String SERVLET_DIR = "servlet";	
}
