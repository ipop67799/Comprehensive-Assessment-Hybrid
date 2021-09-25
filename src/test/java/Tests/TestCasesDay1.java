package Tests;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPage;
import resource.Base;
import resource.excelData;
public class TestCasesDay1 extends Base{
	public static void Homepage() throws IOException {
		Logger log = LogManager.getLogger(HomePage.class.getName());
		driver = InitializeDriver();
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(".\\src\\test\\java\\resource\\data.properties");
		prop.load(fis);
		String url = prop.getProperty("url");
		driver.get(url);
		log.info("Driver Initialize: SUCCESS");
	}
	@Test
	public static void TC01() throws IOException, InterruptedException {
		Logger log = LogManager.getLogger(HomePage.class.getName());
		Homepage();
		driver.manage().window().maximize();
		HomePage home = new HomePage(driver);
		excelData d = new excelData();
		ArrayList<String> data = d.getData("testCase01");
		String tcID = data.get(0);
		String email = data.get(1);
		log.info("Get test data from excel sheet for Test ID: " + tcID + "[SUCCESS]");
		home.Email().sendKeys(email);
		home.Subscribe().click();
		Thread.sleep(2000);
		if (home.SubscribeValidation().isDisplayed() == true) {
			Assert.assertTrue(true);
			log.info("TC01 Passed: SUCCESS");
		}
		else {Assert.assertTrue(false);}
		driver.quit();
	}
	@Test
	public static void TC02() throws IOException, InterruptedException {
		Logger log = LogManager.getLogger(HomePage.class.getName());
		Homepage();
		driver.manage().window().maximize();
		HomePage home = new HomePage(driver);
		SearchPage search = new SearchPage(driver);
		excelData d = new excelData();
		ArrayList<String> data = d.getData("testCase02");
		String tcID = data.get(0);
		String searchText = data.get(1);
		log.info("Get test data from excel sheet for Test ID: " + tcID + "[SUCCESS]");
		home.Search().sendKeys(searchText);
		home.Search().sendKeys(Keys.ENTER);
		search.ArabiaProduct().click();
		Set <String> id = driver.getWindowHandles();
		Iterator <String> i = id.iterator();
		i.next();
		String child_id = i.next();
		driver.switchTo().window(child_id);
		if (search.ArabiaProductDiscount().isDisplayed() == true) {
			log.info("Extracting discount of the product: SUCCESS");
			log.info("Discount: " + search.GalenStudyChairPrice().getText());
		}
		log.info("TC02 Passed: SUCCESS");
		driver.quit();
	}
	@Test
	public static void TC03() throws IOException {
		Logger log = LogManager.getLogger(HomePage.class.getName());
		Homepage();
		driver.manage().window().maximize();
		HomePage home = new HomePage(driver);
		SearchPage search = new SearchPage(driver);
		excelData d = new excelData();
		ArrayList<String> data = d.getData("testCase03");
		String tcID = data.get(0);
		String searchText = data.get(1);
		log.info("Get test data from excel sheet for Test ID: " + tcID + "[SUCCESS]");
		home.Search().sendKeys(searchText);
		home.Search().sendKeys(Keys.ENTER);
		search.GalenStudyChair().click();
		Set <String> id = driver.getWindowHandles();
		Iterator <String> i = id.iterator();
		i.next();
		String child_id = i.next();
		driver.switchTo().window(child_id);
		if (search.GalenStudyChairPrice().isDisplayed() == true) {
			log.info("Extracting Price of the product: SUCCESS");
			log.info("Price: " + search.GalenStudyChairPrice().getText());
		}
		log.info("TC03 Passed: SUCCESS");
		driver.quit();
	}
}
