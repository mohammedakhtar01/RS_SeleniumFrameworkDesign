package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalogPage extends AbstractComponent{
	
	WebDriver driver;
	
	public ProductCatalogPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(xpath ="//div[contains(@class,'col-lg-4')]")
	List<WebElement> products;
	
	@FindBy(xpath="//div[contains(@class,'la-ball-scale-multiple')]")
	WebElement spinner;
	
	@FindBy(xpath="//div[@role='alert']")
	WebElement addToCartMsg;
	
	
	By productsBy = By.xpath("//div[contains(@class,'col-lg-4')]");	
	By loginToastMsg = By.xpath("//div[@id='toast-container']");
	By addToCartBtn = By.xpath("//button[contains(text(),'Add To Cart')]");
	
	public List<WebElement> getProductList() {
		
		presenceOfElement(productsBy);
		return products;
		
	}
	
	public WebElement findProductByName(String productName) {
		
		WebElement prod = getProductList().stream().
				filter(product->product.findElement(By.tagName("b")).getText().contains(productName)).findFirst().orElse(null);		
		return prod;
	}
	
	public void addProductToCart(String productName) {
		
		WebElement prod = findProductByName(productName);
		prod.findElement(addToCartBtn).click();
		visibilityOfElement(addToCartMsg);
		inVisibilityOfElement(spinner);	
	}
	
}
