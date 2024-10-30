package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;


public class TC003_LoginDataDrivenTest extends BaseClass {
	
@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="Datadriven") //geting dataprovider from different class
public void verify_LoginDataDrivenTest(String email,String pwd,String exp)
{
	logger.info("****************************Starting TC003_LoginDataDrivenTest*****************************");
	
	
	
	try 
	{
    HomePage hp=new HomePage(driver);
	
	hp.clickMyAccount();
	hp.clickLogin();
	//lohinpage
	LoginPage lp=new LoginPage(driver);
	lp.setEnail(email);
	lp.setPassword(pwd);
	lp.clickLogin();
	
	//myaccount
	MyAccountPage macc=new MyAccountPage(driver);
	boolean targetPage =macc.isMyAccountPageExists();
	
	/*
	 * Data is valid - login success-testpass-logout
	 * Data is valid- login failed -test fail
	 * Data is invalid - login sccess-testfail-logout
	 * Data is invalid - login failed - testpass
	 */
	
	if(exp.equalsIgnoreCase("Valid"))
	{
		if(targetPage==true)
		{
			macc.clickLogout();
			Assert.assertTrue(true);
			
		}
		else
		{
			Assert.assertTrue(false);
		}
	}
	
	if(exp.equalsIgnoreCase("Inavlid"))
	{
		if(targetPage==true)
		{
			macc.clickLogout();
			Assert.assertTrue(false);
		}
	
		else
		{
			Assert.assertTrue(true);
		}
	
	}
	}
	catch(Exception e) 
	{
		Assert.fail();
	}
	
	
	
	logger.info("****************************Finish TC003_LoginDataDrivenTest*****************************");
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
	
	

}
