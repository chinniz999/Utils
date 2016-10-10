package testCasePack;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utils.ErrorCollector;
import utils.MailwithAttachment;

public class Req_12345 {

	WebDriver driver;

	@BeforeTest
	public void init() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.google.co.in");
		//driver.get("C:\\Users\\user\\Desktop\\Google.html");
	}

	@Test(description = "Verify Title", priority = 0)
	public void verifyURL() {

		String actual = driver.getTitle();
		ErrorCollector.verifyEquals(actual, "Googe");
		ErrorCollector.verifyTrue(false, "'This is Message'------>");

	}

	@Test(description = "Verify URL", priority = 1)
	public void getCurrentURL() {
		String actual = driver.getCurrentUrl();
		ErrorCollector.verifyEquals(actual, "URL");
		ErrorCollector.verifyTrue(false, "Message---->");
	}

	@AfterTest
	public void end() {

		driver.close();
	}
	
	@AfterSuite
	public void teardown(){
		MailwithAttachment attachment=new MailwithAttachment();
		attachment.sendMailwithAttachmnet();
	}

}
