package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass
{
 
	@Test(groups={"Regression","Master"})
	public void verify_account_registration() 
	{
		try
		{
		logger.info("****starting AccountRegistrationTest*****");
		HomePage hp=new HomePage(driver);
		hp.myaccount();
		hp.register();
		logger.info("****Register page*****");
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		logger.info("****Providing customer Details*****");
		regpage.txtFirstName(randomString().toUpperCase());
		regpage.txtlastName(randomString().toUpperCase());
		regpage.email(randomString()+"@gmail.com");
		String password=alphanumeric();
		regpage.telephone(randomNumber());
		regpage.password(password);
		regpage.passwordConfirm(password);
		regpage.agree();
		regpage.submit();
		String confmsg=regpage.conformationMessage();
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}catch(Exception e)
		{
			logger.error("Test is failed");
			logger.debug("Debug logs.....");
			Assert.fail();
		}
				
		logger.info("****Finished AccountRegistrationTest*****");
	}
	
	
	
}
