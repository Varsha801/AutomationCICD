package seleniumcourse.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import seleniumcourse.testcomponents.*;

public class LoginErrormessageTest extends BaseTest {
	ExtentReports extent;
	
	
//	@BeforeClass
//	public void generateReports() {
//		System.out.println("reporter present");
//		String path = System.getProperty("user.dir")+"//reports//index.html";
//		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
//		reporter.config().setReportName("Login test Report");
//		reporter.config().setDocumentTitle("Test Results");
//		extent = new ExtentReports();
//		extent.attachReporter(reporter);
//		extent.setSystemInfo("Tester", "Varsha Narayan");
//	}

	@Test(retryAnalyzer=seleniumcourse.testcomponents.Retry.class)
	public void wrongUsername() {
		
		//ExtentTest test = extent.createTest("Login test1");
		landingpage.loginApplication("user@pimple.com" , "Selenium1!");
		String errorMessage = landingpage.getErrorMessage();
		Assert.assertEquals("Incorrect email / password.", errorMessage);
//		test.fail("No error message");
//		extent.flush();
	}
	
	@Test
	public void wrongPassword() {
		//ExtentTest test = extent.createTest("Login test2");
		landingpage.loginApplication("varsha@exapmle.com" , "Seleniu!");
		String errorMessage = landingpage.getErrorMessage();
		Assert.assertEquals("Incorrect email or password.", errorMessage);
		
		//extent.flush();
	}



@Test
public void validCred() {
	//ExtentTest test = extent.createTest("Login test3");
	landingpage.loginApplication("varsha@example.com" , "Selenium1!");
	String errorMessage = landingpage.getErrorMessage();
	//Assert.assertEquals("", errorMessage);
	System.out.println(errorMessage);
	
	//extent.flush();
}
}
