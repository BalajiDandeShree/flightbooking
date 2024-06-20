package BalajiSoft.SaleniumDesign.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import BalajiSoft.TestComponent.BaseTest;
import BalajiSoft.TestComponent.Retry;
import BalajiSoft.pageobjects.CartPage;
import BalajiSoft.pageobjects.ProductCatalog;

public class ErrroValidationTest extends BaseTest {

	@Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		landingPage.logInApplication("shreeganeassh@abc.com", "Shree@1234");
		
		System.out.println(landingPage.getErrorMessage());
		Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");

	}

	@Test
	public void ProductErrorValidation() throws InterruptedException {
		String productName = "ZARA COAT 3";

		ProductCatalog productCatalog = landingPage.logInApplication("shreeganesh@abc.com", "Shree@1234");

		List<WebElement> list = productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertFalse(match); // This test throws assertion error as product is not in the list
	}
	

}
