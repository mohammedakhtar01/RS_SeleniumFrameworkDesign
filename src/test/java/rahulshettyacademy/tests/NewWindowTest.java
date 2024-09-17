package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NewWindowTest {

	@Test
	public void WorkOnMultipleTabs() throws InterruptedException {
		// Set the path to the chromedriver executable
		// System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");

		// Initialize the WebDriver (e.g., ChromeDriver)

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		// Perform actions in the original tab
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");

		driver.findElement(By.id("name")).sendKeys("Mohd Akhtar");

		Thread.sleep(1000);

		// Open a new tab
		((JavascriptExecutor) driver).executeScript("window.open();");

		// Get the handles for all windows
		String parentHadle = driver.getWindowHandle(); // Get the handle of the original tab

		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(parentHadle)) {
				driver.switchTo().window(handle); // Switch to the new tab
				break;
			}
		}

		// Perform actions in the new tab
		driver.get("https://rahulshettyacademy.com/client/");

		driver.findElement(By.id("userEmail")).sendKeys("mohdakhtar@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Udemy@123");
		driver.findElement(By.id("login")).click();

		Thread.sleep(3000);

		// Switch back to the original tab
		driver.switchTo().window(parentHadle);

		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("Suhana Parveen");
		


		// Close the driver
		// driver.quit();
	}
}
