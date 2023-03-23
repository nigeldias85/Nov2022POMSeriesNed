package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.opencart.exception.FrameworkException;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;

	public static String highlight;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	// Initialize the driver
	/**
	 * This method initializes the driver based on the browser name and returns the
	 * driver instance
	 * 
	 * @param browserName
	 * @return
	 * @throws MalformedURLException
	 */
	public WebDriver initDriver(Properties prop) {
		optionsManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight").trim();
		String browserName = prop.getProperty("browser").toLowerCase().trim();
		System.out.println("The Browser is: " + browserName);
		// String browserName = System.getProperty("browser");

		// Call the driver
		//For Chrome
		if (browserName.equalsIgnoreCase("chrome")) {
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// Run on Remote/Grid
				init_remoteDriver("chrome");
			} else {
				// Run on local machine
				// driver = new ChromeDriver(optionsManager.getChromeOptions());
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
		} 
		
		//For Firefox
		else if (browserName.equalsIgnoreCase("firefox")) {
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// Run on Remote/Grid
				init_remoteDriver("firefox");
			} else {
				// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			}
		} 
		
		//For Edge
		else if (browserName.equalsIgnoreCase("edge")) {
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// Run on Remote/Grid
				init_remoteDriver("edge");
			} else {
				// driver = new EdgeDriver(optionsManager.getEdgeOptions());
				tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			}
		} 
		
		else {
			System.out.println("Please pass the right browser: " + browserName);
			throw new FrameworkException("NO VALID BROWSER FOUND...........");
		}

		// Modify the browser
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}

	/**
	 * This method called internally to initialize the driver with RemoteWebDriver
	 * 
	 * @param browser
	 */
	private void init_remoteDriver(String browser) {
		// TODO Auto-generated method stub
		
		System.out.println("Running test on GRID server: "+browser);

		try {
			switch (browser.toLowerCase()) {
			case "chrome":
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));
				break;

			case "firefox":
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFirefoxOptions()));
				break;

			case "edge":
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOptions()));
				break;

			default:
				System.out.println("Please pass the right browser for remote execution: " + browser);
				throw new FrameworkException("NO REMOTE BROWSER EXCEPTION");
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Get a local thread copy of the driver
	public synchronized static WebDriver getDriver() {
		return tlDriver.get();
	}

	// Initialize the properties
	/**
	 * This method is reading the properties from the config.properties file
	 * 
	 * @return
	 */
	public Properties initProp() {

		prop = new Properties();
		FileInputStream fip = null;
		// mvn command line arguments
		// mvn clean install -Denv="qa"
		String envName = System.getProperty("env");
		System.out.println("Running test case on Env: " + envName);

		try {
			if (envName == null) {
				System.out.println("No environment is defined. Running tests on QA environment");
				fip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			} else {
				// Define the multi-env logic
				switch (envName.toLowerCase().trim()) {
				case "qa":
					fip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;

				case "stage":
					fip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;

				case "dev":
					fip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;

				case "prod":
					fip = new FileInputStream("./src/test/resources/config/config.properties");
					break;

				default:
					System.out.println("Wrong env is defined. Do not run test cases.");
					throw new FrameworkException("WRONG ENVIRONMENT IS GIVEN........");
				}

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Load the fileinputstream into the properties reference - prop
		try {
			prop.load(fip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * Takes the screenshot
	 * 
	 * @return
	 */
	public static String getScreenshot() {

		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		// System.getProperty("user.dir") -> This returns the current project directory
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtil.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
