package seleniumPractice;

import Utils.ExcelFileUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
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
import java.util.NoSuchElementException;

public class AutomationPractice
{
    WebDriver driver;

    String downloadPath = "C:\\Users\\User.TYSS-TEJAS\\Downloads";  // change to your folder



    @BeforeClass
    public void setup() {


        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadPath);
        prefs.put("download.prompt_for_download", false);
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("profile.default_content_setting_values.geolocation", 2);
        prefs.put("safebrowsing.enabled", false);  // disables Chrome safe browsing
        prefs.put("safebrowsing.disable_download_protection", true);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-notifications");
        options.addArguments("--ignore-certificate-errors");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

    }

    @Test(description = "Open app and click on checkbox")
    public void clickOnCheckBox() throws InterruptedException
    {
        driver.get("https://testautomationpractice.blogspot.com/");
        WebElement paginationTable = driver.findElement(By.id("HTML8"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();",paginationTable);
        String s = "Soundbar";

        boolean flag = false;
        for(int i=1;;i++)
        {
            List<WebElement> elements = driver.findElements(By.xpath("//*[@id='productTable']/tbody/tr/td[2]"));
            for (WebElement ele : elements)
            {
                if (s.equalsIgnoreCase(ele.getText()))
                {
                    driver.findElement(By.xpath("//*[@id='productTable']/tbody/tr/td[contains(text(),'" + s + "')]/following-sibling::td/input")).click();
                    String amount = driver.findElement(By.xpath("//*[@id='productTable']/tbody/tr/td[contains(text(),'" + s + "')]/following-sibling::td[1]")).getText();
                    System.out.println("amount : " + amount);
                    flag = true;
                    break;
                }
            }
            if(flag)
            {
                break;
            }
            try
            {
                driver.findElement(By.xpath("//*[@id='pagination']/li["+(i+1)+"]/a")).click();
            } catch (Exception e)
            {
                System.out.println("No element found in the page");
                break;
            }
        }
        Thread.sleep(4000);

    }

    @Test(description = "Open app and click on checkbox")
    public void clickOnCheckBox2() throws InterruptedException
    {
        driver.get("https://testautomationpractice.blogspot.com/");
        WebElement paginationTable = driver.findElement(By.id("HTML8"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();",paginationTable);
        String s = "Wireless Mouse 20";
        boolean found = false;

        int i =2;
        while (true) {
            List<WebElement> rows = driver.findElements(By.xpath("//*[@id='productTable']/tbody/tr"));

            for (WebElement row : rows) {
                WebElement productCell = row.findElement(By.xpath("./td[2]"));
                if (s.equalsIgnoreCase(productCell.getText())) {
                    // Click the checkbox/input in the same row
                    row.findElement(By.xpath("./td/input")).click();

                    // Get the amount from the next column
                    String amount = row.findElement(By.xpath("./td[3]")).getText();
                    System.out.println("amount : " + amount);

                    found = true;
                    break;
                }
            }

            if (found) break;

            // Try to go to the next page
            try {
                WebElement nextPage = driver.findElement(By.xpath("//*[@id='pagination']//a[text()='"+ (i++) +"']"));
                nextPage.click();
            } catch (Exception e) {
                System.out.println("Reached last page, product not found.");
                break;
            }
        }

        Thread.sleep(4000);

    }

    @Test(description = "Open app and click on checkbox")
    public void hdfcBank() throws InterruptedException
    {
        String month = "December";
        String date = "6";
        driver.get("https://paytm.com/");
        driver.findElement(By.id("departureDate")).click();
        try
        {
            driver.findElement(By.xpath("//td[contains(text(),'" + month + "')]/ancestor::table/descendant::div[text()='" + date + "']/parent::div[contains(@class,'calendar__day') and not(contains(@class,'calendar__dayFromOtherMonth')) and not(contains(@class,'calendar__disabledDay'))]")).click();

        } catch (ElementNotInteractableException e)
        {
            driver.findElement(By.xpath("//i[@class=\"fCLnC _1MMxa\"]")).click();
        }
        catch (NoSuchElementException e)
        {
            System.out.println("Date cannot be selected : " +month +" "+ date);
        }

        Thread.sleep(4000);
        ////td[contains(text(),'Novem')]/ancestor::table/descendant::div[text()='6']/parent::div[contains(@class,'calendar__day') and not(contains(@class,'calendar__dayFromOtherMonth')) and not(contains(@class,'calendar__disabledDay'))]
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
