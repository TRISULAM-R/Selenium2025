package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SeleniumTrickySimulationPage
{
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    // 1. Element Locators
    @FindBy(id = "add-item")
    private WebElement addItemBtn;

    @FindBy(className = "todo-item")
    private List<WebElement> toDoItemElement;

    @FindBy(xpath = "//div[@id='todo-list']/div/descendant::button[contains(text(),'Complete')]")
    private List<WebElement> completeItemButton;

    @FindBy(id = "shadow-host")
    private WebElement shadowHostElement;

    @FindBy(id = "result")
    private WebElement resultText;

    String shadowInput = "[id='shadow-input']";


    public SeleniumTrickySimulationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Navigates to the page URL.
     */
    public void open(String url) {
        driver.get(url);
    }

    /**
     * Clicks the Start button to launch the simulation.
     */
    public void clickAddItemBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(addItemBtn)).click();
    }

    public String clickOnCompleteItemButton()
    {
        wait.until(ExpectedConditions.elementToBeClickable(completeItemButton.get(0))).click();
        wait.until(ExpectedConditions.elementToBeClickable(toDoItemElement.get(0))).getCssValue("style");
        return wait.until(ExpectedConditions.elementToBeClickable(toDoItemElement.get(0))).getAttribute("style");
    }

    public void enterValueInShadowRoot(String value)
    {
        SearchContext element = wait.until(ExpectedConditions.elementToBeClickable(shadowHostElement)).getShadowRoot();
        element.findElement(By.cssSelector(shadowInput)).sendKeys(value);
    }
}
