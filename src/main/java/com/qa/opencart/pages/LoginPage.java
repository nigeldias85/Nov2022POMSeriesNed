package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1.Private By Locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By forgotPassword = By.linkText("Forgotten Password");
	private By nalLogo = By.xpath("//div[@id='logo']//img");
	private By menuOptionLinks = By.xpath("//div[@class='list-group']/a");
	private By footerSectionHeader = By.xpath("//footer//div[@class='row']//h5");
	private String footerSectionChildLinkPartialXpath1 = "//footer//div[@class='row']//h5[contains(text(), '";
	private String footerSectionChildLinkPartialXpath2 = "')]/parent::div//li";
	private By registerLink = By.linkText("Register");
	
	//2. Page Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	
	//3. Page Actions/Method
	@Step("***** Getting login page title *****")
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleContainsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE_VALUE);
		System.out.println("Page Title: "+title);
		return title;
	}
	
	
	@Step("***** Getting login page URL *****")
	public String getLoginPageUrl() {
		String url = eleUtil.waitForUrlContainsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE);
		System.out.println("Page Url: "+url);
		return url;
	}
	
	
	@Step("***** Checking forgot password link *****")
	public boolean isForgotPasswordLinkExists() {
		return eleUtil.waitForElementVisibility(forgotPassword, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	
	
	@Step("***** Login with username: {0} and password: {1} *****")
	public AccountsPage doLogin(String un, String pwd) {
		System.out.println("Application Credentials are: "+un+" and "+pwd);
		eleUtil.waitForElementVisibility(emailId, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginButton);
		return new AccountsPage(driver);
	}
	
	@Step("***** Navigate to Register Page *****")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
	
	
	@Step("***** Check for the display of NAL logo *****")
	public boolean isNalLogoDisplayed() {
		return eleUtil.waitForElementVisibility(nalLogo, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	
	
	@Step("***** Check for the NAL logo source *****")
	public String getLogoSource() {
		return eleUtil.waitForElementVisibility(nalLogo, AppConstants.DEFAULT_MEDIUM_TIME_OUT).getAttribute("src").toString();
	}
	
	
	@Step("***** Get NAL logo title *****")
	public String getLogoTitle() {
		return eleUtil.waitForElementVisibility(nalLogo, AppConstants.DEFAULT_MEDIUM_TIME_OUT).getAttribute("title").toString();
	}
	
	@Step("***** Get the menu options link text *****")
	public List<String> getMenuOptionLinksText() {
		List<WebElement> menuOptionsLinksList = eleUtil.waitForElementsVisibility(menuOptionLinks, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		List<String> menuOptionsLinksTextList = new ArrayList<>();
		for(WebElement ele: menuOptionsLinksList) {
			String textValue = ele.getText();
			if(textValue != null || !textValue.isBlank() || !textValue.isEmpty()) {
				menuOptionsLinksTextList.add(textValue);
			}
		}
		return menuOptionsLinksTextList;
	}
	
	@Step("***** Get the footer section headers text *****")
	public List<String> getFooterSectionHeadersText() {
		List<WebElement> footerSectionHeaderList = eleUtil.waitForElementsVisibility(footerSectionHeader, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		List<String> footerSectionHeaderTextList = new ArrayList<>();
		for(WebElement ele: footerSectionHeaderList) {
			String textValue = ele.getText().trim();
			if(textValue != null || !textValue.isBlank() || !textValue.isEmpty()) {
				footerSectionHeaderTextList.add(textValue);
			}
		}
		return footerSectionHeaderTextList;
	}
	
	
	@Step("***** Get the footer section header -> child links text *****")
	public List<String> getFooterSectionChildLinksText(String headerName) {
		String footerSectionChildLinkXpath = footerSectionChildLinkPartialXpath1 + headerName + footerSectionChildLinkPartialXpath2;
		By footerSectionChildLink = By.xpath(footerSectionChildLinkXpath);
		List<WebElement> footerSectionChildLinksList = eleUtil.waitForElementsVisibility(footerSectionChildLink, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		List<String> footerSectionChildLinksTextList = new ArrayList<>();
		for(WebElement ele: footerSectionChildLinksList) {
			String textValue = ele.getText().trim();
			if(textValue != null || !textValue.isBlank() || !textValue.isEmpty()) {
				footerSectionChildLinksTextList.add(textValue);
			}
		}
		return footerSectionChildLinksTextList;
	}
}
