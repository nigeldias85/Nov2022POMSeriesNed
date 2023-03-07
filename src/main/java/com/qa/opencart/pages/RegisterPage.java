package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	
	private By agreeCheckbox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
	private By subscribeYes = By.xpath("//label[normalize-space()='Yes']/input[@type='radio']");
	private By subscribeNo = By.xpath("//label[normalize-space()='No']/input[@type='radio']");
	
	
	private By registerSuccessMessage = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	
	@Step("***** Registering a new user with all mandatory fields *****")
	public boolean registerUser(String firstName, String lastName, String email, 
			String telephone, String password, String subscribe) {
		//Enter the registration form
		eleUtil.waitForElementVisibility(this.firstName, AppConstants.DEFAULT_MEDIUM_TIME_OUT).clear();
		eleUtil.waitForElementVisibility(this.firstName, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(firstName);
		eleUtil.waitForElementVisibility(this.lastName, AppConstants.DEFAULT_SHORT_TIME_OUT).clear();
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.waitForElementVisibility(this.email, AppConstants.DEFAULT_SHORT_TIME_OUT).clear();
		eleUtil.doSendKeys(this.email, email);
		eleUtil.waitForElementVisibility(this.telephone, AppConstants.DEFAULT_SHORT_TIME_OUT).clear();
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.waitForElementVisibility(this.password, AppConstants.DEFAULT_SHORT_TIME_OUT).clear();
		eleUtil.doSendKeys(this.password, password);
		eleUtil.waitForElementVisibility(this.confirmPassword, AppConstants.DEFAULT_SHORT_TIME_OUT).clear();
		eleUtil.doSendKeys(this.confirmPassword, password);
		
		//Enable the subscribe radio button
		if(subscribe.equalsIgnoreCase("Yes")) {
			eleUtil.doClick(subscribeYes);
		}
		else {
			eleUtil.doClick(subscribeNo);
		}
		
		//Enable the checkbox for agree to privacy policy and click on continue
		//eleUtil.doActionsClick(agreeCheckbox);
		eleUtil.doClick(agreeCheckbox);
		eleUtil.doClick(continueButton);
		
		//Get the account registration success message
		String successMessage = eleUtil.waitForElementVisibility(registerSuccessMessage, AppConstants.DEFAULT_MEDIUM_TIME_OUT).getText();
		System.out.println("User registration success message: "+successMessage);
		
		if(successMessage.contains(AppConstants.USER_REG_SUCCESS_MESSAGE)) {
			//Click on logout and click on register so that subsequent user can register
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
		return false;
	}
	

}
