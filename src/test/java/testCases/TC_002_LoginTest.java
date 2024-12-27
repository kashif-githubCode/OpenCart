package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass
{
@Test(groups={"Sanity","Master"})
public void verify_Login()
{
		logger.info("*****Starting Login Test***********");
		HomePage hp=new HomePage(driver);
		hp.myaccount();
		hp.clickLogin();
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("Email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		MyAccountPage map=new MyAccountPage(driver);
		boolean tg_page=map.isMyPageAccountExists();
		Assert.assertEquals(tg_page, true);
		logger.info("**********Finished Login test**********");
}
}
