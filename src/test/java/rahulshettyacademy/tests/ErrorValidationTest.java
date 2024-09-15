package rahulshettyacademy.tests;

import java.io.IOException;
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
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LoginPage;
import rahulshettyacademy.pageobjects.ProductCatalogPage;

public class ErrorValidationTest extends BaseTest{

	
	@Test(groups={"ErrorHandling"},retryAnalyzer = Retry.class)
	public void loginValidation() throws IOException{
		// TODO Auto-generated method stub
		
		
		loginPage.loginToApplication("abcde@gmail.com", "Udemy@123");
		
		System.out.println(loginPage.getErrorMsg());
		
		Assert.assertEquals(loginPage.getErrorMsg().trim(), "Incorrect email or password.");
		
		
	
	}
	
	

	@Test(groups= {"ErrorHandling"})
	public void productErrorValidation() throws IOException{
		// TODO Auto-generated method stub
		
		String productName ="ZARA";		
		
		loginPage.loginToApplication("mohdakhtar@gmail.com", "Udemy@123");
		
		ProductCatalogPage productCatalogPage = new ProductCatalogPage(driver);
		productCatalogPage.findProductByName(productName);		
		productCatalogPage.addProductToCart(productName);
		productCatalogPage.goToCartPage();
		
		CartPage cartPage = new CartPage(driver);
		Boolean match =  cartPage.verifyProductInCart("ABCCDE");
		Assert.assertFalse(match);
	
	}

}
