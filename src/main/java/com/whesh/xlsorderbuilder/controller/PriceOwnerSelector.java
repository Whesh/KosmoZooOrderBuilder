package com.whesh.xlsorderbuilder.controller;

import com.whesh.xlsorderbuilder.model.AbstractPrice;
import com.whesh.xlsorderbuilder.model.PriceDemetra;
import com.whesh.xlsorderbuilder.model.PriceValta;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.IOException;

public class PriceOwnerSelector {

//    private String priceOwner;
    private AbstractPrice detectedPrice;

    public PriceOwnerSelector(File priceFile){

        try (HSSFWorkbook workbook = HSSFWorkbookController.readFile(priceFile)){

            HSSFSheet hssfSheet = workbook.getSheetAt(0);
            if (hssfSheet.getRow(2).getCell(1) != null){
                if (HSSFWorkbookController.getCellValue(hssfSheet
                        .getRow(2)
                        .getCell(1)).equals("ЗАО \"Валта Пет Продактс\"")){
                    detectedPrice = new PriceValta();
                }
            } else if (hssfSheet.getRow(3).getCell(1) != null){
                if (HSSFWorkbookController.getCellValue(hssfSheet
                        .getRow(3)
                        .getCell(1)).equals("Общество с ограниченной ответственностью «Компания ДЕМЕТРА»")){
                    detectedPrice = new PriceDemetra();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    public String getPriceOwner() {
//        return priceOwner;
//    }

    public AbstractPrice getPrice(){
        return detectedPrice;
    }
}
