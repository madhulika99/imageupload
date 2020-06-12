package com.qa.docs.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.qa.docs.base.TestBase;

public class Documents_page extends TestBase {

	@FindBy(xpath = "//input[@id='title']")
	WebElement Title;

	@FindBy(xpath = "//textarea[@name='description']")
	WebElement Descrip;

	@FindBy(xpath = "//input[@id='version']")
	WebElement version;

	@FindBy(xpath = "//input[@name='file']")
	WebElement file;
	@FindBy(xpath = "//select[@name='directory_id']")
	WebElement folder;

	@FindBy(xpath = "//input[@id='tags']")
	WebElement tag;

	@FindBy(xpath = "//input[@id= 'tags'] /following::input[@type='submit']")
	WebElement save;

	public Documents_page() {
		PageFactory.initElements(driver, this);
	}

	public void docsDetailsEntry(String title, String descrp, String vno, String fld, String tg)
			throws InterruptedException, FindFailed {
		Title.clear();
		Descrip.clear();
		version.clear();
		tag.clear();
		Title.sendKeys(title);
		Descrip.sendKeys(descrp);
		version.sendKeys(vno);
		Actions actions = new Actions(driver);
		actions.moveToElement(file).click().build().perform();
		String imagespath = "C:\\SELENIUM TUTORIAL\\fileimg.png";
		String openbtnpath = "C:\\SELENIUM TUTORIAL\\openbt.png";
		String filepath = "C:\\SELENIUM TUTORIAL\\maven_setup_forindows.png";

		Screen scrn = new Screen();
		Pattern fileinputtextbox = new Pattern(imagespath);
		Pattern openbtn = new Pattern(openbtnpath);
		Thread.sleep(2000);
		scrn.wait(fileinputtextbox, 20);
		scrn.type(fileinputtextbox, filepath);
		scrn.click(openbtn);
		Thread.sleep(2000);

		Select S1 = new Select(folder);
		S1.selectByVisibleText(fld);
		tag.sendKeys(tg);
		save.click();
	}

}
