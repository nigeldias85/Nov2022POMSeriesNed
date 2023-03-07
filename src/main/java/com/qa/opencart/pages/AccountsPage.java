package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1. By Locators
	private By logoutLink = By.linkText("Logout");
	private By accountsHeader = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("#search button");

	// 2. Page Constructor
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	
	//3. Page Actions/methods
	@Step("***** Get the accounts page title *****")
	public String getAccountsPageTitle() {
		String title = eleUtil.waitForTitleContainsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
		System.out.println("AccountsPageTitle: "+title);
		return title;
	}
	
	
	@Step("***** Get the accounts page Url *****")
	public String getAccountsPageUrl() {
		String url = eleUtil.waitForUrlContainsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE);
		System.out.println("AccountsPageUrl: "+url);
		return url;
	}
	
	
	@Step("***** Check for existance of the logout link *****")
	public boolean isLogoutExists() {
		return eleUtil.waitForElementVisibility(logoutLink, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	
	
	@Step("***** Check for existance of the search field *****")
	public boolean isSearchExists() {
		return eleUtil.waitForElementVisibility(search, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	
	
	@Step("***** Get the accounts page header links text *****")
	public List<String> getAccountsPageHeadersList() {
		List<WebElement> accountsPageHeadersList = eleUtil.waitForElementsVisibility(accountsHeader, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		List<String> accountsPageHeaderTextList = new ArrayList<>();
		for(WebElement ele: accountsPageHeadersList) {
			String textValue = ele.getText();
			accountsPageHeaderTextList.add(textValue);
		}
		return accountsPageHeaderTextList;
	}
	
	
	@Step("***** Perform a search based on search key *****")
	public SearchPage performSearch(String searchKey) {
		
		if(isSearchExists()) {
			eleUtil.doSendKeys(search, searchKey);
			eleUtil.doClick(searchIcon);
			return new SearchPage(driver);
		}
		else {
			System.out.println("Search field is not present on the page");
			return null;
		}
	}

}
