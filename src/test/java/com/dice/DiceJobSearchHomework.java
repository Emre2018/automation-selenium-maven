package com.dice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearchHomework {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

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

		List<String> keyword = new ArrayList<>();

		keyword.add("java developer"); // 1
		keyword.add("java programmer"); // 2
		keyword.add("java full stack developer"); // 3
		keyword.add("python developer"); // 4
		keyword.add("selenium tester"); // 5
		keyword.add("selenium automation engineer"); // 6
		keyword.add("apache maven"); // 7
		keyword.add("software developer"); // 8
		keyword.add("software engineer"); // 9
		keyword.add("software tester"); // 10
		keyword.add("sdet qa engineer"); // 11
		keyword.add("sdet test engineer"); // 12
		keyword.add("software development engineer"); // 13
		keyword.add("scrum master"); // 14
		keyword.add("java business analyst"); // 15
		keyword.add("java ui developer"); // 16
		keyword.add("automation tester"); // 17
		keyword.add("test automation engineer"); // 18
		keyword.add("ruby test automation"); // 19
		keyword.add("c# development and test engineer"); // 20

		String inputSearch = "";

		List<String> result = new ArrayList<>();

		for (int i = 0; i < keyword.size(); i++) {

			inputSearch = keyword.get(i);
			driver.findElement(By.id("search-field-keyword")).clear();
			driver.findElement(By.id("search-field-keyword")).sendKeys(inputSearch);

			String location = "22207";
			driver.findElement(By.id("search-field-location")).clear();
			driver.findElement(By.id("search-field-location")).sendKeys(location);

			driver.findElement(By.id("findTechJobs")).click();

			String count = driver.findElement(By.id("posiCountId")).getText();
			//System.out.println(count);

			// ensure the count is more than 0
			int countResult = Integer.parseInt(count.replaceAll(",", ""));

			if (countResult > 0) {
				System.out.println("STEP PASS : Keyword : " + inputSearch + " search returned " + countResult
						+ " results in " + location);
			} else {
				System.out.println("STEP FAIL : Keyword : " + inputSearch + " search returned " + countResult
						+ " results in " + location);
			}

			// result.add(inputSearch + "-" + count);
			result.add(inputSearch + "-" + countResult);

			driver.navigate().back();

		}
		driver.close();

		System.out.println("TEST COMPLETED " + LocalDateTime.now());

		System.out.println("RESULTS : " + result);

		// for(int i=0; i < keyword.size(); i++) {
		// System.out.println("result = " + result.get(i));
		// }

	}

}