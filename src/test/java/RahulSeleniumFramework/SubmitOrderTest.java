package RahulSeleniumFramework;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import io.github.bonigarcia.wdm.WebDriverManager;


import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import RahulSeleniumFramework.PageObjects.CartPage;
import RahulSeleniumFramework.PageObjects.LoginPage;
import RahulSeleniumFramework.PageObjects.OrderConfirmationPage;
import RahulSeleniumFramework.PageObjects.OrderPayment;
import RahulSeleniumFramework.PageObjects.ProductCatalog;
import RahulSeleniumFramework.TestComponents.BaseTest;


public class SubmitOrderTest extends BaseTest {

	@Test(dataProvider = "getData")
		public void SubmitOrder(String email, String pwd) throws IOException, InterruptedException
	{
		
        String prodname = "ZARA COAT 3";
        //using page object model by calling appropriate methods from those classes
        //Makes test look clean and easy to understand
        //All locators for each page and action methods are encapsulated in the page object classes making it easier to change only at one place when UI is changed
        //This is one test but multiple tests can use the same methods and locators repeatedly, so this model makes it easier to make editing easier and not duplicate the code
       
        ProductCatalog ProductCatalog = LoginPage.login(email,pwd);
        ProductCatalog.addToCart(prodname);
        CartPage CartPage = ProductCatalog.goToCart();
        CartPage.displayItemsInCart();
        Boolean match = CartPage.verifyItemsInCart(prodname);
        Assert.assertTrue(match);
		Thread.sleep(2000);
		OrderPayment OrderPayment = CartPage.goToCheckout();
		OrderPayment.selectCountry("can","Canada");
		OrderConfirmationPage OrderConfirmationPage = OrderPayment.placeOrder();
		String confirmMsg = OrderConfirmationPage.getConfirmationMsg();
		Assert.assertEquals(confirmMsg,"THANKYOU FOR THE ORDER.");
		Thread.sleep(1000);
		//driver.close();

      
	}
	
	@DataProvider
	public Object[][] getData()
	{
		return new Object[][] {
				{"richa@rahulshetty.com","richa26MAY"},
				{"anshika@gmail.com","Iamking@000"}	
		};
	}

}

