package pomdesign;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.AllOrdersPage;
import pages.ProductsPage;
import pages.WebOrdersLoginPage;

public class WebOrderTests {

	WebDriver driver;
	WebOrdersLoginPage loginPage;
	AllOrdersPage allOrdersPage;
	String userId = "Tester";
	String password = "test";
	ProductsPage productsPage;

	@BeforeClass
	public void setUp() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void setUpApplication() {
		driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/Login.aspx");
		loginPage = new WebOrdersLoginPage(driver);

	}

	@Test(description = "Verify labels and tab links are displayed", priority = 1)
	public void labelsVerification() {

		assertEquals(driver.getTitle().trim(), "Web Orders Login", "Login page is not displayed.");
		// loginPage.username.sendKeys("Tester");
		// loginPage.password.sendKeys("test");
		// loginPage.loginButton.click();
		loginPage.login(userId, password);

		allOrdersPage = new AllOrdersPage(driver);

		assertTrue(allOrdersPage.webOrders.isDisplayed(), "Web Orders is not displayed");
		assertTrue(allOrdersPage.listOfAllOrders.isDisplayed(), "List of orders is not displayed");
		assertEquals(allOrdersPage.welcomeMsg.getText().replace(" | Logout", ""), "Welcome, " + userId + "!");
		assertTrue(allOrdersPage.orderTab.isDisplayed(), "Order tab is not displayed");

	}

	@Test(description = "Verify default Products and Prices")
	public void availableProductsTest() {
		assertEquals(driver.getTitle().trim(), "Web Orders Login", "Login page is not displayed.");
		loginPage.login(userId, password);
		allOrdersPage = new AllOrdersPage(driver);
		allOrdersPage.viewAllProducts.click();
		productsPage = new ProductsPage(driver);

		List<String> expProducts = Arrays.asList("MyMoney", "FamilyAlbum", "ScreenSaver");
		List<String> actProducts = new ArrayList<>();
		// productsPage.productNames.forEach(elem -> actProducts.add(elem.getText()));

		for (WebElement prod : productsPage.productNames) {
			actProducts.add(prod.getText());
		}

		assertEquals(actProducts, expProducts);

		for (WebElement row : productsPage.productRows) {
			System.out.println(row.getText());
			String[] prodData = row.getText().split(" ");
			switch (prodData[0]) {
			case "MyMoney":
				assertEquals(prodData[1], "$100");
				assertEquals(prodData[2], "8%");
				break;
			case "FamilyAlbum":
				assertEquals(prodData[1], "$80");
				assertEquals(prodData[2], "15%");
				break;
			case "ScreenSaver":
				assertEquals(prodData[1], "$20");
				assertEquals(prodData[2], "10%");
				break;

			}

		}

	}

	@AfterMethod
	public void logout() {
		allOrdersPage.logOut();

	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
		
	}

}
