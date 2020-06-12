package com.qa.docs.testutilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.qa.docs.base.TestBase;

public class TestUtil extends TestBase {
	public static long page_load_timeout = 20;
	public static long implict_wait = 10;
	public static Xls_Reader1 reader;
	public static String TESTDATA_SHEET_PATH = ("C:\\SELENIUM TUTORIAL\\MYWORKSPACE\\Crmpro_docs\\src\\test\\java\\com\\qa\\docs\\testutilities\\Docs_details.xlsx");
	static Workbook book;
	static Sheet sheet;

	// method to switch the frame
	public void switchtoframe() {
		driver.switchTo().frame("mainpanel");
	}

	// method to take the screenshot

	public void takesScreenShotAtEndOfTest(WebDriver driver, String Screenshotname) throws IOException {
		File srcfil = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(srcfil,
				new File(currentDir + "\\Screenshots\\" + Screenshotname + System.currentTimeMillis() + ".png"));
	}

	public String takesScreenShotForLogging(WebDriver driver, String Screenshotname) throws IOException {
		String Datename = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		File Source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String Destination = System.getProperty("user.dir") + "\\screenshot\\" + Screenshotname + Datename + ".png";
		File Destin = new File(Destination);
		FileUtils.copyFile(Source, Destin);
		return Destination;
	}

	// create arraylist of object type to get data from excel

	public static ArrayList<Object[]> getDataFromExcel() {

		ArrayList<Object[]> myData = new ArrayList<Object[]>();

		try {
			reader = new Xls_Reader1("C:\\SELENIUM TUTORIAL\\Docs_details.xlsx");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int rownum = 2; rownum <= reader.getRowCount("Documents"); rownum++) {
			String Title = reader.getCellData("Documents", 0, rownum);
			String Description = reader.getCellData("Documents", 1, rownum);
			String Version = reader.getCellData("Documents", 2, rownum);
			String Folder = reader.getCellData("Documents", 3, rownum);
			String Tags = reader.getCellData("Documents", 4, rownum);
			

			Object ob[] = { Title, Description, Version, Folder, Tags};
			myData.add(ob);
			// System.out.println(myData);

		}
		return myData;
	}

	public static Object[][] getTestData(String Sheetname) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (Exception e) {
			// TODO: handle exception
		}
		sheet = book.getSheet(Sheetname);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}

		}
		return data;
	}

	public static String createXpath(String xpathexp, Object... args) {
		for (int i = 0; i < args.length; i++) {
			xpathexp = xpathexp.replace("{" + i + "}", (CharSequence) args[i]);
			// ("//input[@name = '{0}' and @value = '{1}']","receive_email",emailnfy)
		}
		return xpathexp;
	}
}
