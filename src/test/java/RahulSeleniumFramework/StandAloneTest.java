package RahulSeleniumFramework;

import io.github.bonigarcia.wdm.WebDriverManager;

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

public class StandAloneTest {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.id("userEmail")).sendKeys("richa@rahulshetty.com");
        driver.findElement(By.id("userPassword")).sendKeys("richa26MAY");
        driver.findElement(By.id("login")).click();
		List<WebElement> products = driver.findElements(By.cssSelector("div.mb-3"));
		//System.out.println(products.size());//printing the no. of products in the list
		//using java streams to Add to Cart "Zara Coat 3" product only
		List<WebElement> newlist = products.stream().filter(s->s.findElement(By.cssSelector("div.card-body h5 b")).getText().contains("ZARA")).collect(Collectors.toList());
		newlist.stream().forEach(s->s.findElement(By.cssSelector("button.btn.w-10.rounded")).click());
		
		// The below code is using regular for loop without streams for same purpose
		/*for(int i=0;i<products.size();i++)
		{
			//extracting the product name from each webelement in the list
			String pname = products.get(i).findElement(By.cssSelector("div.card-body h5 b")).getText();
			System.out.println(pname);
			if(pname.equals("ZARA COAT 3"))
			{
	        products.get(i).findElement(By.cssSelector("button.btn.w-10.rounded")).click();
		    break;
			}
		    /* Below code to be used if all items have to be added to cart, and it was giving 'Element-not-Interceptable' exception
		     * on the second "add to cart" button click bcoz of "Element is present but having permanent Overlay" issue
             
             WebElement ele = products.get(i).findElement(By.cssSelector("button.btn.w-10.rounded"));
			 JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", ele);
			*/
			
	//	}
	// adding some random comments to demonstrate changes in code and push in GIT repo
	//waiting until the toast container (displaying "product added to cart")is visible on the page before going to cart page to avoid sync issues
	    WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		Thread.sleep(2000);
		//clicking on Cart Button
		WebElement cart = driver.findElement(By.cssSelector("[routerlink*='cart']")); //was giving element not interceptable exception, hence using JavaScriptExecutor
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();",cart);
		
		//verifying if the items displayed in the cart are same as what we added - Zara Coat 3
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[routerlink='/dashboard']")));
		List<WebElement> itemsInCart = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		System.out.println(itemsInCart.size());
		for(int i=0; i<itemsInCart.size(); i++)
		{
		System.out.println(itemsInCart.get(i).getText());
		}
		Boolean match = itemsInCart.stream().anyMatch(s->s.getText().equals("ZARA COAT 3"));
		System.out.println(match);
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector("li.totalRow button")).click(); //clicking on Checkout
		//on next page clicking on Select Country - dynamic dropdown and clicking place order
		driver.findElement(By.cssSelector(".form-group input")).sendKeys("can");
		List<WebElement> countryList = driver.findElements(By.xpath("//*[@class='form-group']/section/button"));
		
		for(int i=0;i<countryList.size();i++)
		{
			if(countryList.get(i).getText().equalsIgnoreCase("Canada"))
			{
				countryList.get(i).click();
				break;
			}
		}

		driver.findElement(By.cssSelector(".actions a")).click();
		//grabbing the 'thank you for the order' message and verifying it with Assertion and closing the end-to-end test
		String confirmationMsg = driver.findElement(By.cssSelector("h1.hero-primary")).getText();
		Assert.assertEquals(confirmationMsg,"THANKYOU FOR THE ORDER.");
		Thread.sleep(1000);
		driver.close();

      
	}

}

