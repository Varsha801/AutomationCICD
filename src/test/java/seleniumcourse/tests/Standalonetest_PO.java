package seleniumcourse.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import seleniumcourse.pageObjects.CheckOutPage;
import seleniumcourse.pageObjects.ConfirmOrder;

import seleniumcourse.pageObjects.ProductCatalaouge;
import seleniumcourse.pageObjects.ThankYouPage;
import seleniumcourse.testcomponents.BaseTest;

public class Standalonetest_PO extends BaseTest {

	@Test(dataProvider = "getData",retryAnalyzer=seleniumcourse.testcomponents.Retry.class)
	public void standaloneTest(HashMap<String, String> input) throws IOException {
		// TODO Auto-generated method stub
		// SetUp

		String originalThankYou = "THANKYOU FOR THE ORDER.";

		// Login
		// Add Products to cart
		ProductCatalaouge productcat = landingpage.loginApplication(input.get("email"), input.get("password"));

		// Go to cart
		// checkout
		CheckOutPage chkout = productcat.addToCart(input.get("product"));
		Boolean match = chkout.verifyProductsInCart(input.get("product"));
		Assert.assertTrue(match);

		// Confirm Order
		ConfirmOrder confirmorder = chkout.goToConfirmPage();

		// Thank you page
		ThankYouPage thankyoupage = confirmorder.orderConfirmation();
		System.out.println(thankyoupage.confirmThankYouMessage(originalThankYou));

	}

	@Test (dependsOnMethods = {"standaloneTest"},dataProvider = "getData")
	public void confirnOrder(HashMap<String, String> input) {
		ProductCatalaouge productcat = landingpage.loginApplication(input.get("email"), input.get("password"));
		Boolean match = productcat.goToOrders(input.get("product"));

		Assert.assertTrue(match);

	}

	@DataProvider()
	public Object[][] getData() throws IOException {

		String fis = System.getProperty("user.dir") + "//src//test//java//seleniumcourse//data//testdata.json";
		List<HashMap<String, String>> data = getJasonDataToMap(fis);
		return new Object[][] { { data.get(0) } };
	}

	

}
