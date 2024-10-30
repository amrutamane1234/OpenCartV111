package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement lnkMyaccount;
	
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
@FindBy(linkText = "Login")     //loginlink added in step no 5
WebElement linkLogin;
	
	

	public void clickMyAccount() 
	{
		lnkMyaccount.click();
	}
	
	public void clickMyregister()
	{
		lnkRegister.click();
	}
	
	
	public void clickLogin()
	{
		linkLogin.click();
	}
	
	
	
	
	
	
	
	
}
