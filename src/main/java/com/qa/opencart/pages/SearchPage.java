package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class SearchPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	By searchProductResults = By.cssSelector("div#content div.product-layout");
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	
	@Step("***** Select a specific product based on product name *****")
	public ProductInfoPage selectProduct(String productName) {
		By productLocator = By.linkText(productName);
		eleUtil.waitForElementVisibility(productLocator, AppConstants.DEFAULT_MEDIUM_TIME_OUT).click();
		return new ProductInfoPage(driver);
	}
	
	
	@Step("***** Get the products count for a specific product *****")
	public int getSearchProductsCount() {
		int productCount = eleUtil.waitForElementsVisibility(searchProductResults, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("ProductCount: "+productCount);
		return productCount;
	}

}
