package Utils;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Utility
{
    public WebElement getShadowRootElementUsingJS(WebDriver driver, String script, WebElement element)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (WebElement) js.executeScript(script, element);
    }

    public WebDriver switchToIFrame(WebDriver driver, WebElement element)
    {
        return driver.switchTo().frame(element);
    }
}

