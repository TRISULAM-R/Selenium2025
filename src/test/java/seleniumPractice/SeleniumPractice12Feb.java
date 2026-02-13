package seleniumPractice;

import Pages.SeleniumTrickySimulationPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SeleniumPractice12Feb {
    @Test
    public void handleChildWindow() {
        String expTitle = "New Window";
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://the-internet.herokuapp.com/windows");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        String parentWin = driver.getWindowHandle();
        driver.findElement(By.linkText("Click Here")).click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> allWin = driver.getWindowHandles();
        for (String s : allWin) {
            if (!s.equals(parentWin)) {
                driver.switchTo().window(s);
                break;
            }
        }
        String title = driver.getTitle();
        Assert.assertEquals(title, expTitle);
        driver.close();
        driver.quit();
    }
}
