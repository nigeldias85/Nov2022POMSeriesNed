package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String, String> productInfoMap;

	By productHeader = By.tagName("h1");
	By productImages = By.cssSelector("ul.thumbnails img");
	By poductMetadata = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	By poductPricedata = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	By quantity = By.id("input-quantity");
	By addToCartBtn = By.id("button-cart");
	By cartSuccessMessage = By.cssSelector("div.alert.alert-success");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	@Step("***** Getting the prouct header value *****")
	public String getProductHeaderValue() {
		String productHeaderValue = eleUtil.doElementGetText(productHeader);
		System.out.println("ProductHeader: " + productHeaderValue);
		return productHeaderValue;
	}

	@Step("***** Getting the product images count *****")
	public int getProductImagesCount() {
		int imageCount = eleUtil.waitForElementsVisibility(productImages, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("ImageCount: " + imageCount);
		return imageCount;
	}
	
	
	@Step("***** Set the value of product count *****")
	public void enterQuantity(int qty) {
		System.out.println("ProductQuantity: "+qty);
		eleUtil.doSendKeys(quantity, String.valueOf(qty));
	}
	
	
	@Step("***** Adding the product to the cart *****")
	public String addProductToCart() {
		eleUtil.doClick(addToCartBtn);
		String successMessage = eleUtil.waitForElementVisibility(cartSuccessMessage, AppConstants.DEFAULT_MEDIUM_TIME_OUT).getText();
		StringBuilder sb = new StringBuilder(successMessage);
		String updatedSuccessMessage = sb.substring(0, successMessage.length() -1).replace("\n", "").toString();
		System.out.println("CartSucccessMessage: "+updatedSuccessMessage);
		return updatedSuccessMessage;
	}

	@Step("***** Getting the product info *****")
	public Map<String, String> getProductInfo() {
		//productInfoMap = new HashMap<>();
		productInfoMap = new LinkedHashMap<>(); //Maintains order in which elements were added
		//productInfoMap = new TreeMap<>(); //Maintains Alphabetical Order
		// Get product header
		productInfoMap.put("productname", getProductHeaderValue().trim());
		getProductMetaData();
		getProductPricingData();
		System.out.println("ProductInfoMap: "+productInfoMap);
		return productInfoMap;
	}

	@Step("***** Getting the product metadata *****")	
	private void getProductMetaData() {
		// Get product Meta Data
		List<WebElement> productMetaList = eleUtil.getElements(poductMetadata);
		for (WebElement ele : productMetaList) {
			String metaInfo = ele.getText();
			String[] metaInfoArray = metaInfo.split(":");
			String key = metaInfoArray[0].trim();
			String value = metaInfoArray[1].trim();
			productInfoMap.put(key, value);
		}
	}

	@Step("***** Getting the product pricing data *****")
	private void getProductPricingData() {
		// Get product pricing data
		List<WebElement> productPriceList = eleUtil.getElements(poductPricedata);
		String price = productPriceList.get(0).getText();
		String exTax = productPriceList.get(1).getText();
		String[] exTaxArray = exTax.split(":");
		String exTaxValue = exTaxArray[1].trim();
		productInfoMap.put("productprice", price);
		productInfoMap.put("exTax", exTaxValue);
	}

}
