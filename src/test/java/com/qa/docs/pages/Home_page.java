package com.qa.docs.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.docs.base.TestBase;

public class Home_page extends TestBase {

	@FindBy(xpath = "//a[contains(text(),'Docs')]")
	WebElement docs;

	@FindBy(xpath = "//a[contains(text(),'New Document')]")
	WebElement newdoc;
	
	@FindBy(xpath = "//td[contains(text(),'User: Demo User')]")
	WebElement labelcheck;
	
	public Actions action;
	
	public Home_page() {
		PageFactory.initElements(driver, this);
		
	}
	
	public Documents_page docsMethod() throws InterruptedException {
		action = new Actions(driver);
		action.moveToElement(docs).build().perform();
		Thread.sleep(2000);
		newdoc.click();
		return new Documents_page();
		
	}
	
	public boolean labelDisplayed() {
		boolean flag = labelcheck.isDisplayed();
		return flag;
	}

}
