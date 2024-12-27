package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDDT extends BaseClass
{
	@Test(dataProvider="loginData",dataProviderClass=DataProviders.class)
	public void verify_TestLoginDDT(String email,String pwd,String exp) throws InterruptedException
	{
		logger.info("****Start LoginDDT ************");
		HomePage hp=new HomePage(driver);
		hp.myaccount();
		hp.clickLogin();
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		Thread.sleep(2000);
		lp.clickLogin();
		Thread.sleep(2000);
		MyAccountPage map=new MyAccountPage(driver);
		boolean tg_page=map.isMyPageAccountExists();
		
		
		/* Data Valid:
		 * 1.login successful----passed
		 * 2.login unsuccessful---failed
		 * Data Invalid
		 * 1.Login successful-----failed
		 * 2.login unsuccessful---passed  
		 * 
		 * */
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(tg_page==true)
			{
				lp.logOutApp();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
			
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(tg_page==true)
			{
				
				lp.logOutApp();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		
	}

	
}
