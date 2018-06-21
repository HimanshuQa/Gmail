import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.Properties;

import javax.mail.*;

public class NewTest {
	WebDriver driver;

	public NewTest() {
		// TODO Auto-generated constructor stub

	}

	@Test
	public void f() {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		// driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		// driver.manage().window().maximize();

		driver.get("https://mail.google.com/mail/u/0/#inbox");

		// gmail login
		driver.findElement(By.id("identifierId")).sendKeys("himanshuqa4490");
		driver.findElement(By.cssSelector("#identifierNext > content > span")).click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("#password > div.aCsJod.oJeWuf > div > div.Xb9hP > input")));
		driver.findElement(By.cssSelector("#password > div.aCsJod.oJeWuf > div > div.Xb9hP > input"))
				.sendKeys("Himanshu@321#");
		driver.findElement(By.cssSelector("#passwordNext > content")).click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.tagName("tr")));	
		

		JavascriptExecutor js=(JavascriptExecutor)driver;

		System.out.println("asd" + js.executeScript("return $('document').querySelector(('a'))[41].text;"));

		try {
			
			js.executeScript("return document.querySelector('tr.zA.zE').click();");
			
			
		} catch (Exception e) {
			
			System.out.println("No unread emails");
		}
		
		// driver.findElement(By.id("gb23")).click();

		// // now talking un-read email form inbox into a list
		// List<WebElement> unreademeil =
		// driver.findElements(By.xpath("//*[@id=':3z']/span"));
		// System.out.println("asd" + driver.findElement(By.cssSelector("#\3a 3z
		// > span")).getText());
		//
		// // Mailer name for which i want to check do i have an email in my
		// inbox
		// String MyMailer = "Udacity";
		//
		// // real logic starts here
		// for(int i=0;i<unreademeil.size();i++){
		// if(unreademeil.get(i).isDisplayed()==true){
		// // now verify if you have got mail form a specific mailer (Note
		// Un-read mails)
		// // for read mails xpath loactor will change but logic will remain
		// same
		// if(unreademeil.get(i).getText().equals(MyMailer)){
		// System.out.println("Yes we have got mail form " + MyMailer);
		// // also you can perform more actions here
		// // like if you want to open email form the mailer
		// break;
		// }else{
		// System.out.println("No mail form " + MyMailer);
		// }
		// }
		//
	}

	@BeforeClass
	public void afterClass() {
		System.setProperty("webdriver.chrome.driver", "/home/himanshuchaudhary/Downloads/chromedriver");
		driver = new ChromeDriver();
	}

	@AfterClass
	public void afterTest() throws InterruptedException {
		// Thread.sleep(10000);
		driver.close();
	}

}
