package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//DataProvider 1
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		String path = "D:\\Testing_Workspace\\OpenCartV111\\TestData\\Opencart__loginData.xlsx";
	                    
	ExcelUtilies xlutil=new ExcelUtilies(path); //creating object for xlutilies
	
	int totalrows= xlutil.getRowCount("Sheet1");
	int totalcols=xlutil.getCellCount("Sheet1",1);
	
	String logindata[][]=new String[totalrows][totalcols];//created two dimension array which can store
	for(int i=1;i<=totalrows;i++) //read data from xl storing in two dimensional array
	{
	   for(int j=0;j<totalcols;j++)  //i is row j is col
	   {
		logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);  //1,0 
	   }
	}
	
	return logindata;//returning two dimension array
	
	
	
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
