package BalajiSoft.SaleniumDesign.tests;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import BalajiSoft.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {

		String productName = "ZARA COAT 3";
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("shreeganesh@abc.com");
		driver.findElement(By.id("userPassword")).sendKeys("Shree@1234");
		driver.findElement(By.id("login")).click();
		
		LandingPage landingPage = new LandingPage(driver);
		WebDriverWait ww = new WebDriverWait(driver, Duration.ofSeconds(10));

		// ww.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<WebElement> list = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = list.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().contains(productName)).findFirst()
				.orElse(null);
		System.out.println(prod.findElement(By.cssSelector("b")).getText());
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		WebElement toast = driver.findElement(By.id("toast-container"));
		ww.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		ww.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector(".ng-animating"));
		System.out.println(toast.getText());
		driver.findElement(By.cssSelector("[routerlink*=cart]")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		// cartProducts.stream().forEach(cartProduct->System.out.println(cartProduct.getText()));

		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equals(productName));
		Assert.assertTrue(match);

		driver.findElement(By.cssSelector(".totalRow button.btn.btn-primary")).click();

		/*
		 * driver.findElement(By.xpath("//input[@placeholder='Select Country']")).
		 * sendKeys("ind");
		 * ww.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
		 * ".ta-results"))); List<WebElement> country =
		 * driver.findElements(By.cssSelector(
		 * ".ta-item.list-group-item.ng-star-inserted"));
		 * 
		 * List<WebElement> select = country.stream().filter(cname ->
		 * cname.getText().trim().equals("India")).collect(Collectors.toList());
		 * 
		 * select.get(0).click(); System.out.println(select.get(0).getText());
		 */

		Actions action = new Actions(driver);
		action.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "india").build().perform();
		ww.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.xpath("//a[contains(@class,'action__submit')]")).click();
		String confirmationMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		System.out.println(confirmationMsg);
		Assert.assertTrue(confirmationMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

}
