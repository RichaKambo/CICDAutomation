package RahulSeleniumFramework.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import RahulSeleniumFramework.AbstractComponents.AbstractComponents;

public class OrderConfirmationPage extends AbstractComponents{
	
	WebDriver driver;

	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy (css = "h1.hero-primary")
	WebElement confirmationMsg;
	
	public String getConfirmationMsg()
	{
		String confirmMsg = confirmationMsg.getText();
		return confirmMsg;
	}
}
