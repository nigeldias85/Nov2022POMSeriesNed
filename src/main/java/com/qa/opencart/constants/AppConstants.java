package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {
	
	public static final int DEFAULT_MEDIUM_TIME_OUT = 10;
	public static final int DEFAULT_SHORT_TIME_OUT = 5;
	public static final int DEFAULT_LONG_TIME_OUT = 20;
	
	public static final String LOGIN_PAGE_TITLE_VALUE = "Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION_VALUE = "route=account/login";
	
	public static final String ACCOUNTS_PAGE_TITLE_VALUE = "My Account";
	public static final String ACCOUNTS_PAGE_URL_FRACTION_VALUE = "route=account/account";
	
	public static final String NAL_LOGO_IMG_SOURCE_FRACTION_VALUE = "opencart-logo.png";
	public static final int ACCOUNTS_PAGE_HEADERS_COUNT = 4;
	
	public static final List<String> EXPECTED_ACCOUNTS_PAGE_HEADER_LIST = Arrays.asList("My Account", "My Orders", 
			"My Affiliate Account", "Newsletter");
	
	public static final List<String> EXPECTED_LOGIN_PAGE_MENU_OPTIONS_LIST = Arrays.asList("Login", "Register", 
			"Forgotten Password","My Account", "Address Book", "Wish List", "Order History", "Downloads", 
			"Recurring payments","Reward Points", "Returns", "Transactions", "Newsletter");
	public static final List<String> EXPECTED_LOGIN_PAGE_FOOTER_HEADERS_LIST = Arrays.asList("Information", 
			"Customer Service", "Extras","My Account");
	public static final List<String> EXPECTED_LOGIN_PAGE_FOOTER_INFOMATION_CHILD_LINKS_LIST = Arrays.asList("About Us", 
			"Delivery Information","Privacy Policy", "Terms & Conditions");
	public static final List<String> EXPECTED_LOGIN_PAGE_FOOTER_SERVICE_CHILD_LINKS_LIST = Arrays.asList("Contact Us", 
			"Returns", "Site Map");
	public static final List<String> EXPECTED_LOGIN_PAGE_FOOTER_EXTRAS_CHILD_LINKS_LIST = Arrays.asList("Brands", 
			"Gift Certificates", "Affiliate","Specials");
	public static final List<String> EXPECTED_LOGIN_PAGE_FOOTER_MY_ACCOUNT_CHILD_LINKS_LIST = Arrays.asList("My Account",
			"Order History", "Wish List","Newsletter");
	public static final CharSequence USER_REG_SUCCESS_MESSAGE = "Your Account Has Been Created";
	
	
	/****************Sheet Names*****************************/
	public static final String REGISTER_SHEET_NAME = "register";
	
}
