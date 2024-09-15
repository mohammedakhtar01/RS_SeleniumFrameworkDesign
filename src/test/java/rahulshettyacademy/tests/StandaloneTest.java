package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LoginPage;
import rahulshettyacademy.pageobjects.ProductCatalogPage;

public class StandaloneTest {

		//Steps
		/*
		 * Click on cart
		 * Check if the item  added is shown in Add cart page
		 * Scenario -2 = Add Multiple items and validate
		 * click on checkout
		 * Fill the details and place the order
		 * Place assertion on thanks and Print the OrderId
		 * 
		 */
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String productName ="ZARA";
		
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.goTo();
		loginPage.loginToApplication("mohdakhtar@gmail.com", "Udemy@123");
		
		ProductCatalogPage productCatalogPage = new ProductCatalogPage(driver);
		productCatalogPage.findProductByName(productName);		
		productCatalogPage.addProductToCart(productName);
		productCatalogPage.goToCartPage();
		
		CartPage cartPage = new CartPage(driver);
		Boolean match =  cartPage.verifyProductInCart(productName);
		Assert.assertTrue(match, "Item NOT Present in cart");
		
		cartPage.clickOnCheckout();
		
		CheckoutPage checkoutPage = new CheckoutPage(driver);		
		checkoutPage.selectCountry("India");
		checkoutPage.clickOnPlaceOrder();
		
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		
		Assert.assertTrue(confirmationPage.getConfirmationMsg().equalsIgnoreCase("Thankyou for the order."));
		
		System.out.println(confirmationPage.getOrderId());
		
		//System.out.println(filterProduct.size());
		
//		for(WebElement elem : filterProduct) {
//			System.out.println(elem.getText());
//		} 
		
//		if(filterProduct.size()!=0) {
//			filterProduct.get(0).findElement(By.xpath("//button[contains(text(),'Add To Cart')]")).click();
//		}
		
		
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[contains(@class,'la-ball-scale-multiple')]"))));
		
		
		//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@role='alert']"))));
		//String msg = driver.findElement(By.xpath("//div[@role='alert']")).getText();
		
		//System.out.println(msg);
		
		
//		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
//		
//		
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='cart']"))));
//		
//		List<WebElement> itemsInCart = driver.findElements(By.xpath("//div[@class='cart']"));
//		
//		//System.out.println("Items In cart =" + itemsInCart.size());
//		
//		if(itemsInCart.size()>0) {
//			
//			boolean flag = itemsInCart.stream().
//			anyMatch(item -> item.findElement(By.xpath("//div[@class='cartSection']//h3")).getText().contains(productName));
//			
//			if(flag)
//				System.out.println("Item Added to Cart Successfully");
//			
//		}else {
//			Assert.fail("No Items In Cart");
//		}
		
		
		
//		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		
		
		
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Select Country']")));
//		
//		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");
//		
//		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')]/span)[2]")).click();
//		
//		
//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		js.executeScript("window.scrollBy(0,600)");
//
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class,'action__submit')]")));
//		
//		driver.findElement(By.xpath("//a[contains(@class,'action__submit')]")).click();
		
		/*
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[@class='hero-primary']")));
		
		Assert.assertTrue( driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText().equalsIgnoreCase("Thankyou for the order."));
		
		System.out.println("OrderId ="+driver.findElement(By.xpath("//label[@class='ng-star-inserted']")).getText());
		
		*/
	}

}
