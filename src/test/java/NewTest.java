
import com.google.common.base.CharMatcher;
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
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;

public class NewTest {

    WebDriver driver;

    public NewTest() {
        // TODO Auto-generated constructor stub

    }

    @Test
    public void f() throws InterruptedException {
        String count;
        int before_count, after_count, n = 0;

        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebDriverWait inboxwait = new WebDriverWait(driver, 2);
        

        // driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // driver.manage().window().maximize();
        driver.get("https://mail.google.com/mail/u/0/#inbox");

        // gmail login
        driver.findElement(By.id("identifierId")).sendKeys("USERNAME");
        driver.findElement(By.cssSelector("#identifierNext > content > span")).click();
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("#password > div.aCsJod.oJeWuf > div > div.Xb9hP > input")));
        driver.findElement(By.cssSelector("#password > div.aCsJod.oJeWuf > div > div.Xb9hP > input"))
                .sendKeys("PASSWORD");
        driver.findElement(By.cssSelector("#passwordNext > content")).click();
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.tagName("tr")));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        RemoteWebElement next_page_button = (RemoteWebElement) js.executeScript("return document.querySelector(('img.amJ.T-I-J3'));");
        System.out.println("opacity " + next_page_button.getCssValue("opacity"));

        if (!js.executeScript("return document.querySelector(('a.J-Ke.n0')).text;").equals("Inbox")) {

            count = CharMatcher.digit().retainFrom(js.executeScript("return document.querySelector(('a.J-Ke.n0')).text;").toString());
            before_count = Integer.parseInt(count);
            System.out.println("before " + before_count);
            while (n == 0) {
                try {
                    
                    inboxwait.until(ExpectedConditions.jsReturnsValue("return document.querySelector('tr.zA.zE');"));
                    js.executeScript("return document.querySelector('tr.zA.zE').click();");
                    n = 1;
                } catch (Exception e) {
                    if (next_page_button.getCssValue("opacity").equals("0.55")) {
                        next_page_button.click();
                    } else {
                        System.out.println("No unread emails and this is last page");
                        n = 1;
                    }

                }

            }
            Thread.sleep(1000); //wait so that inbox count can be changed
            count = CharMatcher.digit().retainFrom(js.executeScript("return document.querySelector(('a.J-Ke.n0')).text;").toString());
            if (!count.isEmpty()) {
                after_count = Integer.parseInt(count);
            } else {
                after_count = 0;
            }
            System.out.println("after " + after_count);

            if (after_count > before_count || after_count == before_count) {
                Assert.fail("Inbox is not updated");
            }

        } else {
            System.out.println("NO unread emails");
        }

    }

    @BeforeClass
    public void afterClass() {
//		System.setProperty("webdriver.chrome.driver", "/home/himanshuchaudhary/Downloads/chromedriver");
        driver = new ChromeDriver();
    }

    @AfterClass
    public void afterTest() throws InterruptedException {
        // Thread.sleep(10000);
        driver.close();
    }

}
