package Pages;

import Utils.Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TrickyAutomationLabHomePage
{

    Utility util = new Utility();

    private WebDriverWait wait;
    private Actions actions;

    @FindBy(linkText = "Tables")
    private WebElement tablesLink;




    public TrickyAutomationLabHomePage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Navigates to the page URL.
     */
    public void open(WebDriver driver, String url) {
        driver.get(url);
    }

    public void clickOnTablesLink()
    {
        tablesLink.click();
    }
}
