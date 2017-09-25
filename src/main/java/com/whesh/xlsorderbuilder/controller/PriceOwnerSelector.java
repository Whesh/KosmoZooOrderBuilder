package com.whesh.xlsorderbuilder.controller;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PriceOwnerSelector {

    private String priceOwner;

    public PriceOwnerSelector(File priceFile){

        try (HSSFWorkbook workbook = HSSFWorkbookController.readFile(priceFile)){

            HSSFSheet hssfSheet = workbook.getSheetAt(0);
            HSSFRow hssfRow = hssfSheet.getRow(2);
            HSSFCell hssfCell = hssfRow.getCell(1);
            priceOwner = hssfCell.getStringCellValue();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getPriceOwner() {
        return priceOwner;
    }
}
