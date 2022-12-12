package sel_object;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class cart2 {

	public static void main(String[] args) throws InterruptedException  {
		// TODO Auto-generated method stub
		String productName ="ZARA COAT 3";
		System.setProperty("webdriver.chrome.driver","/Users/Amrit/Selenium/objectpro/driver/chromedriver");
		WebDriver driver =new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		landingPage landingPage = new landingPage(driver);
		landingPage.goTo();
		ProductCatalogue productCatalogue = landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
		List<WebElement>products =productCatalogue.getProductList();
		//WebDriverWait webdwait = new WebDriverWait(driver, Duration.ofSeconds(10));

		productCatalogue.addProductToCart(productName);
		CartPage cartPage=productCatalogue.goToCartPage();
		Boolean match = cartPage.VertifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		//checkoutPage.selectCountry("india");
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		//Thread.sleep(3000);
		//driver.findElement(By.cssSelector(".action_submit")).click();
		driver.findElement(By.cssSelector(".btnn")).click();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();


		

		
	}

}
