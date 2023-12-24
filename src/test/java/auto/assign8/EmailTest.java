package auto.assign8;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Time;
import java.time.Duration;

public class EmailTest {
    WebDriver driver;

    @Test
    public void accEmail(){
        //open web url
        WebDriverManager.edgedriver();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://yopmail.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ycptcpt")));

        // type 'automationtest' inside the input box
        driver.findElement(By.id("login")).sendKeys("automationtest");
        driver.findElement(By.xpath("//button[@class='md']")).click();

        // assert for new page
        String succActualLogin = driver.findElement(By.xpath("//div[@class='bname']")).getText();
        String succExpectLogin = "automationtest@yopmail.com";
        Assert.assertEquals(succActualLogin,succExpectLogin);

        // extract e-mail message
        driver.switchTo().frame("ifmail");
        String textIframe = driver.findElement(By.xpath("//span[@style='font-size: 14px']")).getText();
        System.out.println(textIframe);
        new WebDriverWait(driver, Duration.ofSeconds(10));

        // close browser
        driver.quit();
    }
}
