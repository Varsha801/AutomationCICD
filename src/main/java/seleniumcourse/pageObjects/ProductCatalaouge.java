package seleniumcourse.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponentg;

public class ProductCatalaouge extends AbstractComponentg {
	WebDriver driver;
	public ProductCatalaouge(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver ;
		PageFactory.initElements(driver,this);
	}
//	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
//	WebElement prod = products.stream().filter(product-> product.findElement(By.tagName("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
//	
//	prod.findElement(By.cssSelector(".btn.w-10.rounded")).click();
//	driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement goToCartBtn;
	@FindBy(css=".ng-animation")
	WebElement spinner;
	
	@FindBy(css="button[routerlink*='myorders']")
	WebElement myOrders;
	By products1 = By.cssSelector(".mb-3");
	By toast =  By.id("toast-container");
	
	@FindBy(xpath="//td[2]")
	List<WebElement> orderItems;
	
	public List<WebElement> getProductList() {
		waitforElementToAppear(products1);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream().filter(product-> product.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public CheckOutPage addToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(By.cssSelector(".btn.w-10.rounded")).click();
		waitforElementToAppear(toast);
		waitforElementToDisappear(spinner);
		goToCartBtn.click();
		CheckOutPage chkout = new CheckOutPage(driver);
		return chkout;
	}
	
	public Boolean goToOrders(String productName) {
		myOrders.click();
		Boolean match = orderItems.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return match;
	}
}
