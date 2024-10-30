package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManagerUtilityfile2 implements ITestListener
{
    public ExtentSparkReporter sparkReporter ;   //UI of the report
    public ExtentReports extent ;  //populate common info on the report
    public ExtentTest test;  // creating test case entries in the reports and update the status of the test method
	
    String repName;
    
    public void onStart(ITestContext testcontext) 
	{
    	/*SimpleDateFormate df=new SimpleDateFormate("yyyy.MM.dd.HH.mm.ss");
    	   Date dt=new Date();
    	   String currentdatetimestamp=df.format(dt);
    	   */
    	
    	
    	String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	repName="Test-Report-"+timeStamp+".html";
    	sparkReporter =new ExtentSparkReporter(".\\reports\\"+repName); //specify locaton of reports save in which folder
		
    	sparkReporter.config().setDocumentTitle("opencart Automation Report");   //title of report
    	sparkReporter.config().setReportName("opencart Functional Testing");  //name of report
    	sparkReporter.config().setTheme(Theme.DARK);
    	
    	extent=new ExtentReports();
    	extent.attachReporter(sparkReporter);
    	
    	extent.setSystemInfo("Application", "opencart");   //this values are manual
    	extent.setSystemInfo("Module", "Admin");
    	extent.setSystemInfo("SubModule", "Customer");
    	extent.setSystemInfo("user Name", System.getProperty("user.name"));
    	extent.setSystemInfo("Environment", "QA");
    	
    	String os=testcontext.getCurrentXmlTest().getParameter("os");
    	extent.setSystemInfo("Operating System", os);
    	
    	String browser = testcontext.getCurrentXmlTest().getParameter("browser");
    	extent.setSystemInfo("Browser", browser);
    	
    	List<String> includedGroups= testcontext.getCurrentXmlTest().getIncludedGroups();
    	if(!includedGroups.isEmpty()) {
    		extent.setSystemInfo("Groups", includedGroups.toString());
    	}	
	  }
	
    public  void onTestSuccess(ITestResult result)
    {
		test= extent.createTest(result.getTestClass().getName());   //create new entry in report
		
	 test.assignCategory(result.getMethod().getGroups()); //to display groups in report
	 test.log(Status.PASS, result.getName()+"got successfully executed");
    }

	
	public void onTestFailure(ITestResult result) 
	{
		test= extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL,result.getName()+"got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			String imgPath=new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
			
		}catch(IOException e1) {
			e1.printStackTrace();
		}
		
		
	  }

	
	
	public void onTestSkipped(ITestResult result)
	{
		test= extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+"got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	  }
	
	public void onFinish(ITestContext context) 
	{
		extent.flush();    //this is mandatory flush
		
		//below code is for open report automatically not need to open report manually
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(pathOfExtentReport);
		
		try 
		{
			Desktop.getDesktop().browse(extentReport.toURI());
			
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
		
		//Below code is for you send report to teammate automatically
		
	/*	try { URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
		
		//Create the email message
		
		ImageHtmlEmail email = new ImageHtmlEmail();
		email.setDataSourceResolver(new DataSourceUrlResolver(url));
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("amu.mane055@gmail.com","password"));
		email.setSSL(true);
		email.setFrom("amu.mane055@gmail.com");   //sender
		email.setSubject("Test Results");
		email.setMsg("Please find Attached Report...");
		email.addTo("mamruta596@gmail.com");      //receiver
		email.attach(url,"extent report","please check report....");
		email.send();   //send the email
		
	  }
		catch(Exception e)
		{
			e.printStackTrace();
		}
	*/
	
	
	
	
	
	
	}
	
}
