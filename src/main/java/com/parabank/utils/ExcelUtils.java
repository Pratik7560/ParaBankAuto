package com.parabank.utils;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    public static Object[][] getTestData(String sheetName) {
        String filePath = ConfigReader.getProperty("excelPath");
        Object[][] data = null;

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet named '" + sheetName + "' not found in Excel file at: " + filePath);
            }

            int rowCount = sheet.getLastRowNum();
            Row headerRow = sheet.getRow(0);

            if (headerRow == null) {
                throw new RuntimeException("Header row is missing in sheet: " + sheetName);
            }

            int colCount = headerRow.getLastCellNum();
            data = new Object[rowCount][colCount];

            DataFormatter formatter = new DataFormatter();

            for (int i = 0; i < rowCount; i++) {
                Row row = sheet.getRow(i + 1);
                for (int j = 0; j < colCount; j++) {
                    if (row == null) {
                        data[i][j] = "";
                    } else {
                        Cell cell = row.getCell(j);
                        data[i][j] = (cell == null) ? "" : formatter.formatCellValue(cell);
                    }
                }
            }
            Log.info("Successfully fetched " + rowCount + " rows of test data from sheet: " + sheetName);

        } catch (IOException e) {
            Log.error("Error reading Excel file: " + e.getMessage());
            throw new RuntimeException("Failed to read Excel file at: " + filePath, e);
        }
        return data;
    }
}