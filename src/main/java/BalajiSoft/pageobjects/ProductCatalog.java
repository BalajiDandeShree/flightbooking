package BalajiSoft.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BalajiSoft.AbstractComponents.AbstractComponent;

public class ProductCatalog  extends AbstractComponent{

	WebDriver driver;

	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//List<WebElement> list = driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	@FindBy(css=".ng-animating")
	WebElement spinner;

	
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.id("toast-container");
	By productBy = By.cssSelector(".mb-3");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productBy);
		return products;
		
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().contains(productName)).findFirst()
				.orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName ) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	}
	
}
