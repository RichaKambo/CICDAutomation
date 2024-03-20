package RahulSeleniumFramework.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import RahulSeleniumFramework.PageObjects.CartPage;

public class AbstractComponents {
	
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		
		this.driver = driver;
		
	}
 
	@FindBy (css = "[routerlink*='cart']")
	WebElement cartIcon;

	public void waitForElementstoAppear(By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
    public CartPage goToCart()
    {
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();",cartIcon);
		CartPage CartPage = new CartPage(driver);
		return CartPage;
    }

}
