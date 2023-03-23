package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;

	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");

		//If the testcase in run on 'remote' machine, then set the following properties
		if (Boolean.parseBoolean(prop.getProperty("remote"))) {
			co.setBrowserVersion(prop.getProperty("browserversion"));
			co.setCapability("browsername", "chrome");
			co.setCapability("enableVNC", true);
			co.setCapability("name", prop.getProperty("testcasename"));
		}

		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			System.out.println("Running chrome in headless mode...");
			co.addArguments("--headless");
		}

		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			System.out.println("Running chrome in incognito mode...");
			co.addArguments("--incognito");
		}

		return co;
	}

	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();

		//If the testcase in run on 'remote' machine, then set the following properties
		if (Boolean.parseBoolean(prop.getProperty("remote"))) {
			fo.setBrowserVersion(prop.getProperty("browserversion"));
			fo.setCapability("browsername", "firefox");
			fo.setCapability("enableVNC", true);
			fo.setCapability("name", prop.getProperty("testcasename"));
		}

		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			System.out.println("Running firefox in headless mode...");
			fo.addArguments("--headless");
		}

		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			System.out.println("Running firefox in incognito mode...");
			fo.addArguments("--incognito");
		}

		return fo;
	}

	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			System.out.println("Running edge in headless mode...");
			eo.addArguments("--headless");
		}

		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			System.out.println("Running edge in incognito mode...");
			eo.addArguments("--incognito");
		}

		return eo;
	}

}
