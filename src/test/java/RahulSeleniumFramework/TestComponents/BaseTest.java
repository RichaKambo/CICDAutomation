package RahulSeleniumFramework.TestComponents;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import RahulSeleniumFramework.PageObjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LoginPage LoginPage;
	

	
	public WebDriver initializeDriver() throws IOException
	{
		//properties class to parse the Globalprop.properties file and decide at runtime which browser to invoke for all your tests
		Properties prop = new Properties();
		//using FileInputStream class to convert the .properties file into an input stream as the load method takes input stream as argument
		FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir")+ "//src//main//java//RahulSeleniumFramework//Resources//Globalprop.properties");
		prop.load(inputStream); // this takes input stream as an argument.
		//the code below takes care of which browser name to execute the tests on sent from Maven commands(terminal), if there is no browser name sent from there, then it picks up browser from global properties
	
		String browserName = System.getProperty("browser")==null ? prop.getProperty("browser"):System.getProperty("browser");
		if(browserName.contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless"))
	        {
	        	options.addArguments("headless");
	        }
	        driver = new ChromeDriver(options);
	        driver.manage().window().setSize(new Dimension(1440,900));//will set to fullscreen - more full than maximize
	  	
		}
		else
		if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
	        driver = new FirefoxDriver();
		}
		else
		if(browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
	        driver = new EdgeDriver();
		}
		
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LoginPage launchApplication() throws IOException
	{
		driver = initializeDriver();
	    LoginPage = new LoginPage(driver);
        LoginPage.goTo();
       return LoginPage;
     
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeApplication()
	{
		driver.close();
    }
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source,file);
		return System.getProperty("user.dir")+ "//reports//" + testCaseName + ".png";
		
	}
	
}
