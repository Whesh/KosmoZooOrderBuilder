package com.whesh.xlsorderbuilder.controller;

import jdk.nashorn.internal.runtime.NumberToString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class HSSFWorkbookController {

    private HSSFWorkbookController(){

    }

    public static HSSFWorkbook readFile(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            return new HSSFWorkbook(fis);        // NOSONAR - should not be closed here
        }
    }

    public static String getCellValue(Cell cell){
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
