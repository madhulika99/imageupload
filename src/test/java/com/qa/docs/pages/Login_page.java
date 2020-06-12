package com.qa.docs.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.docs.base.TestBase;

public class Login_page extends TestBase {
	
	@FindBy (xpath = "//input[@placeholder='Username']")
	WebElement username;
	
	 @FindBy (xpath = "//input[@name='password']")
	 WebElement password;
	 
	 @FindBy (xpath = "//input[@value='Login']")
	 WebElement login;
	 
	 public Login_page() {
		 
		 PageFactory.initElements(driver, this);
	 }
	 
	
public Home_page login_method (String uname,String pswd) {
	username.sendKeys(uname);
	password.sendKeys(pswd);
	login.click();
	return new Home_page();
}
}
