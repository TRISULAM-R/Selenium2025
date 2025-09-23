import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class SeleniumPractice
{
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testGoogleSearch() {
        driver.get("https://demo.guru99.com/test/web-table-element.php#");



        List<String> companyNames = new ArrayList<>();
        List<WebElement> companyList = driver.findElements(By.xpath("//table/tbody/descendant::a"));
        for (WebElement element:companyList)
        {
            companyNames.add(element.getText());
        }
        String companyName = "Central Bank";
        String expectedDetailsPageTitle = " Guru99 Bank Home Page ";
        if(companyNames.contains(companyName))
        {
            String currentPrice = driver.findElement(By.xpath("//a[contains(text(),'" + companyName + "')]/parent::td/following-sibling::td[3]")).getText();
            // Print Current Price
            System.out.println(companyName + " Current Price: " + currentPrice);
            driver.findElement(By.partialLinkText(companyName)).click();

            String actualPageTitle = driver.getTitle();
            Assert.assertEquals(expectedDetailsPageTitle, actualPageTitle);
        }else
        {
            System.out.println( companyName + " is not in the list");
        }
    }

//    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
