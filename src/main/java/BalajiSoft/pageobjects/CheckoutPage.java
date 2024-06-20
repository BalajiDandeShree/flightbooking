package BalajiSoft.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import BalajiSoft.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement sendCountry;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;

	@FindBy(css = ".action__submit")
	WebElement submit;
	By resultCountry = By.cssSelector(".ta-results.list-group.ng-star-inserted");

	public void selectCountry(String countryName) {

		Actions action = new Actions(driver);
		action.sendKeys(sendCountry, countryName).build().perform();
		// sendCountry.sendKeys(countryName);
		waitForElementToAppear(resultCountry);
		selectCountry.click();

	}

	public ConfirmationPage submitOrder() {

		submit.click();
		return new ConfirmationPage(driver);
	}

}
