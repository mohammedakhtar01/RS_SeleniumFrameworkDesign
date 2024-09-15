package rahulshettyacademy.pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
	
	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(xpath ="//h1[@class='hero-primary']")
	WebElement confirmationMsg;
	
	@FindBy(xpath ="//label[@class='ng-star-inserted']")
	WebElement orderId;
	
	By confirmationMsgBy = By.xpath("//h1[@class='hero-primary']");	
	By orderIdBy = By.xpath("//label[@class='ng-star-inserted']");	
	
	public String getConfirmationMsg() {
		
		presenceOfElement(confirmationMsgBy);
		return confirmationMsg.getText();
	}
	
	public String getOrderId() {
		
		presenceOfElement(confirmationMsgBy);
		return orderId.getText();
	}
	
	
	
	
}
