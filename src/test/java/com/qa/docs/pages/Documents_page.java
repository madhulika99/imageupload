package com.qa.docs.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.qa.docs.base.TestBase;

public class Documents_page extends TestBase {

	@FindBy(xpath = "//input[@id='title']")
	WebElement Title;

	@FindBy(xpath = "//textarea[@name='description']")
	WebElement Descrip;

	@FindBy(xpath = "//input[@id='version']")
	WebElement version;

	@FindBy(xpath = "//select[@name='directory_id']")
	WebElement folder;

	@FindBy(xpath = "//input[@id='tags']")
	WebElement tag;

	@FindBy(xpath = "//input[@id= 'tags'] /following::input[@type='submit']")
	WebElement save;

	public Documents_page() {
		PageFactory.initElements(driver, this);
	}

	public void docsDetailsEntry(String title, String descrp, String vno, String fld, String tg) {
		Title.clear();
		Descrip.clear();
		version.clear();
		tag.clear();
		Title.sendKeys(title);
		Descrip.sendKeys(descrp);
		version.sendKeys(vno);
		Select S1 = new Select(folder);
		S1.selectByVisibleText(fld);
		tag.sendKeys(tg);
		save.click();
	}

}
