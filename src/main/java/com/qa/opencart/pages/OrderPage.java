package com.qa.opencart.pages;

import org.openqa.selenium.By;

public class OrderPage {
	
	
	By orderLocator = By.id("order");
	By pricingLocator = By.id("pricing");
	
	public void getOrder() {
		System.out.println("Get Order.");
	}
	
	
	public void getPrice() {
		System.out.println("Get Price.");
	}

}
