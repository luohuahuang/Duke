package com.duke.moyan.util;

import java.util.Hashtable;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class StringManager {
	private ResourceBundle bundle;

	private StringManager(String packageName) {
		String bundleName = packageName + ".LocalStrings";
		try {
			bundle = ResourceBundle.getBundle(bundleName);
			return;
		} catch (MissingResourceException e) {
			System.out.println("Failed to load " + bundleName);
		}
	}

	public String getString(String key) {
		String str = null;
		if (bundle == null){
			return key;
		}
		try {
			str = bundle.getString(key);
		} catch (MissingResourceException mre) {
			System.out.println("Cannot find message associated with key '" + key + "'");
		}

		return str;
	}

	@SuppressWarnings("rawtypes")
	private static Hashtable managers = new Hashtable();

	@SuppressWarnings("unchecked")
	public synchronized static StringManager getManager(String packageName) {
		StringManager mgr = (StringManager) managers.get(packageName);

		if (mgr == null) {
			mgr = new StringManager(packageName);
			managers.put(packageName, mgr);
		}
		return mgr;
	}
}
