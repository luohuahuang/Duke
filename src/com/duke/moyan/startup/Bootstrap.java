package com.duke.moyan.startup;

import com.duke.moyan.connector.HttpConnector;

public class Bootstrap {
	public static void main(String[] args){
		HttpConnector httpConnector = new HttpConnector();
		httpConnector.start();
	}
}
