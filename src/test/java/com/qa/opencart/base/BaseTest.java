package com.qa.opencart.base;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchPage;

public class BaseTest {
	
	DriverFactory df;
	WebDriver driver;
	protected Properties prop;
	protected LoginPage loginPage;
	protected AccountsPage accountsPage;
	protected SearchPage searchPage;
	protected ProductInfoPage productInfoPage;
	protected RegisterPage registerPage; 
	protected SoftAssert softAssert;
	
	//@Parameters({"url", "browser"})
	@BeforeTest
	public void setUp() {
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
