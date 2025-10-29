package seleniumPractice;

import Pages.SeleniumTrickySimulationPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class SeleniumPractice15Oct
{


    public static void main(String[] args) throws Exception
    {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("file:///C:/Users/User.TYSS-TEJAS/Downloads/webpage_gauntlet%20(1).html");
        WebElement iframeHost = driver.findElement(By.id("lab-iframe"));
        driver.switchTo().frame(iframeHost);
        driver.findElement(By.id("lab-input")).sendKeys("YES");
        WebElement confirmBtn=driver .findElement(By.id("confirm"));
        if(confirmBtn.isDisplayed())
        {
            confirmBtn.click();
            wait.until(ExpectedConditions.alertIsPresent());

            Alert alert = driver.switchTo().alert();
            String alertMsg = alert.getText();
            System.out.println(alertMsg);
            alert.accept();
            writeDataIntoExcel(1, 0, "YES", alertMsg);
        } else {
//            writeDataIntoExcel(1, 0, "NO", );
        }
        driver.quit();

    }

    public static void writeDataIntoExcel(int rowNum, int cellNum, String value, String response) throws IOException
    {
        Workbook wb = null;
        try {
        String str = "C:\\Users\\User.TYSS-TEJAS\\Downloads\\Selenium2025\\Selenium2025\\src\\test\\resources\\testData\\TestData4.xlsx";
            FileInputStream fis = new FileInputStream(str);
            wb = new XSSFWorkbook();
            Sheet sh = wb.getSheet("sheet1");
            int cellCount = sh.getRow(0).getPhysicalNumberOfCells();
            System.out.println(cellCount);
            int lastPhysicalRow = sh.getPhysicalNumberOfRows();
            System.out.println(lastPhysicalRow);
            Row row = sh.createRow(lastPhysicalRow);
            row.createCell(0).setCellValue(value);
            row.createCell(1).setCellValue(response);

//        sh.createRow(rowNum).createCell(cellNum).setCellValue(value);


        FileOutputStream fileOutputStream = new FileOutputStream(str);
        wb.write(fileOutputStream);
        fileOutputStream.close();
    }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally{
        wb.close();
    }
    }
}
