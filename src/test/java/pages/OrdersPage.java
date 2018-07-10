package pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class OrdersPage {

	Random r = new Random();
	int i =r.nextInt(3);
	
	public OrdersPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Order")
	public WebElement order;
	
	@FindBy(xpath="//select[@id='ctl00_MainContent_fmwOrder_ddlProduct']")
	public WebElement product;

	@FindBy(id="ctl00_MainContent_fmwOrder_txtQuantity")
	public WebElement quantity;
	
	@FindBy(id="ctl00_MainContent_fmwOrder_txtUnitPrice")
	public WebElement pricePerUnit;
	
	@FindBy(id="ctl00_MainContent_fmwOrder_txtDiscount")
	public WebElement discount;
	
	@FindBy(xpath="//input[@id='ctl00_MainContent_fmwOrder_txtTotal']")
	public WebElement total;
	
	@FindBy(xpath="//input[@value='Calculate']")
	public WebElement calculate;
	
	@FindBy(id="ctl00_MainContent_fmwOrder_txtName")
	public WebElement customerName;
	
	
	@FindBy(id="ctl00_MainContent_fmwOrder_TextBox2")
	public WebElement street;
	
	
	@FindBy(id="ctl00_MainContent_fmwOrder_TextBox3")
	public WebElement city;
	
	
	@FindBy(id="ctl00_MainContent_fmwOrder_TextBox4")
	public WebElement state;
	
	@FindBy(id="ctl00_MainContent_fmwOrder_TextBox5")
	public WebElement zip;
	
	@FindBy(xpath="(//table)[3]//td/input")
	public List<WebElement> card;
	
	
	
	@FindBy(id="ctl00_MainContent_fmwOrder_TextBox6")
	public WebElement cardNr;
	
	@FindBy(id="ctl00_MainContent_fmwOrder_TextBox1")
	public WebElement expireDate ;
	
}
