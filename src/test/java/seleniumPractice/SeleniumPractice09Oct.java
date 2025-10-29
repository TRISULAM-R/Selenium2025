package seleniumPractice;

import Pages.QAGauntletHomePage;
import Pages.TrickyAutomationLabHomePage;
import Pages.TrickyAutomationLabTablesPage;
import Utils.Utility;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.*;

public class SeleniumPractice09Oct
{
    public static WebDriver driver;
    TrickyAutomationLabTablesPage trickyAutomationLabTablesPage = null;
    TrickyAutomationLabHomePage trickyAutomationLabHomePage = null;

    Utility util = new Utility();

    String url = "file:///C:/Users/User.TYSS-TEJAS/Downloads/tricky_automation_lab.html";
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

        trickyAutomationLabTablesPage = new TrickyAutomationLabTablesPage(driver);
        trickyAutomationLabHomePage = new TrickyAutomationLabHomePage(driver);

    }

    @Test(description = "Read the Name and Role cells of each row & Verify that each row contains correct data")
    public void testUserNamesListIsDisplayed() throws InterruptedException
    {
        List<String> expectedUserList = Arrays.asList("User 1", "User 2", "User 3", "User 4", "User 5");
        List<String> expectedRoleList = Arrays.asList("Engineer", "Engineer", "Engineer", "Engineer","Engineer");
        trickyAutomationLabHomePage.open(driver, url);
        trickyAutomationLabHomePage.clickOnTablesLink();
        List<String> userList = trickyAutomationLabTablesPage.getUserNameList();
        List<String> roleList = trickyAutomationLabTablesPage.getRoleList();
        if(expectedUserList.size() == userList.size())
        {
            for (int i = 0; i < userList.size(); i++)
            {
                System.out.println(userList.get(i)+" -> "+roleList.get(i));
                Assert.assertEquals(userList.get(i), expectedUserList.get(i));
                Assert.assertEquals(roleList.get(i), expectedRoleList.get(i));
            }
        } else {
            Assert.fail("Expected and Actual User List is not matching");
        }
    }


    @AfterClass
    public void tearDown() throws InterruptedException {

        Thread.sleep(3000);
        if (driver != null) {
            driver.quit();
        }
    }
}
