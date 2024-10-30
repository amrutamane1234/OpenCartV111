package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	
	WebDriver driver;
	
	public AccountRegistrationPage(WebDriver driver)
	{
	       super(driver);	
	}
	
	

/*

ByXpath("//input[@id='input-telephone']")
ByXpath("//input[@id='input-password']")
ByXpath("//input[@id='input-confirm']")
ByXpath("//input[@value='Continue']")           */
	
	@FindBy(xpath="//input[@id='input-firstname']")
	     WebElement txtFirstname ;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	        WebElement txtLastname ;
	
	
	@FindBy(xpath="//input[@id='input-email']")
	        WebElement txtEmail;
 
	
	@FindBy(xpath="//input[@id='input-telephone']")
	         WebElement txtTelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	          WebElement txtPassword;
	
	
	@FindBy(xpath="//input[@id='input-confirm']")
	         WebElement txtConfirmpassword;
	
	
	@FindBy(xpath="//input[@name='agree']")
	         WebElement chkdPolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	        WebElement btnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	        WebElement msgConfirmation ;

//ByXpath("//h1[normalize-space()='Your Account Has Been Created!']")
	
	public void setFirstName(String fname) {
		txtFirstname.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		txtLastname.sendKeys(lname);
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setTelephone(String tel) {
		txtTelephone.sendKeys(tel);
	}
	
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	
	public void setConfirmPassword(String pwd) {
		txtConfirmpassword.sendKeys(pwd);
	}
	
	public void setPrivacyPolicy() {
		chkdPolicy.click();
	}
	
	public void clickContinue() {
		btnContinue.click();
	}
	
	public String getConfirmationMsg() {
		
		try {
			return (msgConfirmation.getText());
		}catch(Exception e) {
			return(e.getMessage());
		}
	}
	
	

}
