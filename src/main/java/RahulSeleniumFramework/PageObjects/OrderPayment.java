package RahulSeleniumFramework.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulSeleniumFramework.AbstractComponents.AbstractComponents;

public class OrderPayment extends AbstractComponents{
	
	WebDriver driver;

	public OrderPayment(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy (css = ".form-group input")
	WebElement country;
	
	@FindBy (xpath = "//*[@class='form-group']/section/button")
	List<WebElement> countryList;
	
	@FindBy (css =".actions a")
	WebElement OrderButton;
	
	By prodlocator = By.cssSelector(".ta-results"); //locator for the dynamic country dropdown as a whole based on part text entered
	
	
	public void selectCountry(String partCountry, String Country) throws InterruptedException
	{
		country.sendKeys(partCountry);
		waitForElementstoAppear(prodlocator);
		
		for(int i=0;i<countryList.size();i++)
		{
			if(countryList.get(i).getText().equalsIgnoreCase(Country))
			{
				countryList.get(i).click();
				break;
			}
		}
		Thread.sleep(2000);
	}
	
	public OrderConfirmationPage placeOrder() 
	{
		OrderButton.click();
		return new OrderConfirmationPage(driver);
	}
	
}
