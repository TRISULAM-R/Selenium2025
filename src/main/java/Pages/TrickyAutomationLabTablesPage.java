package Pages;

import Utils.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TrickyAutomationLabTablesPage
{

    Utility util = new Utility();

    private WebDriverWait wait;
    private Actions actions;

    @FindBy(xpath = "//table[@id='dup']/descendant::tbody/tr/td[2]")
    private List<WebElement> userNameList;

    @FindBy(xpath = "//table[@id='dup']/descendant::tbody/tr/td[3]")
    private List<WebElement> roleList;




    public TrickyAutomationLabTablesPage(WebDriver driver) {
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

    public List<String> getUserNameList()
    {
        List<String> list = new ArrayList<>();
        for(WebElement element : userNameList)
        {
           list.add( element.getText());
        }
        return list;
    }
    public List<String> getRoleList()
    {
        List<String> list = new ArrayList<>();
        for(WebElement element : roleList)
        {
            list.add( element.getText());
        }
        return list;
    }
}
