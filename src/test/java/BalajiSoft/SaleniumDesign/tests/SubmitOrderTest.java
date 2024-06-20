package BalajiSoft.SaleniumDesign.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BalajiSoft.TestComponent.BaseTest;
import BalajiSoft.pageobjects.CartPage;
import BalajiSoft.pageobjects.CheckoutPage;
import BalajiSoft.pageobjects.ConfirmationPage;
import BalajiSoft.pageobjects.OrderPage;
import BalajiSoft.pageobjects.ProductCatalog;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalog productCatalog = landingPage.logInApplication(input.get("email"), input.get("password"));

		List<WebElement> list = productCatalog.getProductList();
		productCatalog.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalog.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckOut();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmationmessage = confirmationPage.getConfirmationMessage();
		System.out.println(confirmationmessage);
		Assert.assertTrue(confirmationmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		ProductCatalog productCatalog = landingPage.logInApplication("shreeganesh@abc.com", "Shree@1234");
		OrderPage orderPage = productCatalog.goToOrdersPage();
		Boolean match = orderPage.verifyOrderProductDisplay(productName);
		Assert.assertTrue(match);

	}

	@DataProvider
	public Object[][] getData() throws IOException {
	
		String filePath = System.getProperty("user.dir")+"//src//test//java//BalajiSoft//data//purchaseorder.json";
		List<HashMap<String,String>> data= 	getJSONDataToMap(filePath);
		return new Object[][] { { data.get(0) }, { data.get(1) } };
		
		/*
		 * HashMap<String, String> map = new HashMap<String, String>(); map.put("email",
		 * "shreeganesh@abc.com"); map.put("password", "Shree@1234"); map.put("product",
		 * "ZARA COAT 3");
		 * 
		 * HashMap<String, String> map1 = new HashMap<String, String>();
		 * map1.put("email", "shreeganesh@abc.com"); map1.put("password", "Shree@1234");
		 * map1.put("product", "ADIDAS ORIGINAL");
		 */
	}
	
	

}
