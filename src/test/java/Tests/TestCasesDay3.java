package Tests;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import pageObjects.AboutUsPage;
import pageObjects.HelpPage;
import pageObjects.HomePage;
import pageObjects.SearchPage;
import resource.Base;
import resource.excelData;
public class TestCasesDay3 extends Base{
	@SuppressWarnings("deprecation")
	public static void Homepage() throws IOException {
		Logger log = LogManager.getLogger(HomePage.class.getName());
		driver = InitializeDriver();
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(".\\src\\test\\java\\resource\\data.properties");
		prop.load(fis);
		String url = prop.getProperty("url");
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		log.info("Driver Initialization: SUCCESS");
	}
	@Test
	public static void TC07() throws IOException {
		Logger log = LogManager.getLogger(HomePage.class.getName());
		Homepage();
		driver.manage().window().maximize();
		HomePage home = new HomePage(driver);
		AboutUsPage about = new AboutUsPage(driver);
		home.AboutUs().click();
		log.info("Extracting contact Email ID: SUCCESS");
		log.info("Email ID: " + about.EmailID().getText());
		log.info("TC07 Passed: SUCCESS");
		driver.quit();
	}
	@Test
	public static void TC08() throws IOException {
		Logger log = LogManager.getLogger(HomePage.class.getName());
		Homepage();
		driver.manage().window().maximize();
		HomePage home = new HomePage(driver);
		log.info("Extracting CIN: SUCCESS");
		log.info("CIN: " + home.CIN().getText());
		log.info("TC08 Passed: SUCCESS");
		driver.quit();
	}
	@Test
	public static void TC09() throws IOException {
		Logger log = LogManager.getLogger(HomePage.class.getName());
		Homepage();
		driver.manage().window().maximize();
		HomePage home = new HomePage(driver);
		SearchPage search = new SearchPage(driver);
		excelData d = new excelData();
		ArrayList<String> data = d.getData("testCase09");
		String tcID = data.get(0);
		String searchText = data.get(1);
		log.info("Get test data from excel sheet for Test ID: " + tcID + "[SUCCESS]");
		home.Search().sendKeys(searchText);
		home.Search().sendKeys(Keys.ENTER);
		log.info("Extracting Price of Anton Study Table: SUCCESS");
		if (search.AntonStudyTable().isDisplayed() == true) {
			log.info("Price: " + search.AntonStudyTable().getText());
		}
		log.info("TC09 Passed: SUCCESS");
		driver.quit();
	}
	@Test
	public static void TC10() throws IOException {
		Logger log = LogManager.getLogger(HomePage.class.getName());
		Homepage();
		driver.manage().window().maximize();
		HomePage home = new HomePage(driver);
		HelpPage help = new HelpPage(driver);
		home.Help().click();
		excelData d = new excelData();
		ArrayList<String> data = d.getData("testCase10");
		String tcID = data.get(0);
		String query = data.get(1);
		log.info("Get test data from excel sheet for Test ID: " + tcID + "[SUCCESS]");
		help.HelpSearchBar().sendKeys(query);
		help.HelpSearchBar().sendKeys(Keys.ENTER);
		help.DeliveryQuery().click();
		log.info("Extracting Help Delivery Query Response: SUCCESS");
		log.info("Help Delivery Query Response: " + help.DeliveryQueryResponse().getText());
		log.info("TC10 Passed: SUCCESS");
		driver.close();
	}
}
