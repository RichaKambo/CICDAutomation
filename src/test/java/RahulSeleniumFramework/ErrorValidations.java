package RahulSeleniumFramework;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import RahulSeleniumFramework.PageObjects.ProductCatalog;
import RahulSeleniumFramework.TestComponents.BaseTest;
import RahulSeleniumFramework.TestComponents.Retry;


public class ErrorValidations extends BaseTest {
	
@Test //(retryAnalyzer=Retry.class) //any tests you feel are flaky(randomly passing or failing without any change in code) or will fail attach it to retryAnalyzer with this attribute
                                 //so automatically after executing listeners if defined, it will go to Retry class and run the retry method 
public void loginErrorValidation()
{
	 LoginPage.login("anshika@gmail.com","Iamking@0");
	 System.out.println(LoginPage.getErrorMsg());
	 Assert.assertEquals("Incorrect email or password.",LoginPage.getErrorMsg());//intentionally failing the test by altering the message
}
	
}
