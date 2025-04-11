package main.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Loginpage {
   // fetching the web objects
	WebDriver driver;
	 @FindBy( id="user-name")
	 WebElement userName;
	 
	 @FindBy( id="password")
	 WebElement passWord;
	 
	 @FindBy( xpath = " //input[@type='submit']")
	 WebElement loginBTn;
	 
	 @FindBy( xpath = " //h3[@data-test='error']")
	 WebElement errormsg;
	 
	

	// Initialize
	 public  Loginpage(WebDriver driver)
	 {
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
		 
	 }
	 
	 // Actions
	  public String title()
	  {
		  return driver.getTitle();
	  }
	 
	 public void enterun(String username)
	 {
		 userName.sendKeys(username);
	 }
	 
	 public void enterpwd(String password)
	 {
		 passWord.sendKeys(password);
	 }
	 public void clickloginBtn()
	 {
		loginBTn.click();
	 }
	 
	 public void login (String username, String password)
	 {
		 userName.sendKeys(username);
		 passWord.sendKeys(password);
		 loginBTn.click();
	 }
	 String getError()
	 {
		 return errormsg.getText();
	 }
}