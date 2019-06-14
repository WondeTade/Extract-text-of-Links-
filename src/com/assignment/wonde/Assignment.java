package com.assignment.wonde;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Assignment {

	static WebDriver driver;
	static String browser = "Chrome"; 

	public static void main(String[] args) throws Exception {
		if (browser.equals("Chrome")){
		driver = new ChromeDriver();
		}else if (browser.equals("Mozila")){
			driver = new FirefoxDriver();
		}else if (browser.equals("InternetExplorerDriver"))
			driver = new InternetExplorerDriver();
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);

		driver.get("https://www.americangolf.co.uk/");

		// 1.Extract the text of all the Links under the main-menu bar:

		List<WebElement> linksList = driver.findElements(By
				.xpath("//div[@class='header-navigation-left']/ul/li/a"));

		System.out.println("Number of links " + linksList.size());
		
		for (WebElement link : linksList) {
			if(link.isDisplayed()){
			System.out.println(link.getText());
			}
		}
		// 2.Click on each link in the above Menu bar
		// – wait for corresponding page to be loaded.
		// Capture the title of the newly loaded page.

		System.out.println("\nList of titles:");
		//Alternative 1
		driver.findElement(
				By.xpath("//div [@id = 'header-top-wrapper']/div/div [@class = 'header-top-middle']/div/a"))
				.click();
		for (int i = 1; i <= linksList.size(); i++) {
			if (driver.findElement(By.xpath("//div [@id= 'header-navigation']/div[1]/ul/li["+ i + "]/a")).isDisplayed()) {
				
			driver.findElement(By.xpath("//div [@id= 'header-navigation']/div[1]/ul/li["+ i + "]/a")).click();
			System.out.println(i+"." + " " + driver.getTitle());
			driver.findElement(By.xpath("//div [@id = 'header-top-wrapper']/div/div [@class = 'header-top-middle']/div/a")).click();
			}else
				System.out.println(i + "." ); //prints the index of the empty element (index + 1)
		}
		//Alternative 2
		for (int i = 0; i < linksList.size(); i++){
			if (linksList.get(i).isDisplayed()){
				linksList.get(i).click();
				String title = driver.getTitle();
				System.out.println("Titel " + i +": " + title);
				driver.navigate().back();
				//restore the list, otherwise the list will be empty and cause failure 
				linksList = driver.findElements(By.xpath("//div[@class='header-navigation-left']/ul/li/a"));
			}
		}
		driver.close();
	}

}
