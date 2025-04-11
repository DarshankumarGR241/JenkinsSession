package test.pages;

 
	import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.testng.Assert;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

	import main.pages.Loginpage;

	public class LoginTest {
		WebDriver driver;
		Loginpage loginpg;
		
		 @BeforeMethod
		 void launchbrowser()
		 {
			 driver = new ChromeDriver();
			   driver.manage().window().maximize();
			   driver.get("https://www.saucedemo.com/v1/");
			   loginpg= new Loginpage(driver);
		 }
		 @DataProvider(name = "xldata")
			Object[][] testdata() throws IOException{
				FileInputStream fis =new FileInputStream("C://Users//Darshan Kumar G R//Downloads//Sample_data_test.xlsx");
				Workbook workbook =new XSSFWorkbook(fis);
				org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
				
			int rowcount=sheet.getPhysicalNumberOfRows();
			int colcount=sheet.getRow(0).getLastCellNum();
			System.out.println( rowcount+" "+colcount);
			Object[][] data = new Object[ rowcount-1][colcount];
			
			for (int i=1;i<colcount;i++) {
				Row row=sheet.getRow(i);
				for (int j=1;j<colcount;j++) {
					Cell cell = row.getCell(j);
					data[i-1][j]=cell.toString();
				}}
			return data;
		 }
	    @Test(dataProvider  = "xldata")
	    void validlogin()
	    {
	    	loginpg.login("standard_user","secret_sauce");
	    	WebElement item = driver.findElement(By.xpath("//div[.='Sauce Labs Backpack']"));
	    	Assert.assertTrue(item.isDisplayed(), "Item is not displayed!");
	    }
	    
	    @Test
	    void verifyTitle()
	    {
	    	String Expectedtitle="Swag Labs";
	    	Assert.assertEquals(loginpg.title(), Expectedtitle);
	    	
	    }
	    
	    @AfterMethod
	    void teardown()
	    {
	    	driver.quit();
	    }
	}

	
