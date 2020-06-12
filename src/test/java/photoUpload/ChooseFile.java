package photoUpload;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ChooseFile {
	WebDriver driver;

	@BeforeClass
	public void setUp() {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		System.setProperty("webdriver.chrome.driver", "C:\\SELENIUM TUTORIAL\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://opensource-demo.orangehrmlive.com/");
		System.out.println(driver.getTitle());

	}

	@Test
	public void loginToApplication() throws InterruptedException, FindFailed {
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("admin123");
		driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
		WebElement pm = driver.findElement(By.xpath("//b[contains(text(),'PIM')]"));
		WebElement addem = driver.findElement(By.xpath("//a[@id='menu_pim_addEmployee']"));
		Actions action = new Actions(driver);
		Thread.sleep(2000);
		action.moveToElement(pm).build().perform();
		addem.click();
		driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("Kumud");
		driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Singh");
		WebElement empid = driver.findElement(By.xpath("//input[@id='employeeId']"));
		empid.clear();
		empid.sendKeys("9667");
		WebElement photo = driver.findElement(By.xpath("//input[@id='photofile']"));
		action.moveToElement(photo).click().build().perform();
		String imagespath = "C:\\SELENIUM TUTORIAL\\fileimg.png";
		String openbtnpath = "C:\\SELENIUM TUTORIAL\\openbt.png";
		String filepath = "C:\\SELENIUM TUTORIAL\\maven_setup_forindows.png";

		Screen scrn = new Screen();
		Pattern fileinputtextbox = new Pattern(imagespath);
		Pattern openbtn = new Pattern(openbtnpath);
		Thread.sleep(2000);
		scrn.wait(fileinputtextbox, 20);
		scrn.type(fileinputtextbox,filepath);
		scrn.click(openbtn);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='btnSave']")).click();

	}
}
