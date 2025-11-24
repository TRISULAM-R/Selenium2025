package seleniumPractice;

import Pages.SeleniumTrickySimulationPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;
import java.time.Duration;
import java.util.*;

public class EyGrooming
{
    WebDriver driver;
    SeleniumTrickySimulationPage seleniumTrickySimulationPage = null;

    String url = "https://testautomationpractice.blogspot.com/";
    String selectorsHuburl = "https://selectorshub.com/xpath-practice-page/";
    String candyMapperUrl = "https://candymapper.com/";
    String paytmUrl = "https://paytm.com/";
    String downloadPath = "C:\\Users\\User.TYSS-TEJAS\\Downloads";  // change to your folder


    @BeforeClass
    public void setup()
    {


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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @Test(description = "Pagination")
    public void checkElementAndPrintItInConsole() throws InterruptedException
    {
        driver.get(url);
        WebElement scrollEle = driver.findElement(By.id("HTML8"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", scrollEle);

        String str = "Router";
        String checkboxXpath = "//table[@id='productTable']/tbody/tr/td[contains(text(),'" + str + "')]/following-sibling::td/input";
        String priceXpath = "//table[@id='productTable']/tbody/tr/td[contains(text(),'" + str + "')]/following-sibling::td[1]";

        List<WebElement> pagination = driver.findElements(By.xpath("//ul[@id='pagination']/descendant::a"));
        boolean flag = false;
        for (int i = 1; i <= pagination.size(); i++)
        {
            List<WebElement> elements = driver.findElements(By.xpath("//table[@id='productTable']/tbody/tr/td[2]"));
            for (WebElement ele : elements)
            {
                String sr = ele.getText();
                if (str.equalsIgnoreCase(sr))
                {
                    driver.findElement(By.xpath(checkboxXpath)).click();
                    String value = driver.findElement(By.xpath(priceXpath)).getText();
                    System.out.println(value);
                    flag = true;
                    break;
                }
            }
            if (flag)
            {
                break;
            } else
            {
                try
                {
                    driver.findElement(By.xpath("//ul[@id='pagination']/descendant::a[text()='" + (i + 1) + "']")).click();
                } catch (Exception e)
                {
                    System.out.println("Given Element is not found in the table");
                }
            }
        }
    }


    @Test
    public void compareList() throws IOException
    {
        String path = "C:\\Users\\User.TYSS-TEJAS\\Downloads\\Selenium2025\\Selenium2025\\src\\test\\resources\\testData\\TestDataEy.xlsx";

        List<String> list1 = fetchDataFromExcel(path);
        List<String> list2 = returnSortedList(list1);

        System.out.println(list1);
        System.out.println(list2);

        System.out.println("The given List is sorted : " + checkSame(list1, list2));
    }

    @Test
    public void selectorsHub() throws IOException
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(selectorsHuburl);
        WebElement scrollElement = driver.findElement(By.xpath("//h6[contains(text(),'iframe and table')]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", scrollElement);
        String xpath = "(//table[@id='tablepress-1']/descendant::td/input)[5]";

        driver.findElement(By.xpath(xpath)).click();
    }

    @Test
    public void canddyMapperDotCom()
    {
        driver.get(candyMapperUrl);
        driver.findElement(By.xpath("//*[local-name()='svg' and @id='popup-widget307423-close-icon']")).click();

        WebElement scrollToElement = driver.findElement(By.xpath("//span[text()='Slider Challenge: Select Worcestershire']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", scrollToElement);
        WebElement iFrameElement = driver.findElement(By.xpath("//div[@id='bs-7']/descendant::iframe"));
        driver.switchTo().frame(iFrameElement);
        WebElement tCounty = driver.findElement(By.id("tCounty"));
        Select select = new Select(tCounty);
        select.selectByValue("OK");

        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Tyne and Wear");
    }

    @Test
    public void paytmCalender()
    {
        String monAndYear = "August 2026";
        String date = "1";
        driver.get(paytmUrl);
        driver.findElement(By.id("departureDate")).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement scrollEle = driver.findElement(By.xpath("//span[text()='Depart']"));
        js.executeScript("arguments[0].scrollIntoView(true)", scrollEle);
        while (true)
        {
            try
            {
                WebElement ele = driver.findElement(By.xpath("//td[contains(text(),'" + monAndYear + "')]/ancestor::table/descendant::div[@class='calendar__day']/div[text()='" + date + "']"));
                ele.click();
                break;
            } catch (ElementNotInteractableException e)
            {
                driver.findElement(By.xpath("//i[@class='fCLnC _1MMxa']")).click();
            }

        }
    }

    public List<String> returnSortedList(List<String> list1)
    {
        List<String> list2 = new ArrayList<>(list1);
        for (int i = 0; i < list2.size(); i++)
        {
            for (int j = i + 1; j < list2.size(); j++)
            {
                if (list2.get(i).compareTo(list2.get(j)) > 0)
                {
                    String temp = list2.get(j);
                    list2.set(j, list2.get(i));
                    list2.set(i, temp);
                }
            }
        }
        return list2;
    }

    public List<String> fetchDataFromExcel(String path) throws IOException
    {
        List<String> li = new ArrayList<>();
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);

        Workbook wb = WorkbookFactory.create(fis);
        Sheet sheet = wb.getSheet("sheet1");
        int rowNum = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < rowNum; i++)
        {
            String string = sheet.getRow(i).getCell(0).getStringCellValue();
            li.add(string);
        }
        return li;
    }

    public boolean checkSame(List<String> list1, List<String> list2)
    {
        boolean flag = true;
        if (list1.size() != list2.size())
        {
            flag = false;
            return flag;
        }
        for (int i = 0; i < list1.size(); i++)
        {
            if (!(list1.get(i).equals(list2.get(i))))
            {
                flag = false;
                return flag;
            }
        }
        return flag;
    }

    @Test
    public void brokenLinks()
    {
        driver.get("https://onlinesbi.sbi.bank.in/");
        List<WebElement> links = driver.findElements(By.xpath("//a"));
        for(WebElement link : links)
        {
            String s = link.getAttribute("href");
            try{
                URL url = new URL(s);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                int responseCode = connection.getResponseCode();
                if(responseCode>400)
                {
                    System.out.println(responseCode+" link : "+ s);
                }
            } catch (IOException e)
            {
                System.out.println("Link is Malformed");
            }
            catch (Exception e)
            {
                System.out.println("Exception");
            }
        }
        driver.close();

    }
    @AfterClass
    public void tearDown() throws InterruptedException
    {
        Thread.sleep(4000);
        if (driver != null) {
            driver.quit();
        }
    }
}
