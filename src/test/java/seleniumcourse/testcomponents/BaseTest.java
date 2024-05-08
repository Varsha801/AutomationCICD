package seleniumcourse.testcomponents;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import seleniumcourse.pageObjects.LandingPage;

public class BaseTest {
	public WebDriver driver ;
	public LandingPage landingpage;
	public WebDriver initializeBrowser() throws IOException {
		
		//Global properties
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\seleniumcourse\\resources\\Globalvalues.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");
		System.out.println(browserName);
		if(browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			
			System.out.println("choose chrome browser");
			if(browserName.contains("headless")) {
				options.addArguments("headless");
				driver.manage().window().setSize(new Dimension(1440,900));
			}
		 driver=new ChromeDriver(options);
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().maximize();
		}
		
		else if(browserName.equalsIgnoreCase("edge")) {
			System.out.println("choose edge browser");
			
			 driver=new EdgeDriver();
			 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				driver.manage().window().maximize();
				
			
			}
		
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeBrowser();
		landingpage = new LandingPage(driver);
		landingpage.goTo();
		return landingpage;
		
	}
	
	public List<HashMap<String, String>> getJasonDataToMap(String filepath) throws IOException {
		File file = new File(filepath);
		String jsonContents = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String , String >> data = mapper.readValue(jsonContents, new TypeReference <List<HashMap<String , String >>>(){
			
		});
		return data;
	}
	
	public String getScreenShot(String testcasename,WebDriver driver) throws IOException {
		TakesScreenshot scrShot = (TakesScreenshot)driver;

		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File(System.getProperty("user.dir")+ "//reports//"+ testcasename + ".png" );
		FileUtils.copyFile(SrcFile, DestFile);
		return System.getProperty("user.dir")+"//reports//"+ testcasename + ".png";
	}
	
	
	
	@AfterMethod(alwaysRun=true)
	public void closeBrowser() {
		driver.quit();
	}

}
