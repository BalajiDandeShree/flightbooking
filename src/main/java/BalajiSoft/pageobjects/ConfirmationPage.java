package BalajiSoft.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BalajiSoft.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{

	
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver,this);
	}
	@FindBy(css=".hero-primary")
	WebElement confirmationMsg;
	
	public String getConfirmationMessage() {
		return  confirmationMsg.getText();
	}
	
	
}
