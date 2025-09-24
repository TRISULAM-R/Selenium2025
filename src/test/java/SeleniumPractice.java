import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SeleniumPractice
{
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(description = "Automation #1")
    public void testLinkTextOfGivenCompany() {
        driver.get("https://demo.guru99.com/test/web-table-element.php#");

        List<String> companyNames = driver.findElements(By.xpath("//table/tbody/descendant::a"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        System.out.println(companyNames);

        String companyName = "IDFC Bank";
        String expectedDetailsPageTitle = "Guru99 Bank Home Page";
        if(companyNames.contains(companyName))
        {
            String currentPrice = driver.findElement(By.xpath("//a[contains(text(),'" + companyName + "')]/parent::td/following-sibling::td[3]")).getText();
            // Print Current Price
            System.out.println(companyName + " Current Price: " + currentPrice);
            driver.findElement(By.partialLinkText(companyName)).click();

            String actualPageTitle = driver.getTitle().trim();
            Assert.assertEquals(expectedDetailsPageTitle, actualPageTitle);
        }else
        {
            System.out.println( companyName + " is not in the list");
        }
    }

    @Test(description = "Automation #2")
    public void testBrokenLinks() {
        driver.get("https://the-internet.herokuapp.com/");

        List<WebElement> linkElements = driver.findElements(By.xpath("//a | //img"));

        List<String> links= new ArrayList<>();
        SoftAssert softAssert = new SoftAssert();
        for(WebElement linkElement : linkElements)
        {
            String tag = linkElement.getTagName();
            String href = linkElement.getAttribute("href");
            String src = linkElement.getAttribute("src");
            links.add(tag.equals("a") ? href : src);
        }
        checkBrokenLinks(links, softAssert);
    }

    @Test(description = "Automation #3")
    public void testNestedIFrame() throws InterruptedException
    {
        driver.get("https://selectorshub.com/iframe-in-shadow-dom/");

        String userNameText = "Naveen";

        WebElement cusor = driver.findElement(By.className("elementor-widget-container"));
        Actions action = new Actions(driver);
        action.moveToElement(cusor).perform();
        WebElement shadowHost = driver.findElement(By.id("userName"));

        SearchContext rootElement =shadowHost.getShadowRoot();
        rootElement.findElement(By.cssSelector("input[id='kils']")).sendKeys(userNameText);
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        WebElement shadowRoot = (WebElement) js.executeScript("return document.querySelector(\"#userName\")", shadowHost);
//        WebElement innerElement = (WebElement) js.executeScript(
//                "return arguments[0].querySelector('#kils')", shadowRoot);
//
//        shadowRoot.click();
//        shadowRoot.sendKeys(userNameText);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Thread.sleep(2000);
        WebElement frame= driver.findElement(By.xpath("//iframe[@id='pact1']"));
        wait.until(ExpectedConditions.elementToBeClickable(frame));
        driver.switchTo().frame(frame);
        driver.findElement(By.id("glaf")).sendKeys("Chennai");
        driver.switchTo().parentFrame();

        driver.findElement(By.id("app2")).getShadowRoot()
                .findElement(By.cssSelector("div[id='concepts']"))
                .sendKeys("Margareeta");


    }

    public void checkBrokenLinks(List<String> links, SoftAssert softAssert)
    {
        for(String url : links)
        {
            try
            {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.connect();
                int responseCode = connection.getResponseCode();

                if (responseCode >= 400)
                {
                    System.out.println("❌ Broken link: " + url + " — Response Code: " + responseCode);
                } else
                {
                    System.out.println("✅ Valid link: " + url + " — Response Code: " + responseCode);
                }

            } catch (Exception e)
            {
                System.out.println("⚠️ Exception for URL: " + url + " — " + e.getMessage());
            }
            softAssert.assertAll();
        }
    }
//    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
