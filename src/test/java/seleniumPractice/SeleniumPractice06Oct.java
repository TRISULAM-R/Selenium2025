package seleniumPractice;

import Pages.QAGauntletHomePage;
import Pages.SeleniumTrickySimulationPage;
import Utils.Utility;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class SeleniumPractice06Oct
{
    public static WebDriver driver;
    QAGauntletHomePage qAGauntletHomePage = null;

    Utility util = new Utility();

    String url = "file:///C:/Users/User.TYSS-TEJAS/Downloads/webpage_gauntlet%20(1).html";
    String downloadPath = "C:\\Users\\User.TYSS-TEJAS\\Downloads";


    @BeforeClass
    public void setup() {


        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadPath);
        prefs.put("download.prompt_for_download", false);
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("safebrowsing.enabled", false);  // disables Chrome safe browsing
        prefs.put("safebrowsing.disable_download_protection", true);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        qAGauntletHomePage = new QAGauntletHomePage(driver);

    }

    @Test(description = "Launch the “QA Gauntlet” page and verify the page title")
    public void testHomePageIsDisplayed() throws InterruptedException
    {
        String expectedPageTitle = "The QA Gauntlet — Complex Test Page";
        qAGauntletHomePage.open(driver, url);
        String actualPageTitle = driver.getTitle();
        System.out.println(actualPageTitle);
        Assert.assertEquals(expectedPageTitle, actualPageTitle);
    }

    @Test(description = "Locate and click on a hidden or shadow DOM element using JavaScriptExecutor. Advanced DOM handling.")
    public void testShadowDomHandling() throws InterruptedException
    {
        //document.querySelector("#shadow-host").shadowRoot.querySelector("#shBtn")
        int count= 5;
        String expectedCountText = "Clicks: ";
        qAGauntletHomePage.open(driver, url);
        for(int i=0; i<count; i++)
        {
            qAGauntletHomePage.clickOnShadowElement(driver);
        }
        String countText = qAGauntletHomePage.getClickCount(driver);
        System.out.println(countText);
        Assert.assertEquals(expectedCountText + count, countText);
    }

    @Test(description = "")
    public void testIFrameElement() throws InterruptedException
    {
        String expectedString = "YES";
        qAGauntletHomePage.open(driver, url);
        qAGauntletHomePage.enterValueInIFrameElement(driver, expectedString);
    }

    @Test(description = "Perform hover action over a button and verify the tooltip")
    public void testDragAndDrop() throws InterruptedException
    {
        String expectedString = "Dropped: BLUE";
        qAGauntletHomePage.open(driver, url);
        String value = qAGauntletHomePage.dragAndDropBlueBtn(driver);
        System.out.println(value);
        Assert.assertEquals(expectedString, value);
    }

////tbody[@id='dyn-body']/tr

    @Test(description = "Validate a dynamic table")
    public void testDynamicTable() throws InterruptedException
    {
        String expectedString = "Dropped: BLUE";
        qAGauntletHomePage.open(driver, url);
        qAGauntletHomePage.extractValuesFromTable(driver);
    }

//    @AfterClass
    public void tearDown() throws InterruptedException {

        Thread.sleep(5000);
        if (driver != null) {
            driver.quit();
        }
    }
}
