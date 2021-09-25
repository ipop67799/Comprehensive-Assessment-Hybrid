package Tests;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.GiftCardsPage;
import pageObjects.HomePage;
import pageObjects.StoresPage;
import resource.Base;
public class TestCasesDay2 extends Base{
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
	public static void TC04() throws IOException {
		Logger log = LogManager.getLogger(HomePage.class.getName());
		Homepage();
		driver.manage().window().maximize();
		HomePage home = new HomePage(driver);
		StoresPage stores = new StoresPage(driver);
		home.Stores().click();
		log.info("Navigation to Stores Page: SUCCESS");
		stores.StoreExample().click();
		if (stores.StoreAddress().isDisplayed() == true) {
			log.info("Extract Store Address: SUCCESS");
			log.info("Store Address: " + stores.StoreAddress().getText());
		}
		driver.quit();
	}
	@Test
	public static void TC05() throws IOException {
		Logger log = LogManager.getLogger(HomePage.class.getName());
		Homepage();
		driver.manage().window().maximize();
		HomePage home = new HomePage(driver);
		GiftCardsPage gifts = new GiftCardsPage(driver);
		home.GiftCards().click();
		log.info("GiftCards click: SUCCESS");
		if (gifts.TermsConditionsMessage().isDisplayed() == true) {
			log.info("Extract Terms and Conditions Message: SUCCESS");
			log.info("Terms and Conditions Message: " + gifts.TermsConditionsMessage().getText());
		}
		log.info("TC05 Passed: SUCCESS");
		driver.quit();
	}
	@Test
	public static void TC06() throws IOException {
		Logger log = LogManager.getLogger(HomePage.class.getName());
		Homepage();
		driver.manage().window().maximize();
		HomePage home = new HomePage(driver);
		CartPage cart = new CartPage(driver);
		home.Cart().click();
		if (cart.EmptyMessage().isDisplayed()) {
			log.info("Cart is Empty: SUCCESS");
			log.info("Cart Message: " + cart.EmptyMessage().getText());
		}
		log.info("TC06 Passed: SUCCESS");
		driver.quit();
	}
}
