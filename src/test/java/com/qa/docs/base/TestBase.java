package com.qa.docs.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.docs.testutilities.TestUtil;
import com.qa.docs.testutilities.WebEventListener;

public class TestBase {
	/*
	 * initialize all the things like browser launching, maximazation and use
	 * concept of inheritance where child class can inherit the properties of base
	 * class
	 */

	public static WebDriver driver;
	public static Properties prop;
	public static Properties prop1;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventlistener;
	public ExtentHtmlReporter htmlreporter;
	public ExtentTest Etest;
	public ExtentReports Ereport;
	public TestUtil testutil;

	/*
	 * create the constructor of the testbase class for initilisation purpose and
	 * avoil null point exception error, initilisation of property file
	 */

	public TestBase() {
		try {

			prop = new Properties(); // Properties class is use to load the properties file, the extension of the
										// file should be .properties
			prop1 = new Properties();

			File f = new File(
					System.getProperty("user.dir") + "\\src\\test\\java\\com\\qa\\docs\\config\\config.properties");

			// FileInputStream fis = new
			// FileInputStream(System.getProperty("/pageobjectmodel/src/main/java/com/crm/qa/config/config.properties"));
			FileInputStream obj = new FileInputStream(f);
			// FileReader obj = new FileReader(f);
			prop.load(obj);

			/*
			 * load the cases file File f1 =new File (System.getProperty("user.dir") +
			 * "\\src\\main\\java\\com\\qa\\config\\cases.properties"); FileInputStream obj1
			 * = new FileInputStream(f1); prop1.load(obj1);
			 */

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* set up of the browse */
	// @Test
	public static void initialization() {
		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true"); // to suppress chrome
																									// warning messages
																									// like SEVERE]:
																									// Timed out
																									// receiving message
																									// from renderer:
																									// 0.100
			System.setProperty("webdriver.chrome.driver", "C:\\SELENIUM TUTORIAL\\driver\\chromedriver.exe");
			driver = new ChromeDriver();

			// System.out.println("done");
		} else if (browserName.equals("FireFox")) {
			System.setProperty("webdriver.gecko.driver",
					"C:\\SELENIUM TUTORIAL\\geckodriver-v0.26.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		e_driver = new EventFiringWebDriver(driver);
		eventlistener = new WebEventListener();
		e_driver.register(eventlistener);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.page_load_timeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.implict_wait, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));

	}

	@BeforeTest
	public void extentSetup() {
		String Datenm = new SimpleDateFormat("yyyymmdd").format(new Date());
		String currentDir = System.getProperty("user.dir");
		htmlreporter = new ExtentHtmlReporter(currentDir + "\\test-output\\" + Datenm + ".html");
		htmlreporter.config().setDocumentTitle("Autoamtion report");
		htmlreporter.config().setReportName("functional testing");
		htmlreporter.config().setTheme(Theme.DARK);
		Ereport = new ExtentReports();
		Ereport.attachReporter(htmlreporter);
		Ereport.setSystemInfo("Hostname", "LocalHost");
		Ereport.setSystemInfo("tester", "Madhulika");
	}

	@AfterMethod // The annotated method will be run after all the test methods in the current `E
	// class have been run.

	public void tearDown(ITestResult result) throws IOException {
		testutil = new TestUtil();
		testutil.takesScreenShotAtEndOfTest(driver, result.getName());
		String screenshotpath = testutil.takesScreenShotForLogging(driver, result.getName());
		if (result.getStatus() == ITestResult.FAILURE) {
			Etest.log(Status.FAIL, "Test case failed is " + result.getName()); // to log method name which failed
			Etest.log(Status.FAIL, "Test Case failed is " + result.getThrowable()); // to log all exceptions and errors
			Etest.addScreenCaptureFromPath(screenshotpath); // add screenshot in the extentreport

		} else if (result.getStatus() == ITestResult.SKIP) {
			Etest.log(Status.SKIP, "TestCase skipped is " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			Etest.log(Status.PASS, "TestCase passed is " + result.getName());
		}
	}

	@AfterTest
	public void extentClose() {
		Ereport.flush();
	}

}
