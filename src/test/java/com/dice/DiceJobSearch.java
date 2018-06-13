package com.dice;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {

	public static void main(String[] args) {

		// System.setProperty("webdriver.chrome.driver",
		// "C:/Users/Emre/Documents/selenium dependencies/drivers/chromedriver.exe");

		// setup chrome driver path
		WebDriverManager.chromedriver().setup();
		// invoke selenium webdriver
		WebDriver driver = new ChromeDriver();

		// fullscreen
		driver.manage().window().maximize();
		// set universal wait time
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// step1: launch browser and navigate to https://dice.com
		// expected:dice home page should be displayed

		String url = "https://dice.com";
		driver.get(url);

		String actualTitle = driver.getTitle();
		String expectedTitle = "Job Search for Technology Professionals | Dice.com";

		if (actualTitle.equals(expectedTitle)) {
			System.out.println("Step PASS. Dice homepage successfully loaded");
		} else {
			System.out.println("Step FAIL. Dice homepage DID NOT successfully loaded");
			throw new RuntimeException("Step FAIL. Dice homepage DID NOT successfully loaded");
		}

		String keyword = "java developer";
		driver.findElement(By.id("search-field-keyword")).clear();
		driver.findElement(By.id("search-field-keyword")).sendKeys(keyword);

		String location = "22207";
		driver.findElement(By.id("search-field-location")).clear();
		driver.findElement(By.id("search-field-location")).sendKeys(location);

		driver.findElement(By.id("findTechJobs")).click();

		String count = driver.findElement(By.id("posiCountId")).getText();
		System.out.println(count);

		// ensure the count is more than 0

		int countResult = Integer.parseInt(count.replaceAll(",", ""));

		if (countResult > 0) {
			System.out.println(
					"STEP PASS : Keyword : " + keyword + " search returned " + countResult + " results in " + location);
		} else {
			System.out.println(
					"STEP FAIL : Keyword : " + keyword + " search returned " + countResult + " results in " + location);
		}

		driver.close();

		System.out.println("TEST COMPLETED " + LocalDateTime.now());

	}

}
