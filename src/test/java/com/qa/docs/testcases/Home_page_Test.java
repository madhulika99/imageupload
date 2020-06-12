package com.qa.docs.testcases;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.qa.docs.base.TestBase;
import com.qa.docs.pages.Documents_page;
import com.qa.docs.pages.Home_page;
import com.qa.docs.pages.Login_page;
import com.qa.docs.testutilities.TestUtil;

public class Home_page_Test extends TestBase {

	Login_page login;
	Home_page homepage;
	TestUtil testutil;
	Documents_page docspage;

	public Home_page_Test() {
		super();
	}

	@BeforeMethod
	public void setup() {
		initialization();
		login = new Login_page();
		homepage = new Home_page();
		docspage = new Documents_page();
		testutil = new TestUtil();
		homepage = login.login_method(prop.getProperty("username"), prop.getProperty("password"));
		testutil.switchtoframe();

	}

	@Test(priority = 1, description = "check the label to verify the homepage")
	public void verifyHomepage() {
		Etest = Ereport.createTest("verfify the home page by checking the label display");
		boolean t = homepage.labelDisplayed();
		Assert.assertTrue(t);

	}

	@Test(priority = 2, description = "enter in the documents page")
	public void docsentryPage() throws InterruptedException {
		Etest = Ereport.createTest("go to the documents details page");
		docspage = homepage.docsMethod();

	}

}
