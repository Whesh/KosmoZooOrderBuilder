package com.whesh.xlsorderbuilder.model;

import com.whesh.xlsorderbuilder.controller.HSSFWorkbookController;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Reporter {

    private Map<String, Boolean> reportList = new HashMap<>();
    private File orderFile;
    private File reportFile;
    private PriceOrder priceOrder;
    private String sellerName;
    private Map<String, Double> orderMap;

    private HSSFWorkbook reportBook;

    public Reporter(File orderFile, PriceOrder priceOrder, String sellerName, Map<String, Double> orderMap){
        this.orderFile = orderFile;
        this.priceOrder = priceOrder;
        this.sellerName = sellerName;
        this.orderMap = orderMap;

        String workDirPath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        workDirPath = workDirPath.substring(0, workDirPath.lastIndexOf("/"));
        workDirPath = workDirPath.replaceAll("%20"," ");

//                new File(ClassLoader.getSystemClassLoader().getResource(".").getPath())
//                .getAbsolutePath();

        File reportDir = new File(workDirPath + "/reports/");

        if (!reportDir.isDirectory()){
            reportDir.mkdir();
        }

        reportFile = new File(reportDir.toURI().getPath() +"/report_"
                    + sellerName
                    + "_"
                    + System.currentTimeMillis()
                    + ".xls");
        try {
            reportFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        reportBook = new HSSFWorkbook();

    }

    public void append(String itemId, boolean founded){
        if (reportList.containsKey(itemId)){
            if (!reportList.get(itemId) && founded){
                reportList.put(itemId, founded);
            }
        } else {
            reportList.put(itemId, founded);
        }
    }

    public String getReport(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Следующие артикулы отсутствуют в прайсе назначения: \n");
        for (Map.Entry<String, Boolean> entry : reportList.entrySet()){
            if (!entry.getValue()){
                stringBuilder.append("Артикул: " + entry.getKey() + " не найден. \n");
            }
        }

            reportBook.createSheet();
            reportBook = setTitle(reportBook);
            reportBook = setTable(reportBook);
            reportBook = setContent(reportBook);

        try (FileOutputStream fos = new FileOutputStream(reportFile)){

            reportBook.write(fos);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reportBook != null){
                try {
                    reportBook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return stringBuilder.toString();
    }

    private HSSFWorkbook setTitle(HSSFWorkbook reportBook){
        HSSFSheet hssfSheet = reportBook.getSheetAt(0);

        HSSFFont hssfFont = reportBook.createFont();
        hssfFont.setBold(true);
        hssfFont.setFontHeightInPoints((short)(14));

        Row row = hssfSheet.createRow(0);
        CellStyle cellStyle = reportBook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setFont(hssfFont);

        for (int i = 0; i < 3; i++){
            row.createCell(i);
        }
        CellRangeAddress rangeAddress = new CellRangeAddress(0, 0, 0, 3);
        hssfSheet.addMergedRegion(rangeAddress);

        row.getCell(0).setCellValue("Не найденные артикулы");
        row.getCell(0).setCellStyle(cellStyle);

        row = hssfSheet.createRow(1);

        for (int i = 0; i < 4; i++){
            row.createCell(i);
        }
        rangeAddress.setFirstRow(1);
        rangeAddress.setLastRow(1);
        hssfSheet.addMergedRegion(rangeAddress);

        row.getCell(0).setCellValue(sellerName);
        row.getCell(0).setCellStyle(cellStyle);

        return reportBook;
    }

    private HSSFWorkbook setTable(HSSFWorkbook reportBook){
        HSSFSheet hssfSheet = reportBook.getSheetAt(0);

        HSSFFont hssfFont = reportBook.createFont();
        hssfFont.setBold(true);
        hssfFont.setFontHeightInPoints((short)(10));

        CellStyle cellStyle = reportBook.createCellStyle();
        cellStyle.setBorderBottom(BorderStyle.MEDIUM);
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);
        cellStyle.setBorderRight(BorderStyle.MEDIUM);
        cellStyle.setBorderTop(BorderStyle.MEDIUM);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setFont(hssfFont);

        Row row = hssfSheet.createRow(2);

        Cell cellId = row.createCell(0);
        cellId.setCellValue("Код");

        cellId.setCellStyle(cellStyle);

        Cell cellArticul = row.createCell(1);
        cellArticul.setCellValue("Артикул");

        cellArticul.setCellStyle(cellStyle);

        Cell cellItemName = row.createCell(2);
        cellItemName.setCellValue("Наименование товара");
        cellItemName.setCellStyle(cellStyle);

        Cell cellOrderCols = row.createCell(3);
        cellOrderCols.setCellValue("Количество");
        cellOrderCols.setCellStyle(cellStyle);

        int maxNumCharacters = HSSFWorkbookController.getCellValue(cellItemName).length();
        for (int i = 0; i < 4; i++){
            int width = ((int)(maxNumCharacters * 1.3)) * 256;
            hssfSheet.setColumnWidth(i, width);
        }

        hssfSheet.setColumnWidth(2, (int)((70 * 1.3) * 256));

        return reportBook;
    }

    private HSSFWorkbook setContent(HSSFWorkbook reportBook){

        int workingRow = 3;

        try (HSSFWorkbook orderBook = HSSFWorkbookController.readFile(orderFile)){

            HSSFSheet reportSheet = reportBook.getSheetAt(0);
            HSSFSheet orderSheet = orderBook.getSheetAt(0);

            HSSFFont hssfFont = reportBook.createFont();
            hssfFont.setFontHeightInPoints((short)(8));

            CellStyle cellStyle = reportBook.createCellStyle();
            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setBorderTop(BorderStyle.THIN);
            cellStyle.setFont(hssfFont);

            for (Map.Entry<String, Boolean> entry : reportList.entrySet()){
                if (!entry.getValue()){
                    Row row = reportSheet.createRow(workingRow);
                    row.createCell(0);
                    row.createCell(1).setCellValue(entry.getKey());
                    row.createCell(2);
                    row.createCell(3);

                    row.getCell(0).setCellStyle(cellStyle);
                    row.getCell(1).setCellStyle(cellStyle);
                    row.getCell(2).setCellStyle(cellStyle);
                    row.getCell(3).setCellStyle(cellStyle);

                    for (Row orderRow : orderSheet){
                        Cell idCell = orderRow.getCell(priceOrder.getProductArticulCellNumber());

                        if (idCell != null){
                            String id = HSSFWorkbookController.getCellValue(idCell);
                            if (id.equals(entry.getKey())){
                                row.getCell(0)
                                        .setCellValue(HSSFWorkbookController
                                                .getCellValue(orderRow
                                                        .getCell(priceOrder.getProductIdCellNumber())));
                                row.getCell(2)
                                        .setCellValue(HSSFWorkbookController
                                                .getCellValue(orderRow
                                                        .getCell(priceOrder.getProductNameCellNumber())));
                                row.getCell(3)
                                        .setCellValue(orderMap.get(entry.getKey()));
                            }
                        }

                    }
                    workingRow++;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return reportBook;
    }

}
