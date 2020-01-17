package example;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.openqa.selenium.By;		
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;		
import org.testng.Assert;			
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
public class NewTest {
	 static WebDriver driver;
	 static ExtentTest test;
	 static ExtentTest test2;

	 static ExtentReports report;
	 @BeforeSuite
	 public static void startTest()
	 {
	 report = new ExtentReports("C:\\chromedriver\\ExtentReportResults.html",true);
	 test = report.startTest("ExtentDemo");
	 }
	
	@BeforeTest
	public void beforeTest() {	
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
	   driver =new ChromeDriver();
	   
	}	
  @Test
  public void testEasy() {	
	  driver.get("http://google.com"); 
		String title = driver.getTitle();
		System.out.println(title);
		if(title.equalsIgnoreCase("google")) {
			test.log(LogStatus.PASS, "correct title");	
		}
		else
		{
			test.log(LogStatus.FAIL, "wrong title");	

			
		}
	driver.manage().window().maximize();
	test.log(LogStatus.PASS, "maximized window");
	
  }
		
  @Test(dependsOnMethods= {"testEasy"})
  public void  close()
  {
	  driver.close();
	  test.log(LogStatus.PASS, "closed window");
  }	
  
	
	@AfterTest
	public void afterTest() {
		driver.quit();	
		report.endTest(test);
	}		
	@AfterSuite
	public static void endTest()
	{
		test2=report.startTest("second test");
		System.out.println("second test");
		test2.log(LogStatus.PASS, "passed second");
		report.endTest(test2);
	
	report.flush();
	}

}	

