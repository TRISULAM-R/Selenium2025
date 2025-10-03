package Utils;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ExcelFileUtility
{

    public static Object[][] getData(String filePath, String sheetName) {
        Object[][] data = null;
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            int rowNum = sheet.getPhysicalNumberOfRows();
            int cellNum = sheet.getRow(0).getPhysicalNumberOfCells();

            data = new Object[rowNum - 1][cellNum];

            for (int i = 1; i < rowNum; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < cellNum; j++) {
                    data[i - 1][j] = row.getCell(j).getStringCellValue();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void writeStatus(String filePath, String sheetName, int rowIndex, int colIndex, String status) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(rowIndex);
            if (row == null) row = sheet.createRow(rowIndex);

            Cell cell = row.getCell(colIndex);
            if (cell == null) cell = row.createCell(colIndex);

            cell.setCellValue(status);

            // Optional: Add color formatting
            CellStyle style = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            if ("Pass".equalsIgnoreCase(status)) {
                font.setColor(IndexedColors.GREEN.getIndex());
            } else if ("Fail".equalsIgnoreCase(status)) {
                font.setColor(IndexedColors.RED.getIndex());
            }
            style.setFont(font);
            cell.setCellStyle(style);

            // Save changes
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void writeStatus(String filePath, String sheetName, String userName, String password, String status) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {


            Sheet sheet = workbook.getSheet(sheetName);
            int lastRow = sheet.getPhysicalNumberOfRows(); // returns highest index of used rows
            Row row = sheet.createRow(lastRow);

            row.createCell(0).setCellValue(userName);
            row.createCell(1).setCellValue(password);

            row.createCell(2).setCellValue(status);

            // Save changes
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

