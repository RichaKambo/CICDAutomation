package RahulSeleniumFramework.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends BaseTest implements ITestListener {
	
	//declaring test variable and initializing extent variable to use them in overriden Listener methods
	//for creating test entry in the extent report and performing actions in the report on test success and failure like taking screenshot on failure
	//Can use ThreadLocal class (avoid concurrency issues) to make parallel tests thread safe, so that each test is alloted a unique thread id and testid associated with it is placed in a map
	//the result is that it pulls the correct test id from the map for that particular thread and does not overwrite the test variable in the extent report
	//without using this threadlocal concept, if we run parallel tests, it can show another test failed which is actually passed because of overwriting of test variable while running tests parallely
	//ThreadLocal thread = new ThreadLocal(); //make it thread safe
	// 
	 ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	
  @Override
   public void onTestStart(ITestResult result) {
	  
	
	 test = extent.createTest(result.getMethod().getMethodName());
	 //thread.set(test);// provides unique thread id on pushing the test variable into thread object
  }
	
    @Override
	 public void onTestSuccess(ITestResult result) {
	    test.log(Status.PASS, "Test Passed");
	  }
    
    @Override
	 public void onTestFailure(ITestResult result) {
	 // test.log(Status.FAIL, "Test Failed");
	  test.fail(result.getThrowable()); // Logs an event with a FAIL status and prints the exception thrown on test failure
	  //instead of test.fail use "thread.get().fail()" above for parallel tests and replace test. everywhere with thread.get().
	  //Add Screenshot and attach to report
	  try {
		driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
	} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 String filePath = null;
	  try {
		filePath = getScreenshot(result.getMethod().getMethodName(),driver);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  test.addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
	  
	  }
    
    @Override
    public void onFinish(ITestContext context) {
 	  
 		extent.flush();
   }
    

}
