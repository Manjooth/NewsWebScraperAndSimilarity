import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class PopulateExcel {

    public static void insertData(ArrayList<String> sky, ArrayList<String> bbc, String spreadsheetName){
        try{
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet(spreadsheetName);
            String[] columnHeadings = {"Article", "Source"};

            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < columnHeadings.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columnHeadings[i]);
            }

            int rowNum = 1;
            for (int i = 0; i < sky.size(); i++) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(sky.get(i));
                row.createCell(1).setCellValue("Sky");
            }

            for (int i = 0; i < sky.size(); i++) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(bbc.get(i));
                row.createCell(1).setCellValue("BBC");
            }

            FileOutputStream fileOut = new FileOutputStream("/home/manjooth/Documents/" + spreadsheetName + ".xlsx");
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            System.out.println("Completed writing to excel spreadsheet");
        }catch(Exception exception){
            System.out.println("An error has occurred " + exception);
            exception.printStackTrace();
        }
    }

    public static void insertData(ArrayList<String> popularStories, String spreadsheetName){
        try{
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet(spreadsheetName);
            String[] columnHeadings = {"Article"};

            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < columnHeadings.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columnHeadings[i]);
            }

            int rowNum = 1;
            for (int i = 0; i < popularStories.size(); i++) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(popularStories.get(i));
            }

            FileOutputStream fileOut = new FileOutputStream("/home/manjooth/Documents/" + spreadsheetName + ".xlsx");
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            System.out.println("Completed writing popular news titles");
        }catch(Exception exception){
            System.out.println("An error has occurred " + exception);
            exception.printStackTrace();
        }
    }
}
