package test.review.ratting;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class reviewRatting {

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
		
		driver.get("https://wallethub.com/join/light");
		
		//Wait
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//*[@id='join-light']/form/div/nav/ul/li[2]/a")).click();
		driver.findElement(By.name("em")).sendKeys(prop.getProperty("uname"));
		driver.findElement(By.name("pw1")).sendKeys(prop.getProperty("pwd") + Keys.ENTER);
		
		
		//Wait
		Thread.sleep(3000);

		// Launch Insurance Site
		driver.navigate().to("https://wallethub.com/profile/test_insurance_company");

		//Wait
		Thread.sleep(1000);
		
		//click on Review tab
		driver.findElement(By.xpath("//*[@class='nav-link semi-bold-font-weight'][1]")).click();
		
		//Wait
		Thread.sleep(2000);
		
		//hover to rating star
		Actions action = new Actions(driver);
		WebElement we = driver.findElement(By.cssSelector("#reviews-section > div.review-stat-box > div.rv.review-action.ng-enter-element > review-star > div > svg:nth-child(4)"));
		action.moveToElement(we).build().perform();
		
		Thread.sleep(2000);
		
		//click on forth star
		driver.findElement(By.cssSelector("#reviews-section > div.review-stat-box > div.rv.review-action.ng-enter-element > review-star > div > svg:nth-child(4)")).click();
		
		//wait for page to redirect
		Thread.sleep(5000);
		
		//Select Dropdown
		driver.findElement(By.xpath("//*[@id=\"reviews-section\"]/modal-dialog/div/div/write-review/div/ng-dropdown/div/span")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='reviews-section']/modal-dialog/div/div/write-review/div/ng-dropdown/div/ul/li[2]")).click();

		Thread.sleep(2000);
		
		//fill review comment
		driver.findElement(By.xpath("//*[@id=\"reviews-section\"]/modal-dialog/div/div/write-review/div/div[1]/textarea")).sendKeys(prop.getProperty("reviewcomment"));

		//Click on Submit
		driver.findElement(By.xpath("//*[@id=\"reviews-section\"]/modal-dialog/div/div/write-review/sub-navigation/div/div[2]")).click();
		
		Thread.sleep(3000);
		
		//verify review
		String reviewcomment = driver.findElement(By.xpath("//*[@id=\"reviews-section\"]/section/article[contains(.,'@krisnap914')]/div[4]")).getText();
		if(reviewcomment == prop.getProperty("reviewcomment"))
		{
			System.out.println("review added successfully");
		}
		else
		{
			System.out.println("review not added");
		}
		
		
	}

}
