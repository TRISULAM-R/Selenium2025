package seleniumPractice;

import Pages.SeleniumTrickySimulationPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;

public class EyGrooming2
{
    WebDriver driver;
    SeleniumTrickySimulationPage seleniumTrickySimulationPage = null;

    String url = "https://testautomationpractice.blogspot.com/";
    String selectorsHuburl = "https://selectorshub.com/xpath-practice-page/";
    String candyMapperUrl = "https://candymapper.com/";
    String paytmUrl = "https://paytm.com/";
    String downloadPath = "C:\\Users\\User.TYSS-TEJAS\\Downloads";  // change to your folder


    ChromeOptions options = new ChromeOptions();
    @BeforeClass
    public void setup()
    {


        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadPath);
        prefs.put("download.prompt_for_download", false);
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("safebrowsing.enabled", false);  // disables Chrome safe browsing
        prefs.put("safebrowsing.disable_download_protection", true);


        options.setExperimentalOption("prefs", prefs);

    }

    @Test
    public void testDataFromExcelIsSorted() throws IOException
    {
        String s = "./src/test/resources/testData/TestDataEy2.xlsx";
        List<String> list =fetchDataFromExcel(s);
        List<String> list2 = sortTheList(list);
        System.out.println(compareLists(list,list2));
    }
    @Test
    public void testPagination()
    {
        String url = "https://testautomationpractice.blogspot.com/";
        String productName = "Soundbar";

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(url);

        for(int i=1; ;i++)
        {
            List<WebElement> elements = driver.findElements(By.xpath("//table[@id='productTable']/descendant::tr/td[2]"));
            boolean flag = false;
            for (WebElement ele : elements)
            {
                String s = ele.getText();
                if (productName.equalsIgnoreCase(s))
                {
                    String price = driver.findElement(By.xpath("//td[text()='" + productName + "']/following-sibling::td[1]")).getText();
                    driver.findElement(By.xpath("//td[text()='" + productName + "']/following-sibling::td/input")).click();
                    System.out.println(price);
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
                driver.findElement(By.xpath("//ul[@id='pagination']/descendant::a[text()='"+(i+1)+"']")).click();
            } catch (NoSuchElementException e)
            {
                System.out.println("No Such Record Found");
            }
        }
    }


    public static List<String> fetchDataFromExcel(String path) throws IOException
    {
        List<String> list = new ArrayList<>();
        Workbook wb = null;
        try
        {
            FileInputStream fis = new FileInputStream(path);
            wb = WorkbookFactory.create(fis);
            Sheet sheet = wb.getSheet("sheet1");
            int rowNum = sheet.getPhysicalNumberOfRows();
            for(int i=0; i<rowNum; i++)
            {
                String s = sheet.getRow(i).getCell(0).toString();
                list.add(s);
            }
        } catch (IOException e)
        {
            throw new RuntimeException("Exception Occured");
        }
        finally
        {
            if(wb != null)
            {
                wb.close();
            }
        }
        return list;
    }

    public static List<String> sortTheList(List<String> list)
    {
        List<String> list2 = new ArrayList<>(list);
        for(int i=0; i<list2.size();i++)
        {
            for(int j=i+1; j<list2.size();j++)
            {
                if(list2.get(i).compareTo(list2.get(j))>0)
                {
                    String temp = list2.get(j);
                    list2.set(j, list2.get(i));
                    list2.set(i, temp);
                }
            }
        }
        return list2;
    }
    public static boolean compareLists(List<String> list1, List<String> list2)
    {
        System.out.println(list1);
        System.out.println(list2);
        if(list1.size() != list2.size())
        {
            return false;
        }
        boolean flag = true;
        for(int i=0; i<list1.size();i++)
        {
            if(!(list1.get(i).equals(list2.get(i))))
            {
                flag = false;
                break;
            }
        }
        return flag;
    }

    @Test
    public void fetchDataFromStaticTable() throws IOException
    {
        Random ran = new Random();
        int ranNum = ran.nextInt(1000);
        System.out.println("ranNum : "+ranNum);
        File file = new File("src/test/resources/testData/TestData"+ranNum+".xlsx");
        XSSFWorkbook wb = new XSSFWorkbook();
        Sheet sh = wb.createSheet("sheet1");


            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
            driver.get("https://testautomationpractice.blogspot.com/");
            List<WebElement> list = driver.findElements(By.xpath("//table[@name='BookTable']/tbody/tr"));
            System.out.println(list.size());
            for (int i = 0; i < list.size(); i++)
            {
                Row row = sh.createRow(i);
                List<WebElement> elements = list.get(i).findElements(By.tagName("td"));
                for (int j = 0; j < elements.size(); j++)
                {
                    String s = elements.get(j).getText();
                    row.createCell(j).setCellValue(s);
                }
            }
            FileOutputStream fos = new FileOutputStream(file);
            wb.write(fos);
            fos.close();
            wb.close();
    }

    @Test
    public void fetchDataFromStaticTable2() {

        int ranNum = new Random().nextInt(1000);

        String filePath = "src/test/resources/testData/EYTestData"+ranNum+".xlsx";
        String url = "https://testautomationpractice.blogspot.com/";
        String tableXpath = "//table[@name='BookTable']/tbody/tr";

        try (XSSFWorkbook wb = new XSSFWorkbook();
              FileOutputStream fos = new FileOutputStream(filePath)) {

            Sheet sheet = wb.createSheet("sheet1");

            WebDriver driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
            driver.get(url);
            List<WebElement> rows = driver.findElements(By.xpath(tableXpath));

            for (int i = 0; i < rows.size(); i++) {

                Row excelRow = sheet.createRow(i);
                List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
                for (int j = 0; j < cols.size(); j++) {
                    excelRow.createCell(j).setCellValue(cols.get(j).getText());
                }
            }
            wb.write(fos);
            driver.quit();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
            e.printStackTrace();

        } catch (IOException e) {
            System.out.println("IO Error while working with Excel file.");
            e.printStackTrace();

        } catch (Exception e) {
            System.out.println("Unexpected error occurred.");
            e.printStackTrace();
        }
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
