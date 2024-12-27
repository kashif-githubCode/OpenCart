package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage
{
	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtemail;
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtpassword;
	@FindBy(xpath="//input[@value='Login']")
	WebElement btnlogin;
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Logout']")
	WebElement btnClickLougout;
	public void setEmail(String username)
	{
		txtemail.sendKeys(username);
	}
	public void setPassword(String pass)
	{
		txtpassword.sendKeys(pass);
	}
	public void clickLogin()
	{
		btnlogin.click();
	}
	
	
	public void logOutApp()
	{
		btnClickLougout.click();
	}
	
	
}
