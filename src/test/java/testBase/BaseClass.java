package testBase;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.security.DrbgParameters.Capability;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.net.Urls;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.Logger;          //this perticular imports for log4j
import org.apache.logging.log4j.LogManager;      //this perticular imports for log4j
public class BaseClass {

	// this class is base class for all the testcase i.e. we use same method thats why we store it in this seprate class
	
	
public static WebDriver driver;
public Logger logger;  //log4j step
public Properties p;

	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os , String br) throws IOException
	{
		//loading config.prperties file
		
		FileReader file = new FileReader("D:\\Testing_Workspace\\OpenCartV111\\src\\test\\resources\\config,properties");  //path of properties file
		 p =new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass());
		//below code is for selenium grid
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else {
				System.out.println("No matching os");
				return;
			}
		
		//Browser
		switch(br.toLowerCase()) 
		{
		case "chrome":capabilities.setBrowserName("chrome");break;
		case "edge":capabilities.setBrowserName("MicrosoftEdge");break;
		default:System.out.println("No matching browser"); return;
		}
             driver=new RemoteWebDriver(new URL("https://localhost:4444/wd/hub"),capabilities);
		}
		
		//below code for local host
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch (br.toLowerCase()) {
		case "chrome":driver=new ChromeDriver();  break;
		case "edge":driver=new EdgeDriver();  break;
		case "firefox":driver=new FirefoxDriver();  break;
		default: System.out.println("Invalid Browser");
			return;
		}
		}
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		driver.get(p.getProperty("appURL"));    //reading url from properties file
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown() 
	{
		driver.quit();
	}
	
	//here we generate random data
	
		public String randomeString() 
		{
			
		String generatedstring=	RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
		}
		
		
		public String randomeNumber() 
		{
			
		String generatednumber=	RandomStringUtils.randomNumeric(10);
		return generatednumber;
		}
		
		public String randomeAlphaNumeric() 
		{
			
			String generatedstring=	RandomStringUtils.randomAlphabetic(3);
			String generatednumber=	RandomStringUtils.randomNumeric(3);
		return (generatedstring+"@"+generatednumber);
		}
		
		public String captureScreen(String tname) throws IOException
		{
			String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			
			TakesScreenshot takeScreenshot =(TakesScreenshot)driver;
			File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
			
			String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+tname+"__"+timeStamp+".png";
			File targetFile=new File(targetFilePath);
			
			sourceFile.renameTo(targetFile);
			
			return targetFilePath;
			
			
			
		}
		
		
		
		
		
		//but in password we cant call this method 2 times for password and confirm password bcoz when you call it 2 time it 
	    //gives to difft string so the passsword and confirm password not match thats why we create veriable in string and pass
	    //those veriable into password and confirmpassword

	
	
	
	
	
	
	
	
	
	
	
}
