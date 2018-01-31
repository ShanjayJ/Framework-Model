package com.vmetry.pages;

import com.vmetry.lib.DriverBase;
import com.vmetry.locators.SignUpLocators;

public class SignUpPages extends SignUpLocators {
	
	DriverBase driver;

	public SignUpPages(DriverBase driver) {
		// TODO o-generated constructor stub
		super();
		this.driver = driver;
	}

	
	public void doLogin(String mobNum , String password) {
		driver.sendKeys("ID", MOB_NUMB_ID, mobNum);
		driver.sendKeys("ID", PASSWORD_ID, password);
	}
	
	public String getMobNum() {
		return driver.getAttribute("ID", MOB_NUMB_ID, "value");
	}
	/*protected void validateLoginSuccess(String loginUser) {
	s_assert.assertTrue(driver.isElementPresent("CK_SignIn_LogOut_xpath"),
			DriverBase.prop.getProperty("CK_Generic_ErrorMsg").trim());
	s_assert.assertTrue(validateLoggedUser(loginUser),
			DriverBase.prop.getProperty("CK_SignIn_InvalidUser_Msg").trim() + " "
					+ DriverBase.prop.getProperty("CK_Expected_Const").trim() + loginUser + " "
					+ DriverBase.prop.getProperty("CK_Actual_Const").trim()
					+ driver.getDisplayedText("CK_SignIn_LoggedUser_xpath"));
}

*/

}

