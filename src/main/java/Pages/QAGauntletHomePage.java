package Pages;

import Utils.Utility;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class QAGauntletHomePage
{

    Utility util = new Utility();

    private WebDriverWait wait;
    private Actions actions;

    @FindBy(id = "shadow-host")
    private WebElement shadowHostElement;

    @FindBy(id = "shCount")
    private WebElement shadowClickCount;

    @FindBy(id = "lab-iframe")
    private WebElement iFrameElement;

    @FindBy(id = "lab-input")
    private WebElement iFrameInputFld;

    @FindBy(id = "confirm")
    private WebElement iFrameConfirmBtn;

    @FindBy(css = "*[data-color='blue']")
    private WebElement blueBtn;

    @FindBy(id = "drop-target")
    private WebElement dropTarget;

//    @FindBy(id = "//tbody[@id='dyn-body']/tr/td[2]")
//    private List<WebElement> tableElements;



    public QAGauntletHomePage(WebDriver driver) {
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

    /**
     * Clicks the Start button to launch the simulation.
     */
    public void clickOnShadowElement(WebDriver driver) {
        WebElement webElement = util.getShadowRootElementUsingJS(driver, "return arguments[0].shadowRoot.querySelector('#shBtn')", shadowHostElement);
        webElement.click();
    }

    public String getClickCount(WebDriver driver)
    {
        WebElement countElement = util.getShadowRootElementUsingJS(driver, "return arguments[0].shadowRoot.querySelector('#shCount')", shadowHostElement);
        return countElement.getText();
    }

    public void enterValueInIFrameElement(WebDriver driver, String value)
    {
        util.switchToIFrame(driver, iFrameElement);
        wait.until(ExpectedConditions.visibilityOf(iFrameInputFld)).sendKeys(value);
        clickOnIFrameElementConfirm();
//        String text = driver.switchTo().alert().getText();
//        System.out.println(text);
    }

    public void clickOnIFrameElementConfirm()
    {
        wait.until(ExpectedConditions.elementToBeClickable(iFrameConfirmBtn)).click();
    }

    public String dragAndDropBlueBtn(WebDriver driver)
    {
        Actions action = new Actions(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return arguments[0].scrollIntoView(true)",blueBtn);
        action.dragAndDrop(blueBtn,dropTarget).perform();
        return dropTarget.getText();

    }

    public void extractValuesFromTable(WebDriver driver)
    {
        List<WebElement> tableElements =  driver.findElements(By.xpath("//tbody[@id='dyn-body']/tr/td[2]"));
        for(WebElement ele : tableElements)
        {
            System.out.println(ele.getText());
        }
    }

}
