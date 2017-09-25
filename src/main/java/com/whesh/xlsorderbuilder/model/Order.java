package com.whesh.xlsorderbuilder.model;

import com.whesh.xlsorderbuilder.controller.HSSFWorkbookController;
import jdk.nashorn.internal.runtime.NumberToString;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Order {

    private Map<String, Double> orderList = new HashMap<>();

    public Order(File orderFile) {

        try (HSSFWorkbook hssfWorkbook = HSSFWorkbookController.readFile(orderFile)){
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);

            for (Row row : hssfSheet){
                Cell idCell = row.getCell(3);
                Cell colsCell = row.getCell(9);
                if (idCell != null && colsCell != null){
                    String id = getCellValue(idCell);
                    String cols = getCellValue(colsCell);
                    if (id.equals("") || id == null || cols.equals("") || cols == null){
                        continue;
                    } else {
                        orderList.put(id, Double.valueOf(cols));
                    }
                } else {
                    continue;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public Map<String, Double> getOrderList() {
        return orderList;
    }

    private String getCellValue(Cell cell){
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return String.valueOf(cell.getRichStringCellValue().getString());
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return String.valueOf(cell.getDateCellValue());
                } else {
                    return NumberToString.stringFor(cell.getNumericCellValue());
                }
            case Cell.CELL_TYPE_BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case Cell.CELL_TYPE_FORMULA:
                return String.valueOf(cell.getCellFormula());
            case Cell.CELL_TYPE_ERROR:
                return String.valueOf("");
            case Cell.CELL_TYPE_BLANK:
                return String.valueOf("");
            default:
                return String.valueOf("");
        }
    }
}
