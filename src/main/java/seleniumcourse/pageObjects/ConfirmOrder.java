package seleniumcourse.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import AbstractComponents.AbstractComponentg;

public class ConfirmOrder extends AbstractComponentg {

	WebDriver driver;
	public ConfirmOrder(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver ;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css=".totalRow .btn-primary")
	WebElement confirmBtn;
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement selectCountry;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement countryOption;
	
	@FindBy(css="a.action__submit")
	WebElement orderBtn;
	
	By countryOptions = By.xpath("(//button[contains(@class,'ta-item')])[2]");
	

	
	public ThankYouPage orderConfirmation() {
		confirmBtn.click();
		selectCountry.sendKeys("india");
		waitforElementToBeClickable(countryOptions);
		countryOption.click();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Actions a = new Actions(driver);
		a.moveToElement(orderBtn).click().build().perform();
//		waitforElementToBeClickable(By.cssSelector("a.action__submit"));
//		orderBtn.click();
		ThankYouPage thankyoupage = new ThankYouPage(driver);
		return thankyoupage;
		
	}

}
