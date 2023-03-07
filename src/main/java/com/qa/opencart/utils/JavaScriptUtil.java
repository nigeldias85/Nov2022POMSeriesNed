package com.qa.opencart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
	
	private WebDriver driver;
	
	public JavaScriptUtil(WebDriver driver) {
		this.driver = driver;
	}
	
	
	public String getTitleByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.title;").toString();
	}
	
	
	public void generateAlert(String message) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("alert('"+message+"');");
	}
	
	
	public void generateConfirmationPopUp(String message) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("confirm('"+message+"');");
	}
	
	
	public void goBackByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(-1);");
	}
	
	public void goForwardByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(1);");
	}
	
	public void refreshBrowserByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(0);");
	}
	
	
	public String getPageInnerText() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.documentElement.innerText;").toString();
	}
	
	
	public void drawBorder(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='5px solid red'", element);
	}
	
	
	public void flash(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String currBgColor = element.getCssValue("backgroundColor"); //Get current bg color
		for(int i=0; i<10; i++) {
			changeColor("rgb(0, 200, 0)", element); //1.Apply green color
			changeColor(currBgColor, element); //2.Apply current bg color
			//This continuous flipping of colors creates a highlight/flash effect
		}
	}
	
	public void changeColor(String color, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.backgroundColor='"+color+"';", element);
		
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
	
	
	public void scrollPageDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}
	
	
	public void scrollPageDown(String height) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, '"+height+"');");
	}
	
	public void scrollHalfPageDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, (document.body.scrollHeight/2));");
	}
	
	
	public void scrollPageUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight, 0);");
	}
	
	
	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	
	public void clickElementByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	
	public void sendKeysUsingIdByJS(String id, String value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('" + id + "').value='" + value + "';");
		//For eg: document.getElementById('twotabsearchtextbox').value='iphone12'
		
		
		// document.getElementById('twotabsearchtextbox').value=
	}
}
