package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC - 100 | Design for Open Cart App")
@Story("Login: 100_02 | Design accounts page features for Open Cart App")
public class AccountsPageTest extends BaseTest {

	
	@BeforeClass
	public void accountsPageSetUp() {
		accountsPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	@Severity(SeverityLevel.TRIVIAL)
	@Description("***** Getting title of the accounts page | QA: Nigel *****")
	@Test
	public void accountsPageTitleTest() {
		String title = accountsPage.getAccountsPageTitle();
		Assert.assertEquals(title, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Description("***** Getting url of the accounts page | QA: Nigel *****")
	@Test
	public void accountsPageUrlTest() {
		String url = accountsPage.getAccountsPageUrl();
		Assert.assertTrue(url.contains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE));
	}
	
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("***** Checking logout link on the accounts page | QA: Nigel *****")
	@Test
	public void isLogoutLinkExistsTest() {
		boolean blnResult = accountsPage.isLogoutExists();
		Assert.assertTrue(blnResult);
	}
	
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("***** Checking count of headers on the accounts page | QA: Nigel *****")
	@Test
	public void accountsPageHeaderTest() {
		List<String> accountsPageHeaderTextList = accountsPage.getAccountsPageHeadersList();
		System.out.println("accountsPageHeaderTextList "+accountsPageHeaderTextList);
		Assert.assertTrue(accountsPageHeaderTextList.size() == AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
	}
	
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("***** Checking headers on the acccounts page | QA: Nigel *****")
	@Test
	public void accountsPageHeaderValueTest() {
		List<String> accountsPageHeaderTextList = accountsPage.getAccountsPageHeadersList();
		System.out.println("Comparing expected: "+accountsPageHeaderTextList+" and actual: "+AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADER_LIST);
		Assert.assertEquals(accountsPageHeaderTextList, AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADER_LIST);
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
				{"Macbook"},
				{"iMac"},
				{"Apple"},
				{"Samsung"}
		};
	}
	
	@Severity(SeverityLevel.BLOCKER)
	@Description("***** Checking the search feature for products in accounts page | QA: Nigel *****")
	@Test(dataProvider = "getProductData")
	public void searchProductCountTest(String searchKey) {
		searchPage = accountsPage.performSearch(searchKey);
		searchPage.getSearchProductsCount();
		Assert.assertTrue(searchPage.getSearchProductsCount() > 0);
	}
	
	
	@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][] {
				{"Macbook", "MacBook Pro"},
				{"Macbook", "MacBook Air"},
				{"iMac", "iMac"},
				{"Apple", "Apple Cinema 30\""},
				{"Samsung", "Samsung SyncMaster 941BW"},
				{"Samsung", "Samsung Galaxy Tab 10.1"}
		};
	}
	
	@Severity(SeverityLevel.BLOCKER)
	@Description("***** Checking the search results for a product | QA: Nigel *****")
	@Test(dataProvider = "getProductTestData")
	public void searchProductTest(String searchKey, String productName) {
		searchPage = accountsPage.performSearch(searchKey);
		if(searchPage.getSearchProductsCount() > 0) {
			productInfoPage = searchPage.selectProduct(productName);
			String actualProductHeaderValue = productInfoPage.getProductHeaderValue();
			Assert.assertEquals(actualProductHeaderValue, productName);
		}
	}
}
