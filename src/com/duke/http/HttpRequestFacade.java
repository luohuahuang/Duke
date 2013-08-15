package com.duke.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;

public class HttpRequestFacade implements ServletRequest {

	private ServletRequest request = null;

	public HttpRequestFacade(HttpRequest request) {
		this.setRequest(request);
	}

	private void setRequest(ServletRequest request) {
		this.request = request;
	}

	@Override
	public Object getAttribute(String arg0) {
		return request.getAttribute(arg0);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Enumeration getAttributeNames() {
		return request.getAttributeNames();
	}

	@Override
	public String getCharacterEncoding() {
		return request.getCharacterEncoding();
	}

	@Override
	public int getContentLength() {
		return request.getContentLength();
	}

	@Override
	public String getContentType() {
		return request.getContentType();
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		return request.getInputStream();
	}

	@Override
	public Locale getLocale() {
		return request.getLocale();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Enumeration getLocales() {
		return request.getLocales();
	}

	@Override
	public String getParameter(String arg0) {
		return request.getParameter(arg0);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Enumeration getParameterNames() {
		return request.getParameterNames();
	}

	@Override
	public String[] getParameterValues(String arg0) {
		return request.getParameterValues(arg0);
	}

	@Override
	public String getProtocol() {
		return request.getProtocol();
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return request.getReader();
	}

	@SuppressWarnings("deprecation")
	@Override
	public String getRealPath(String arg0) {
		return request.getRealPath(arg0);
	}

	@Override
	public String getRemoteAddr() {
		return request.getRemoteAddr();
	}

	@Override
	public String getRemoteHost() {
		return request.getRemoteHost();
	}

	@Override
	public RequestDispatcher getRequestDispatcher(String arg0) {
		return request.getRequestDispatcher(arg0);
	}

	@Override
	public String getScheme() {
		return request.getScheme();
	}

	@Override
	public String getServerName() {
		return request.getServerName();
	}

	@Override
	public int getServerPort() {
		return request.getServerPort();
	}

	@Override
	public boolean isSecure() {
		return request.isSecure();
	}

	@Override
	public void removeAttribute(String arg0) {
		request.removeAttribute(arg0);

	}

	@Override
	public void setAttribute(String arg0, Object arg1) {
		request.setAttribute(arg0, arg1);

	}

}
