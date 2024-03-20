package RahulSeleniumFramework.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulSeleniumFramework.AbstractComponents.AbstractComponents;

public class LoginPage extends AbstractComponents{
	
	public WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy (id="userEmail")
	WebElement userid;
	@FindBy (id="userPassword")
	WebElement password;
	@FindBy (id="login")
	WebElement login_button;
	@FindBy (id="toast-container")
	WebElement toastMsg;
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public ProductCatalog login(String username, String passwd)
	{
		userid.sendKeys(username);
		password.sendKeys(passwd);
		login_button.click();
		ProductCatalog ProductCatalog = new ProductCatalog(driver);
		return ProductCatalog;
		
	}

	public String getErrorMsg() {
		
		return toastMsg.getText();
	}
	
}
