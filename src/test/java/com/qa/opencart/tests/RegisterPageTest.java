package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC - 100 | Design for Open Cart App")
@Story("Login: 100_04 | Design register user page features for Open Cart App")
public class RegisterPageTest extends BaseTest{

	@BeforeClass
	public void regPageSetup() {
		registerPage = loginPage.navigateToRegisterPage();
	}
	
	
	public String getRandomEmail() {
		String emailId = "auto_" + System.currentTimeMillis() + "@gmail.com";
		return emailId;
	}
	
	@DataProvider
	public Object[][] getRegTestData() {
		Object[][] regTestData = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return regTestData;
	}
	
	
	@Severity(SeverityLevel.BLOCKER)
	@Description("***** Perform new user registration | QA: Nigel *****")
	@Test(dataProvider = "getRegTestData")
	public void userRegistrationTest(String firstName, String lastName, 
			String telephone, String password, String subscribe) {
		Assert.assertTrue(registerPage.registerUser(firstName, lastName, getRandomEmail(), 
				 telephone, password, subscribe));
	}
}
