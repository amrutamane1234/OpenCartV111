package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{

	
	@Test(groups={"Sanity","Master"})
	public void verify_login() 
	{
		logger.info("******************Starting TC002 LoginTest******");
	try
	{
		
		//homepage
	HomePage hp=new HomePage(driver);
	
	hp.clickMyAccount();
	hp.clickLogin();
	//lohinpage
	LoginPage lp=new LoginPage(driver);
	lp.setEnail(p.getProperty("email"));
	lp.setPassword(p.getProperty("password"));
	lp.clickLogin();
	
	//myaccount
	MyAccountPage macc=new MyAccountPage(driver);
	boolean targetPage =macc.isMyAccountPageExists();
	Assert.assertEquals(targetPage, true,"Login Failed");
	
	}
	catch (Exception e)
	{
		Assert.fail();
	}
	
	logger.info("******************Finish TC002 LoginTest******");
	
	
	
	
	}
}
