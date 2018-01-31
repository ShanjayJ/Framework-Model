package com.vmetry.testcases;

import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.vmetry.datautils.ExcelReader;
import com.vmetry.lib.DriverBase;
import com.vmetry.lib.Helper;
import com.vmetry.pages.HomePage;
import com.vmetry.pages.SignUpPages;
import com.vmetry.reporter.CustomReporter;

public class SignIn {
	
	private boolean preConditionFlg = false;
	private SoftAssert s_assert;
	private String suite;
	DriverBase driver;
	HomePage homePge;
	SignUpPages signUpPge;

	@BeforeTest
	private void setUp(ITestContext cntx) {
		suite = cntx.getCurrentXmlTest().getSuite().getName().trim();
		CustomReporter.htmlRptTest = CustomReporter.htmlReport.startTest(this.getClass().getSimpleName())
				.assignCategory("Booking", suite);
		driver = new DriverBase();
		homePge = new HomePage(driver);
		signUpPge = new SignUpPages(driver);
		driver.initLogs(suite, this.getClass());
		s_assert = new SoftAssert();
		
	}

	@Test(dataProvider = "Sign In")
	public void doBooking(String browser, String mobNumb, String passwrd)
			throws InterruptedException {
		preConditionFlg = Helper.validatePreCondition(browser);
		if (preConditionFlg) {
			homePge.ClickLogin();
			signUpPge.doLogin(mobNumb, passwrd);
			s_assert.assertEquals(signUpPge.getMobNum(), mobNumb);
		} else {
			s_assert.fail("Pre-Condition Failure");
		}
		s_assert.assertAll();
	}

	@DataProvider(name = "Sign In")
	public static Object[][] getData() throws Exception {
		Object[][] data = null;
		if (ExcelReader.verifyRunMode("SignIn")) {
			data = ExcelReader.selectSingleDataOrMulitiData("SignIn");
			Helper.noOfDataset = data.length;
		}
		return data;
	}

	@AfterTest
	private void tearDown() throws Exception {
		CustomReporter.htmlReport.endTest(CustomReporter.htmlRptTest);
		CustomReporter.htmlReport.flush();
		CustomReporter.dsIndex = 1;
		Helper.noOfDataset = 0;
		s_assert = null;
	}

}
