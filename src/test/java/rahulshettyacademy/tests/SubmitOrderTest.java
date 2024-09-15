package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LoginPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogPage;

public class SubmitOrderTest extends BaseTest{

	
	@Test(dataProvider="getData",groups= {"Purchase"})
	//public void submitOrder(String userName,String password, String productName) throws IOException{
	public void submitOrder(HashMap<String, String> inputs) throws IOException{
			// TODO Auto-generated method stub
		
		//String productName ="ZARA";		
		
		loginPage.loginToApplication(inputs.get("email"), inputs.get("password"));
		
		ProductCatalogPage productCatalogPage = new ProductCatalogPage(driver);
		productCatalogPage.findProductByName(inputs.get("productName"));		
		productCatalogPage.addProductToCart(inputs.get("productName"));
		productCatalogPage.goToCartPage();
		
		CartPage cartPage = new CartPage(driver);
		Boolean match =  cartPage.verifyProductInCart(inputs.get("productName"));
		Assert.assertTrue(match, "Item NOT Present in cart");
		
		cartPage.clickOnCheckout();
		
		CheckoutPage checkoutPage = new CheckoutPage(driver);		
		checkoutPage.selectCountry("India");
		checkoutPage.clickOnPlaceOrder();
		
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		
		Assert.assertTrue(confirmationPage.getConfirmationMsg().equalsIgnoreCase("Thankyou for the order."));
		
		System.out.println(confirmationPage.getOrderId());
	
	}
	
	
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest() {
		
		String orderName = "ZARA";
		
		loginPage.loginToApplication("mohdakhtar@gmail.com", "Udemy@123");
		
		OrderPage orderPage = new OrderPage(driver);
		
		orderPage.goToOrdersPage();
		
		boolean flag= orderPage.verifyOrderDisplay(orderName);
		
		Assert.assertTrue(flag);
		
	}
	
	
//	@DataProvider
//	public Object[][] getData() {
//		
//		return new Object[][] {
//				{"mohdakhtar@gmail.com","Udemy@123","ZARA COAT 3"},
//				{"mohdakhtar1@gmail.com","Udemy@123","IPHONE 13 PRO"}
//		};
//	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
//		HashMap<String,String> map1 = new HashMap<>();
//		map1.put("email", "mohdakhtar@gmail.com");
//		map1.put("password", "Udemy@123");
//		map1.put("productName", "ZARA COAT 3");
//		
//		HashMap<String,String> map2 = new HashMap<>();
//		map2.put("email", "mohdakhtar1@gmail.com");
//		map2.put("password", "Udemy@123");
//		map2.put("productName", "IPHONE 13 PRO");
		
		String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json";
		List<HashMap<String, String>> data = getJsonDataToMap(filePath);
		
		return new Object[][] {
				{data.get(0)},
				{data.get(1)}
		};
	}
	
}
