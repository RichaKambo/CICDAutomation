package RahulSeleniumFramework.PageObjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulSeleniumFramework.AbstractComponents.AbstractComponents;

public class ProductCatalog extends AbstractComponents{
	
	WebDriver driver;

	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); //will initialize all the locators and place them in their underlying variables
		
	}

	//Apply PageFactory 
	@FindBy(css = "div.mb-3")
	List <WebElement> products;
	
	By prodlocator = By.cssSelector("div.mb-3");
	By productName = By.cssSelector("div.card-body h5 b");
	By addCartButton = By.cssSelector("button.btn.w-10.rounded");
	By toastContainer = By.id("toast-container");
	
			
	public void addToCart(String prodname) throws InterruptedException
	{
		waitForElementstoAppear(prodlocator);
		List<WebElement> newlist = products.stream().filter(s->s.findElement(productName).getText().contains(prodname)).collect(Collectors.toList());
		newlist.stream().forEach(s->s.findElement(addCartButton).click());
		waitForElementstoAppear(toastContainer);
		Thread.sleep(2000);
		
	}
	
	
	
}
