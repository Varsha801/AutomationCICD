package seleniumcourse.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AbstractComponents.AbstractComponentg;

public class ThankYouPage extends AbstractComponentg {

	WebDriver driver;
	public ThankYouPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver ;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css="h1")
	WebElement thankYouText;
	
	@FindBy(xpath="(//td//label)[2]")
	WebElement orderNo;
	
	
	//System.out.println(driver.findElement(By.xpath("(//td//label)[2]")).getText().split(" ")[1]);

	public String confirmThankYouMessage(String originalThankYou) {
		String fetchedThankYou = thankYouText.getText();
		Assert.assertEquals(originalThankYou, fetchedThankYou);
		String orderNost = orderNo.getText().split(" ")[1];
		return orderNost;
	}
}
