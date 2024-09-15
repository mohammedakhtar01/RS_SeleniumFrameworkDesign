package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(xpath ="//table[contains(@class,'ng-star-inserted')]")
	List<WebElement> orderList;
	
	
	
	By orderListBy = By.xpath("//table[contains(@class,'ng-star-inserted')]");	
	
	
	public List<WebElement> getOrdersList() {
		
		presenceOfElement(orderListBy);
		return orderList;
		
	}
	
	public Boolean verifyOrderDisplay(String productName) {
		
		Boolean match =getOrdersList().stream().
				anyMatch(order -> order.findElement(By.xpath("//td[2]")).getText().contains(productName));		
		return match;
	}
	
	
}
