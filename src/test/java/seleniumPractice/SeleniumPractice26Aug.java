package seleniumPractice;

import Utils.ExcelFileUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.groovy.json.internal.IO;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class SeleniumPractice26Aug
{
    WebDriver driver;

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

    }

    @Test(description = "Task 1: Shadow DOM Handling")
    public void testChromeDownloadsHandlingShadowRoot() throws InterruptedException
    {

        driver.get("https://demo.automationtesting.in/FileDownload.html");
        driver.findElement(By.xpath ("//a[@type='button']")).click();
        Thread.sleep(1000);
        driver.navigate().to ("chrome://downloads/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement downloadsItem = (WebElement) js.executeScript(
                "return document.querySelector(\"body > downloads-manager\").shadowRoot.querySelector(\"#frb0\").shadowRoot.querySelector(\"#file-link\")"
        );
        String element = downloadsItem.getText();
        System.out.println("last downloaded file name : "+ element);
        WebElement clearAllButton = (WebElement)js.executeScript("return document.querySelector(\"body > downloads-manager\").shadowRoot.querySelector(\"#toolbar\").shadowRoot.querySelector(\"#clearAll\")");
        clearAllButton.click();
        System.out.println("======= Clear All button clicked successfully. =======");
    }

    @DataProvider(name = "loginCredentials")
    public Object[][] loginData()
    {
        String filePath = "src/test/resources/testData/TestData1.xlsx";
        String sheetName = "Sheet1";
        return ExcelFileUtility.getData(filePath, sheetName);
    }
    @Test(description = "Task 2: Read & Write Excel Data",dataProvider = "loginCredentials")
    public void testReadDataFromExcel(String userName, String password) throws InterruptedException
    {
        String filePath = "src/test/resources/testData/TestData2.xlsx";
        String sheetName = "Sheet1";


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        String expectedTitle = "OrangeHRM";
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        WebElement userNameField = driver.findElement(By.name ("username"));
        wait.until(ExpectedConditions.elementToBeClickable(userNameField));
        userNameField.sendKeys(userName);
        driver.findElement(By.xpath ("//input[@name='password']")).sendKeys(password);
        WebElement loginBtn = driver.findElement(By.cssSelector("button[type='submit']"));
        wait.until(ExpectedConditions.visibilityOf(loginBtn));
        loginBtn.click();
       try{
           WebElement invalidCredElement = driver.findElement(By.xpath("//h6[text()='Dashboard']"));
           if(invalidCredElement.isDisplayed())
           {
               System.out.println(driver.getTitle());
               System.out.println(driver.getCurrentUrl());
               Assert.assertEquals(expectedTitle, driver.getTitle());
               driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click();
               driver.findElement(By.xpath("//a[contains(@href,'logout')]")).click();

               String status = "PASS";
               ExcelFileUtility.writeStatus(filePath, sheetName, userName, password, status);
           }
       } catch (Exception e)
       {
           String status = "FAIL";
           ExcelFileUtility.writeStatus(filePath, sheetName, userName, password, status);
       }

    }

    @Test(description = "Automation #4")
    public void testLinkTextOfGivenCompany() {
        driver.get("https://demo.guru99.com/test/web-table-element.php#");

        String companiesNameXpath ="//table/tbody/descendant::a";
        Map<String, String> map = new LinkedHashMap<>();
        List<WebElement> companyNames = driver.findElements(By.xpath(companiesNameXpath));
        System.out.println(companyNames);

        for(WebElement companyName : companyNames)
        {
            String comName = companyName.getText().trim();
            String xpath = "//a[contains(text(),'"+comName+"')]/parent::td/following-sibling::td[3]";
            map.put(comName, driver.findElement(By.xpath(xpath)).getText());
        }
        System.out.println(map);

        String filePath = "src/test/resources/testData/TestData2.xlsx";
        String sheetName = "Sheet2";

        for(Map.Entry<String, String> entries : map.entrySet())
        {
            String companyName = entries.getKey();
            String currentPrice = entries.getKey();
            System.out.println(entries.getKey()+" : " +entries.getValue());
            ExcelFileUtility.writeStatus(filePath,sheetName,companyName, currentPrice, "");
        }
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
