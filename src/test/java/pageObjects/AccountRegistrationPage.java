package pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage
{
	public AccountRegistrationPage(WebDriver driver) 
	{
		super(driver);
	}

@FindBy(xpath="//input[@id='input-firstname']") WebElement txtFirstName;
@FindBy(xpath="//input[@id='input-lastname']") WebElement txtLastName;
@FindBy(xpath="//input[@id='input-email']") WebElement txtEmail;
@FindBy(xpath="//input[@id='input-telephone']")  WebElement txtTelephone;
@FindBy(xpath="//input[@id='input-password']")  WebElement txtPassword;
@FindBy(xpath="//input[@id='input-confirm']") WebElement txtPasswordConfirm;
@FindBy(xpath="//input[@name='agree']") WebElement ChkbAgree;
@FindBy(xpath="//input[@value='Continue']")  WebElement btnContinue;
@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")	
WebElement confirmationMessage;
	
public void txtFirstName(String fname)
{
	txtFirstName.sendKeys(fname);
}

public void txtlastName(String lname)
{
	txtLastName.sendKeys(lname);
}

public void email(String email)
{
	txtEmail.sendKeys(email);
	
}
public void telephone(String telephone)
{
	txtTelephone.sendKeys(telephone);
}

public void password(String password)
{
	txtPassword.sendKeys(password);
}

public void passwordConfirm(String confirmpassowrd)
{
	txtPasswordConfirm.sendKeys(confirmpassowrd);
}

public void agree()
{
	ChkbAgree.click();
}
public void submit()
{
	btnContinue.click();
	
/*	Actions action=new Actions(driver);
	action.moveToElement(btnContinue).click().perform();
	*/
/*	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript("arguments[0].click();", btnContinue);
	*/
}

public String conformationMessage()
{
	try {
		return confirmationMessage.getText();
	    }
	catch(Exception e)
	{
		return(e.getMessage());
	}
	
	
}
		
	
	



}
