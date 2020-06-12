package com.qa.docs.testcases;

import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.docs.base.TestBase;
import com.qa.docs.pages.Documents_page;
import com.qa.docs.pages.Home_page;
import com.qa.docs.pages.Login_page;
import com.qa.docs.testutilities.TestUtil;

public class Documents_page_Test extends TestBase {
	Login_page login;
	Home_page homepage;
	TestUtil testutil;
	Documents_page docspage;

	public Documents_page_Test() {
		super();
	}

	@BeforeMethod
	public void setup() throws InterruptedException {
		initialization();
		login = new Login_page();
		homepage = new Home_page();
		docspage = new Documents_page();
		testutil = new TestUtil();
		homepage = login.login_method(prop.getProperty("username"), prop.getProperty("password"));
		testutil.switchtoframe();
		docspage = homepage.docsMethod();

	}

	@DataProvider (name = "exceldata")
	public Iterator<Object[]> getTest1Data() {

		ArrayList<Object[]> testdata = TestUtil.getDataFromExcel();

		return testdata.iterator();
	}

	@Test(priority = 1, dataProvider = "exceldata", description = "enter the new docs details in the form")
	public void createNewDocs(String Title, String Description, String Version, String Folder, String Tags) {
		Etest = Ereport.createTest("enter each details of the new doc");
		docspage.docsDetailsEntry(Title, Description, Version, Folder, Tags);
	}
}
