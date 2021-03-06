package com.duke.moyan.connector;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;
import java.io.File;
import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.duke.moyan.request.HttpRequest;
import com.duke.moyan.request.HttpRequestFacade;
import com.duke.moyan.response.HttpResponse;
import com.duke.moyan.response.HttpResponseFacade;

public class ServletProcessor {

	public void process(HttpRequest request, HttpResponse response) {

		String uri = request.getRequestURI();
		String servletName = uri.substring(uri.lastIndexOf("/") + 1);
		URLClassLoader loader = null;

		try {
			// create a URLClassLoader
			URL[] urls = new URL[1];
			URLStreamHandler streamHandler = null;
			File classPath = new File(Constants.WEB_ROOT);
			// the forming of repository is taken from the createClassLoader
			// method in
			// org.apache.catalina.startup.ClassLoaderFactory
			String repository = (new URL("file", null,
					classPath.getCanonicalPath() + File.separator)).toString() + Constants.SERVLET_DIR + File.separator;
			// the code for forming the URL is taken from the addRepository
			// method in
			// org.apache.catalina.loader.StandardClassLoader class.
			urls[0] = new URL(null, repository, streamHandler);
			loader = new URLClassLoader(urls);
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		@SuppressWarnings("rawtypes")
		Class myClass = null;
		try {
			myClass = loader.loadClass(servletName);
		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
		}

		Servlet servlet = null;
	    HttpRequestFacade requestFacade = new HttpRequestFacade(request);
	    HttpResponseFacade responseFacade = new HttpResponseFacade(response);
		try {
			servlet = (Servlet) myClass.newInstance();
			servlet.service((ServletRequest) requestFacade,
					(ServletResponse) responseFacade);
			response.finishResponse();
		} catch (Exception e) {
			System.out.println(e.toString());
		} catch (Throwable e) {
			System.out.println(e.toString());
		}

	}
}