package seleniumcourse.stepDefinitions;

import java.io.IOException;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seleniumcourse.pageObjects.CheckOutPage;
import seleniumcourse.pageObjects.ConfirmOrder;
import seleniumcourse.pageObjects.LandingPage;
import seleniumcourse.pageObjects.ProductCatalaouge;
import seleniumcourse.pageObjects.ThankYouPage;
import seleniumcourse.testcomponents.BaseTest;

public class StepDefinitionImpl extends BaseTest{

	public LandingPage landingPage;
	public ProductCatalaouge productCatalogue;
	public CheckOutPage chkout;
	public ConfirmOrder confirmorder;
	public ThankYouPage thankyoupage ;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException 
	{
		landingPage = launchApplication();
		//code
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password)
	{
		productCatalogue = landingPage.loginApplication(username,password);
	}
	
	@When("^I add product (.+) to Cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException
	{
		chkout = productCatalogue.addToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName)
	{
		
		Boolean match = chkout.verifyProductsInCart(productName);
		Assert.assertTrue(match);

		// Confirm Order
		confirmorder = chkout.goToConfirmPage();
		thankyoupage = confirmorder.orderConfirmation();

	}
	
	@Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_confirmationPage(String string)
    {
		System.out.println(thankyoupage.confirmThankYouMessage(string));
		driver.close();
    }

}
