package com.qa.docs.testcases;

import java.io.IOException;
import java.sql.SQLException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.qa.docs.base.TestBase;
import com.qa.docs.pages.Home_page;
import com.qa.docs.pages.Login_page;
import com.qa.docs.testutilities.TestUtil;

public class Login_page_test extends TestBase {
	Login_page login;
	Home_page homepage;
	TestUtil testutil;

	public Login_page_test() {
		super();
	}

	@BeforeMethod
	public void setup() {
		initialization();
		login = new Login_page();
		homepage = new Home_page();
		testutil = new TestUtil();

	}

	@Test(priority = 1, description = "to test login with correct credentials")

	public void loginTest() {
		Etest = Ereport.createTest("First test is login test");
		homepage = login.login_method(prop.getProperty("username"), prop.getProperty("password"));

	}

	
}
