package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AbstractComponent {
	
	WebDriver driver;
	WebDriverWait wait ;
	
	public AbstractComponent(WebDriver driver) {
		
		this.driver = driver;
	}
	
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartLink;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement orderHeader;
	
	
	public void presenceOfElement(By findBy) {
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		wait.until(ExpectedConditions.presenceOfElementLocated(findBy));
		
	}
	
	public void visibilityOfElement(WebElement elem) {
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		wait.until(ExpectedConditions.visibilityOf(elem));
		
	}
	
	public void inVisibilityOfElement(WebElement elem) {
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		wait.until(ExpectedConditions.invisibilityOf(elem));
		
	}
	
	
	
	public void elementToBeClickable(By findBy) {
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		wait.until(ExpectedConditions.elementToBeClickable(findBy));
		
	}
	public void scrollDown() {
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1000)");
	}
	
	
	public void goToCartPage() {
		
		cartLink.click();
	}
	
	public void goToOrdersPage() {
		
		orderHeader.click();
	}
			
}
