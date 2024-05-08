package seleniumcourse.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import AbstractComponents.AbstractComponentg;

public class CheckOutPage extends AbstractComponentg{

	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver ;
		PageFactory.initElements(driver,this);
	}
	
//	List<WebElement> items = driver.findElements(By.cssSelector(".items h3"));
	
	@FindBy(css=".items h3")
	List<WebElement> items;
//	
//	Boolean match = items.stream().anyMatch(item->item.getText().equalsIgnoreCase(productName));
//	Assert.assertTrue(match);
	
	public Boolean verifyProductsInCart(String productName) {
		Boolean match = items.stream().anyMatch(item->item.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public ConfirmOrder goToConfirmPage() {
		ConfirmOrder confirmorder = new ConfirmOrder(driver);
		return confirmorder;
	}

}
