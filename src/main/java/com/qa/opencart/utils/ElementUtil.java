package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {
	
	private WebDriver driver;
	private JavaScriptUtil jsUtil;
	
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
	}

	public WebElement getElement(By locator) {
		WebElement ele = driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(ele);
		}
		return ele;
	}
	
	public WebElement getElement(By locator, int timeout) {
		return waitForElementVisibility(locator, timeout);
	}
	
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	
	public  void doSendKeys(By locator, String value) {
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}
	
	public void doClick(By locator) {
		WebElement element = getElement(locator);
		element.click();
	}
	
	public void doActionSendKeys(By locator, String key) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), key).build().perform();
	}
	
	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).build().perform();
	}
	
	public String  doElementGetText(By locator) {
		WebElement element =  getElement(locator);
		return element.getText();
	}
	
	public boolean isElementDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	
	public String getElementAttribute(By locator, String attribute) {
		return getElement(locator).getAttribute(attribute);
	}
	
	public void getElementAttributes(By locator, String attribute) {
		List<WebElement> elementList = getElements(locator);
		for (WebElement ele : elementList) {
			System.out.println(ele.getAttribute(attribute));
		}
	}
	
	public int getTotalElementsCount(By locator) {
		int eleCount = getElements(locator).size();
		System.out.println("ElementCountFor: "+locator+" ---> "+eleCount);
		return eleCount;
	}
	
	
	public List<String> getElemntsTextList(By locator) {
		List<WebElement> elementList =   getElements(locator);
		List<String> elementTextList = new ArrayList<String>();
		for(WebElement ele: elementList) {
			elementTextList.add(ele.getText());
		}
		return elementTextList;
	}
	
	/******************SELECT BASED DROP DOWN UTILS**************************/
	public void doSelectDropDownByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}
	
	public void doSelectDropDownByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}
	
	public void doSelectDropDownByVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}
	
	public List<String> getDropDownOptionsTextList(By locator) {
		List<WebElement> optionsList = getTotalDropDownOptionsList(locator);
		List<String> optionsTextList = new ArrayList<String>();
		for(WebElement ele: optionsList) {
			String countryName = ele.getText();
			optionsTextList.add(countryName);
		}
		return optionsTextList;
	}
	
	public List<WebElement> getTotalDropDownOptionsList(By locator) {
		Select select = new Select(driver.findElement(locator));
		return select.getOptions();
	}
	
	public int getTotalDropDownOptions(By locator) {
		int optionsCount =  getTotalDropDownOptionsList(locator).size();
		System.out.println("TotalOptionsCount: "+optionsCount);
		return optionsCount;
	}
	
	
	public void selectDropDownValue(By locator, String expectedValue) {
		List<WebElement> optionsList = getTotalDropDownOptionsList(locator);
		for(WebElement ele: optionsList) {
			if(ele.getText().equals(expectedValue)) {
				ele.click();
				System.out.println(expectedValue +" is selected");
				break;
			}
		}
	}
	
	
	
	public void doSearch(By googleSuggestionLocator, String googleSearchSuggestionName,
			boolean printAllSuggestions) {

		List<WebElement> searchSuggestionsList = getElements(googleSuggestionLocator);
		System.out.println(searchSuggestionsList.size());

		// Print all the suggestions
		if (printAllSuggestions) {
			for (WebElement ele : searchSuggestionsList) {
				System.out.println(ele.getText());
			}
		}

		// Click on a specific suggestion
		for (WebElement ele : searchSuggestionsList) {
			System.out.println(ele.getText());
			if (ele.getText().contains(googleSearchSuggestionName)) {
				ele.click();
				break;
			}
		}
	}
	
	
	public void waitForPageload(int timeout) {
		long endtime = System.currentTimeMillis() + timeout;
		
		while(System.currentTimeMillis() < endtime) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String pageState = js.executeScript("return document.readyState").toString();
			if(pageState.equals("completed")) {
				System.out.println("Page DOM is fully loaded now.");
				break;
			}
		}
		
	}
	
	
	/***************WAIT UTILS*********************/
	public WebElement waitForElementPresence(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	
	public List<WebElement> waitForElementsPresence(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}
	
	
	public WebElement waitForElementVisibility(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	
	public List<WebElement> waitForElementsVisibility(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public Alert waitForJSAlertPresence(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	
	public String getAlertText(int timeout) {
		return waitForJSAlertPresence(timeout).getText();
	}
	
	
	public void acceptAlert(int timeout) {
		waitForJSAlertPresence(timeout).accept();
	}
	
	
	public void dismissAlert(int timeout) {
		waitForJSAlertPresence(timeout).dismiss();
	}
	
	
	public void alertSendKeys(int timeout, String value) {
		waitForJSAlertPresence(timeout).sendKeys(value);
	}
	
	public String waitForTitleContainsAndFetch(int timeout, String titlePartialValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.titleContains(titlePartialValue));
		return driver.getTitle();
	}
	
	public String waitForTitleIsAndFetch(int timeout, String titleValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.titleIs(titleValue));
		return driver.getTitle();
	}
	
	public String waitForUrlContainsAndFetch(int timeout, String urlPartialValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.urlContains(urlPartialValue));
		return driver.getCurrentUrl();
	}
	
	public String waitForUrlIsAndFetch(int timeout, String urlValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.urlToBe(urlValue));
		return driver.getCurrentUrl();
	}
	
	public boolean waitForUrlContains(int timeout, String urlPartialValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.urlContains(urlPartialValue));
	}
	
	
	public void waitForFrameAndSwitchToItByIdOrName(String idOrName, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
	}
	
	
	public void waitForFrameAndSwitchToItByIndex(int frameIndex, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}
	
	
	public void waitForFrameAndSwitchToItByFrameElement(WebElement frameWebElement, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameWebElement));
	}
	
	
	public void waitForFrameAndSwitchToItByFrameLocator(By frameLocator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}
	
	
	public void clickWhenReady(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	
	public WebElement waitforElementToBeClickable(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	
	public void doClickWithActionsAndWait(By locator, int timeout) {
		WebElement ele = waitforElementToBeClickable(locator, timeout);
		Actions act = new Actions(driver);
		act.click(ele).build().perform();
	}
}
