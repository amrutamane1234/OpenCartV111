package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

	WebDriver driver;
	
	public BasePage(WebDriver driver) {
		
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//this is page object class using page factory
	//this class is constructor for all page object class
	
	
	
}
