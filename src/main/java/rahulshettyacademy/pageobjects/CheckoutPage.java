package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(xpath ="//input[@placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath ="(//button[contains(@class,'ta-item')]/span)[2]")
	WebElement selectCountry;
	
	@FindBy(xpath ="//a[contains(@class,'action__submit')]")
	WebElement placeOrderBtn;
	
	By countryBy = By.xpath("//input[@placeholder='Select Country']");	
	By placeOrderBy = By.xpath("//a[contains(@class,'action__submit')]");
	
	public void selectCountry(String countryName) {
		
		presenceOfElement(countryBy);
		country.sendKeys(countryName);
		selectCountry.click();
		inVisibilityOfElement(selectCountry);
	}
	
	public void clickOnPlaceOrder() {
		
		scrollDown();
		elementToBeClickable(placeOrderBy);
		placeOrderBtn.click();
		
	}
	
	
}
