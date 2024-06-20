package BalajiSoft.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import BalajiSoft.TestComponent.BaseTest;
import BalajiSoft.pageobjects.CartPage;
import BalajiSoft.pageobjects.CheckoutPage;
import BalajiSoft.pageobjects.ConfirmationPage;
import BalajiSoft.pageobjects.LandingPage;
import BalajiSoft.pageobjects.ProductCatalog;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {

	LandingPage landingPage;
	ProductCatalog productCatalog;
	CartPage cartPage;
	CheckoutPage checkoutPage;
	ConfirmationPage confirmationPage;


	
	@Given("I landded on Ecommerce Page")
	public void I_landded_on_Ecommerce_Page() throws IOException {
		
		landingPage = launchApplication();

	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String userName, String password) {
		productCatalog = landingPage.logInApplication(userName, password);
	}

	@When("^I add product (.+) to  cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> list = productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
	}

	@When("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName) {
		cartPage = productCatalog.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		checkoutPage = cartPage.goToCheckOut();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
	}

	@Then("{string} message is displayed in ConfirmationPage")
	public void message_is_displayed_in_confirmationPage(String message) {
		String confirmationmessage = confirmationPage.getConfirmationMessage();
		System.out.println(confirmationmessage);
		Assert.assertTrue(confirmationmessage.equalsIgnoreCase(message));
		driver.close();
	}
	
	
	 @Then("{string} message is displayed")
	 public void message_is_displayed(String message) {
		 Assert.assertEquals(landingPage.getErrorMessage(), message);
		 driver.close();
	 }

}
