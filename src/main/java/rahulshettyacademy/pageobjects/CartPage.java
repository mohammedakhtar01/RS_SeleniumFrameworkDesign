package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(xpath ="//div[@class='cart']")
	List<WebElement> cartList;
	
	@FindBy(xpath ="//button[text()='Checkout']")
	WebElement checkoutLink;
	
	
	By cartListBy = By.xpath("//div[@class='cart']");	
	
	
	public List<WebElement> getItemsList() {
		
		presenceOfElement(cartListBy);
		return cartList;
		
	}
	
	public Boolean verifyProductInCart(String productName) {
		
		Boolean match =getItemsList().stream().
				anyMatch(item -> item.findElement(By.xpath("//div[@class='cartSection']//h3")).getText().contains(productName));		
		return match;
	}
	
	public void clickOnCheckout() {
		checkoutLink.click();
	}
	
	
}
