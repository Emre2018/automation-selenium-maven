package pomdesign;

import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.AllOrdersPage;
import pages.OrdersPage;
import pages.ProductsPage;
import pages.WebOrdersLoginPage;

public class WebOrders_OrderTest {

	WebDriver driver;
	WebOrdersLoginPage loginPage;
	AllOrdersPage allOrdersPage;
	String userId = "Tester";
	String password = "test";
	ProductsPage productsPage;
	OrdersPage orderPage;
	Faker faker;
	Select select;
	Random r;

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
		orderPage = new OrdersPage(driver);
		faker = new Faker();
		r = new Random();

	}

	@Test
	public void loginAndOpenOrderPage() {
		loginPage.login(userId, password);
		orderPage.order.click();

		Select s = new Select(orderPage.product);
		s.selectByIndex(r.nextInt(3));

		orderPage.quantity.sendKeys("" + (r.nextInt(100) + 1));
		orderPage.pricePerUnit.sendKeys("" + (r.nextInt(100) + 1));
		orderPage.discount.sendKeys("" + (r.nextInt(51) + 1));
		orderPage.calculate.click();

		int actualTotal = Integer.parseInt(orderPage.total.getAttribute("value"));
		int rawTotal = Integer.parseInt(orderPage.quantity.getAttribute("value"))
				* Integer.parseInt(orderPage.pricePerUnit.getAttribute("value"));
		int discountedTotal = (int) (rawTotal * Integer.parseInt(orderPage.discount.getAttribute("value")) / 100);
		int expectedTotal = rawTotal - discountedTotal;

		assertEquals(actualTotal, expectedTotal, "Calculation is false");

		orderPage.customerName.sendKeys(faker.name().fullName());
		orderPage.street.sendKeys(faker.address().streetAddress());
		orderPage.city.sendKeys(faker.address().cityName());
		orderPage.state.sendKeys(faker.address().state());
		orderPage.zip.sendKeys(faker.address().zipCode().substring(0, 5));

		WebElement cardType = orderPage.card.get(r.nextInt(3));
		cardType.click();

		String cardName = cardType.getAttribute("value");
		System.out.println("======" + cardName);

		StringBuilder cardNumber = new StringBuilder();

		switch (cardName.toString()) {
		case "Visa":
			for (int i = 0; i < 14; i++) {
				cardNumber.append(r.nextInt(9));
			}
			break;
		case "MasterCard":
			for (int i = 0; i < 14; i++) {
				cardNumber.append(r.nextInt(9));
			}
			break;

		case "American Express":
			for (int i = 0; i < 15; i++) {
				cardNumber.append(r.nextInt(9));
			}
			break;
		}
		System.out.println(cardNumber);
		orderPage.cardNr.sendKeys(cardNumber);

		orderPage.expireDate.sendKeys("" + (r.nextInt(12)+1) + "/" + (r.nextInt(5)+18) );
		
		
		
		
		
	}

}
