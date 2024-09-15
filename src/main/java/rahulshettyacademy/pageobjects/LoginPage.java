package rahulshettyacademy.pageobjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent{
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement loginBtn;
	
	@FindBy(xpath="//div[contains(@class,'ng-trigger-flyInOut')]")
	WebElement loginErrorMsg;
	
	
	public void goTo() {
		
		driver.get("https://rahulshettyacademy.com/client");
	
	}
	
	public void loginToApplication(String userName, String password) {
		
		userEmail.sendKeys(userName);
		userPassword.sendKeys(password);;
		loginBtn.click();
		
	}

	public String getErrorMsg() {
		
		visibilityOfElement(loginErrorMsg);
		return loginErrorMsg.getText();
	}
	
}
