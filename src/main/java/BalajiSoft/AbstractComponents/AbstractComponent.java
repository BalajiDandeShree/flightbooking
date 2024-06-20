package BalajiSoft.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import BalajiSoft.pageobjects.CartPage;
import BalajiSoft.pageobjects.OrderPage;

public class AbstractComponent {

	WebDriver driver;
	WebDriverWait wait;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement cart;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;

	public CartPage goToCartPage() {
		cart.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}

	public void waitForElementToAppear(By findBy) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForWebElementToVisible(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public OrderPage goToOrdersPage() {
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
		
	}

	public void waitForElementToDisappear(WebElement element) throws InterruptedException {
		Thread.sleep(5000);

		/*
		 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		 * wait.until(ExpectedConditions.invisibilityOf(element));
		 */

	}

}
