package com.qa.opencart.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC - 100 | Design for Open Cart App")
@Story("Login: 100_01 | Design login page features for Open Cart App")
public class LoginPageTest extends BaseTest {

	@Severity(SeverityLevel.TRIVIAL)
	@Description("***** Getting title of the login page | QA: Nigel *****")
	@Test
	public void loginPageTitleTest() {
		String actualTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE_VALUE);
	}

	@Severity(SeverityLevel.NORMAL)
	@Description("***** Getting URL of the login page | QA: Nigel *****")
	@Test
	public void loginPageUrlTest() {
		String actualUrl = loginPage.getLoginPageUrl();
		Assert.assertTrue(actualUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
	}

	@Severity(SeverityLevel.CRITICAL)
	@Description("***** Checking forgot password link | QA: Nigel *****")
	@Test
	public void forgotPasswordExistsTest() {
		boolean blnResult = loginPage.isForgotPasswordLinkExists();
		Assert.assertTrue(blnResult);
	}

	@Severity(SeverityLevel.CRITICAL)
	@Description("***** Checking user is able to login to open cart app with correct username and passsword | QA: Nigel *****")
	@Test(priority = 1)
	public void loginTest() {
		//accountsPage = loginPage.doLogin("qatestertest@gmail.com", "Test@123");
		accountsPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		boolean blnResult = accountsPage.isLogoutExists();
		Assert.assertTrue(blnResult);
	}

	
	@Severity(SeverityLevel.MINOR)
	@Description("***** Checking for the page logo  | QA: Nigel *****")
	@Test
	public void isLogoPresent() {
		boolean blnResult = loginPage.isNalLogoDisplayed();
		Assert.assertTrue(blnResult);
	}

	@Severity(SeverityLevel.MINOR)
	@Description("***** Vaalidate source for the page logo | QA: Nigel *****")
	@Test
	public void logoSourceTest() {
		String logoSrc = loginPage.getLogoSource();
		System.out.println("lgoSrc: " + logoSrc);
		Assert.assertTrue(logoSrc.contains(AppConstants.NAL_LOGO_IMG_SOURCE_FRACTION_VALUE));
	}

	@Severity(SeverityLevel.MINOR)
	@Description("***** Validating title of the page logo | QA: Nigel *****")
	@Test
	public void logoTitleTest() {
		String logoTitle = loginPage.getLogoTitle();
		System.out.println("logoTitle " + logoTitle);
		Assert.assertEquals(logoTitle, "naveenopencart");
	}

	@Severity(SeverityLevel.CRITICAL)
	@Description("***** Getting menu options on the login page  | QA: Nigel *****")
	@Test
	public void menuOptionsTextTest() {

		List<String> menuOptionTextList = loginPage.getMenuOptionLinksText();

		// Validate the number of menu options
		Assert.assertTrue(AppConstants.EXPECTED_LOGIN_PAGE_MENU_OPTIONS_LIST.size() == menuOptionTextList.size());

		// Validate the content
		Assert.assertEquals(menuOptionTextList, AppConstants.EXPECTED_LOGIN_PAGE_MENU_OPTIONS_LIST);
	}

	
	@Severity(SeverityLevel.CRITICAL)
	@Description("***** Getting footer section headers of the page | QA: Nigel *****")
	@Test
	public void footerSectionHeaderTextTest() {
		List<String> footerSectionHeadersText = loginPage.getFooterSectionHeadersText();

		// Validate the number of menu options
		Assert.assertTrue(AppConstants.EXPECTED_LOGIN_PAGE_FOOTER_HEADERS_LIST.size() == footerSectionHeadersText.size());

		// Validate the content
		Assert.assertEquals(footerSectionHeadersText, AppConstants.EXPECTED_LOGIN_PAGE_FOOTER_HEADERS_LIST);
	}

	
	@Severity(SeverityLevel.CRITICAL)
	@Description("***** Getting footer child links of the page | QA: Nigel *****")
	@Test
	public void footerSectionChildLinksTextTest() {
		List<String> footerSectionHeadersText = loginPage.getFooterSectionHeadersText();
		List<List<String>> expectedFooterChildLinkTestLists = new ArrayList<>();
		expectedFooterChildLinkTestLists.add(AppConstants.EXPECTED_LOGIN_PAGE_FOOTER_INFOMATION_CHILD_LINKS_LIST);
		expectedFooterChildLinkTestLists.add(AppConstants.EXPECTED_LOGIN_PAGE_FOOTER_SERVICE_CHILD_LINKS_LIST);
		expectedFooterChildLinkTestLists.add(AppConstants.EXPECTED_LOGIN_PAGE_FOOTER_EXTRAS_CHILD_LINKS_LIST);
		expectedFooterChildLinkTestLists.add(AppConstants.EXPECTED_LOGIN_PAGE_FOOTER_MY_ACCOUNT_CHILD_LINKS_LIST);
		

		// Iterate with each footer section header
		for (int i = 0; i < footerSectionHeadersText.size(); i++) {

			// Get the child links text corresponding to each header
			String headerValue = footerSectionHeadersText.get(i);
			List<String> footerSectionChildLinksText = loginPage.getFooterSectionChildLinksText(headerValue);

			// Validate the number of child link for each footer block/section
			System.out.println("Comparing expected: " + expectedFooterChildLinkTestLists.get(i).size() + " and actual: "
					+ footerSectionChildLinksText.size());
			Assert.assertTrue(expectedFooterChildLinkTestLists.get(i).size() == footerSectionChildLinksText.size());

			// Validate the content
			System.out.println("Comparing expected: " + expectedFooterChildLinkTestLists.get(i) + " and actual: "
					+ footerSectionChildLinksText);
			Assert.assertEquals(footerSectionChildLinksText, expectedFooterChildLinkTestLists.get(i));
		}
	}

}
