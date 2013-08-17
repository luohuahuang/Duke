package com.duke.moyan.connector;

import java.io.IOException;

import com.duke.moyan.request.HttpRequest;
import com.duke.moyan.response.HttpResponse;

public class StaticResourceProcessor {
	public void process(HttpRequest request, HttpResponse response) {
		try {
			response.sendStaticResource();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
