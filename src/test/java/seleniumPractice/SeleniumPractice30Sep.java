package seleniumPractice;

import Pages.SeleniumTrickySimulationPage;
import Utils.ExcelFileUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.*;

public class SeleniumPractice30Sep
{
    WebDriver driver;
    SeleniumTrickySimulationPage seleniumTrickySimulationPage = null;

    String url = "file:///C:/Users/User.TYSS-TEJAS/Downloads/selenium_tricky_challenges_html_css_simulation.html";  // change to your folder
    String downloadPath = "C:\\Users\\User.TYSS-TEJAS\\Downloads";  // change to your folder


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

        seleniumTrickySimulationPage = new SeleniumTrickySimulationPage(driver);

    }

    @Test(description = "Dynamic lists & stale elements")
    public void testDynamicListAndStaleElements() throws InterruptedException
    {
        String expectedStyle = "opacity: 0.5";
        seleniumTrickySimulationPage.open(url);
        seleniumTrickySimulationPage.clickAddItemBtn();
        String style = seleniumTrickySimulationPage.clickOnCompleteItemButton();
        System.out.println(style);
        Assert.assertTrue(style.contains(expectedStyle));

    }

    @Test(description = "Shadow DOM")
    public void testShadowDomElement() throws InterruptedException
    {
        String expectedValue = "Yo..! I'm Testing";
        seleniumTrickySimulationPage.open(url);
        seleniumTrickySimulationPage.enterValueInShadowRoot(expectedValue);
    }

    @Test(description = "Nested iFrames")
    public void testNestedIFrames() throws InterruptedException
    {
        String expectedValue = "Yo..! I'm Testing";
        seleniumTrickySimulationPage.open(url);
        seleniumTrickySimulationPage.enterValueInShadowRoot(expectedValue);
    }

    @Test(description = "Hidden file upload")
    public void testHiddenFileUpload() throws InterruptedException
    {
        String expectedValue = "";
        seleniumTrickySimulationPage.open(url);
        seleniumTrickySimulationPage.enterValueInShadowRoot(expectedValue);
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
