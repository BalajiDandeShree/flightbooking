package BalajiSoft.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BalajiSoft.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);

	}

	// WebElement userEmail = driver.findElement(By.id("userEmail"));
	@FindBy(id = "userEmail")
	WebElement userEmail;
	
	//driver.findElement(By.id("userPassword")
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	//driver.findElement(By.id("login")
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;
	
	
	
	public ProductCatalog logInApplication(String email,String passWord) {
		
		userEmail.sendKeys(email);
		userPassword.sendKeys(passWord);
		submit.click();
		ProductCatalog productCatalog =new ProductCatalog(driver);
		return productCatalog;
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String  getErrorMessage() {
		waitForWebElementToVisible(errorMessage);
		return errorMessage.getText();
	}
	
}
