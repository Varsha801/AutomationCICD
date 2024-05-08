package seleniumcourse.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Standalonetest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//SetUp 
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		
		//Login
		driver.findElement(By.id("userEmail")).sendKeys("varsha@example.com");
		driver.findElement(By.id("userPassword")).sendKeys("Selenium1!");
		driver.findElement(By.id("login")).click();
		
		//Add Products to cart 
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream().filter(product-> product.findElement(By.tagName("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".btn.w-10.rounded")).click();
		
		//Go to cart 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		//.ng-animation
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animation")));
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		//checkout
		List<WebElement> items = driver.findElements(By.cssSelector(".items h3"));
		
		Boolean match = items.stream().anyMatch(item->item.getText().equalsIgnoreCase("ZARA COAT 3"));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow .btn-primary")).click();
		//driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(@class,'ta-item')])[2]")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scroll(0,1000)");
		a.moveToElement(driver.findElement(By.cssSelector("a.action__submit"))).click().build().perform();
		//driver.findElement(By.cssSelector("a.action__submit")).click();
		System.out.println(driver.findElement(By.xpath("(//td//label)[2]")).getText().split(" ")[1]);
		
		
	}

}
