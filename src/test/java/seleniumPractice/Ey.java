package seleniumPractice;

import Pages.SeleniumTrickySimulationPage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.time.Duration;
import java.util.*;

public class Ey
{
    public static void main(String[] args) throws IOException
    {
        fetchValue("","Akash");
    }

    public static String fetchValue(String path, String expectedName) throws IOException
    {
        FileInputStream fis = new FileInputStream(path);
        Workbook wb = WorkbookFactory.create(fis);
        Sheet sh = wb.getSheet("sheet1");
        int rowCount = sh.getPhysicalNumberOfRows();
        String value = "";
        for (int i = 0; i < rowCount; i++)
        {
            int cellCount = sh.getRow(i).getPhysicalNumberOfCells();
            for (int j = 0; j < cellCount; j++)
            {
                String name = sh.getRow(i).getCell(0).getStringCellValue();
                if (expectedName.equalsIgnoreCase(name))
                {
                    value = sh.getRow(i).getCell(3).getStringCellValue();
                }
            }
        }
        return value;
    }
}
