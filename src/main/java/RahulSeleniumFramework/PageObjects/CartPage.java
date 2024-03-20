package RahulSeleniumFramework.PageObjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import RahulSeleniumFramework.AbstractComponents.AbstractComponents;
import org.testng.Assert;
public class CartPage extends AbstractComponents{
	
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); //will initialize all the locators and place them in their underlying variables
		
	}

	//Apply PageFactory 
	
	
	@FindBy (xpath = "//div[@class='cartSection']/h3")
	List <WebElement> cartItems;
	
	@FindBy (css = "li.totalRow button")
	WebElement checkoutButton;
	
	
	public int getSizeoOfItemsInCart()
	{
		return cartItems.size();
	}
	
	public void displayItemsInCart()
	{
		for(int i=0; i<cartItems.size(); i++)
		{
		System.out.println(cartItems.get(i).getText());
		}
	}
	
	public Boolean verifyItemsInCart(String prodname) throws InterruptedException
	{
		Boolean match = cartItems.stream().anyMatch(s->s.getText().equals(prodname));
		System.out.println(match);
		return match;
	}
	
	public OrderPayment goToCheckout()
	{
		checkoutButton.click();
		return new OrderPayment(driver);
	}
	
	
	
	
}
