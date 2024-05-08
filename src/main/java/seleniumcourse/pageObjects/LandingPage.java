package seleniumcourse.pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponentg;

public class LandingPage extends AbstractComponentg {

	WebDriver driver;
	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver ;
		PageFactory.initElements(driver,this);
	}

	
		// TODO Auto-generated method stub
		//WebElement userEmail = driver.findElement(By.id("userEmail"));
//	driver.findElement(By.id("userPassword")).sendKeys("Selenium1!");
//	driver.findElement(By.id("login")).click();
	
	//Page Factory 
		@FindBy(id="userEmail")
		WebElement useremail;
		
		@FindBy(id="userPassword")
		WebElement userpassword;
		
		@FindBy(id="login")
		WebElement submitButton;
		
		@FindBy(css=".ng-trigger-flyInOut")
		WebElement errorMessage ;
		
		public ProductCatalaouge loginApplication(String username , String password) {
			useremail.sendKeys(username);
			userpassword.sendKeys(password);
			submitButton.click();
			ProductCatalaouge productcat = new ProductCatalaouge(driver);
			return productcat;
		}
		
		public void goTo() {
			driver.get("https://rahulshettyacademy.com/client/");
		}

		
		public String getErrorMessage() {
			waitforElementToAppear(By.cssSelector(".ng-trigger-flyInOut"));
			return errorMessage.getText();
		}
	

}
