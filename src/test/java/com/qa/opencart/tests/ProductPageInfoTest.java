package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC - 100 | Design for Open Cart App")
@Story("Login: 100_03 | Design product info page features for Open Cart App")
public class ProductPageInfoTest extends BaseTest {

	
	@BeforeClass
	public void productInfoPageSetUp() {
		//accountsPage = loginPage.doLogin("qatestertest@gmail.com", "Test@123");
		accountsPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	
	@DataProvider
	public Object[][] getProductImagesTestData() {
		return new Object[][] {
				{"Macbook", "MacBook Pro", 4},
				{"iMac", "iMac", 3},
				{"Apple", "Apple Cinema 30\"", 6},
				{"Samsung", "Samsung SyncMaster 941BW", 1}
		};
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("***** Checking product images count in the product info page | QA: Nigel *****")
	@Test(dataProvider = "getProductImagesTestData")
	public void productImagesCountTest(String searchKey, String productName, int imagesCount) {
		searchPage = accountsPage.performSearch(searchKey);
		productInfoPage = searchPage.selectProduct(productName);
		int actualImagesCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(actualImagesCount, imagesCount);
	}
	
	
	@Severity(SeverityLevel.TRIVIAL)
	@Description("***** Checking product metadata for: MacBook Pro| QA: Nigel *****")
	@Test
	public void productInformationTest() {
		searchPage = accountsPage.performSearch("macbook");
		productInfoPage = searchPage.selectProduct("MacBook Pro");
		Map<String, String> actualProductInfoMap = productInfoPage.getProductInfo();
		softAssert.assertEquals(actualProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actualProductInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actualProductInfoMap.get("productname"), "MacBook Pro");
		softAssert.assertEquals(actualProductInfoMap.get("productprice"), "$2,000.00");
		softAssert.assertAll();
	}
	
	//Assert versus Verify(Soft Assertion)
	
	
	@DataProvider
	public Object[][] getProductCartData() {
		return new Object[][] {
				{"Macbook", "MacBook Pro", 2},
				{"iMac", "iMac", 1},
				//{"Apple", "Apple Cinema 30\"", 2},
				{"Samsung", "Samsung SyncMaster 941BW", 1}
		};
	}
	
	@Severity(SeverityLevel.TRIVIAL)
	@Description("***** Checking the feature of adding product to the shopping cart| QA: Nigel *****")
	@Test(dataProvider = "getProductCartData")
	public void addToCartTest(String searchKey, String productName, int quantity) {
		searchPage = accountsPage.performSearch(searchKey);
		productInfoPage = searchPage.selectProduct(productName);
		productInfoPage.enterQuantity(5);
		String actualConfirmationMessage = productInfoPage.addProductToCart();
		softAssert.assertTrue(actualConfirmationMessage.contains("Success"));
		softAssert.assertTrue(actualConfirmationMessage.contains(productName));
		softAssert.assertEquals(actualConfirmationMessage, "Success: You have added "+productName+" to your shopping cart!");
		softAssert.assertAll();
	}
}
