package test.facebook.post;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class facebookpost {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriver driver;
		
		Properties prop=new Properties();
		FileInputStream ip= new FileInputStream("src/resources/config.properties");
		
		prop.load(ip);

		//Creating an instance of chrome level class to disable browser level notifications
		ChromeOptions coptions = new ChromeOptions();
		coptions.addArguments("--disable-notifications");

		// Telling Selenium to find Chrome Driver
		System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver.exe");

		// Initialize browser
		driver = new ChromeDriver(coptions);
		

		// Launch Facebook
		driver.get("http://facebook.com/");

		//Wait
		Thread.sleep(1000);

		//Maximize Window
		driver.manage().window().maximize();

		//Wait
		Thread.sleep(2000);

		//Enter Username
		WebElement userTextField = driver.findElement(By.id("email"));
		userTextField.sendKeys(prop.getProperty("email"));

		//Wait
		Thread.sleep(2000);

		//Enter Password
		WebElement PassTextField = driver.findElement(By.id("pass"));
		PassTextField.sendKeys(prop.getProperty("password"));

		//Wait
		Thread.sleep(2000);

		//Click on Login button
		driver.findElement(By.name("login")).click();

		//Wait
		Thread.sleep(10000);

		//Click on What's on Your Mind?
		WebElement TextArea = driver.findElement(By.cssSelector("div[role=\"button\"] > div > span[style*=\"webkit-box-orient\"]"));
		TextArea.click();
		Thread.sleep(3000);

		//Click on the expanded text area
		WebElement TextAreaExpanded = driver.findElement(By.cssSelector("div[aria-describedby*=\"placeholder\"]"));
		TextAreaExpanded.click();
		TextAreaExpanded.sendKeys(prop.getProperty("post"));

		//Wait
		Thread.sleep(3000);

		//Click On Post Button
		WebElement PostBtn = driver.findElement(By.cssSelector("div[aria-label=\"Post\"]"));
		PostBtn.click();

		//Wait
		Thread.sleep(4000);

		// Take Screenshot for Evidence
		File srce = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// Save the screenshot in the given path by the name FbStatus.png
		FileUtils.copyFile(srce, new File("src/output/FbStatus.png"));

		driver.quit();

	}

}
