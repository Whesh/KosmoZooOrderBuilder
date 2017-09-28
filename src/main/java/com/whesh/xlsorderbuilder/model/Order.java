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

    public Order(File orderFile, AbstractPrice orderPrice) {

        try (HSSFWorkbook hssfWorkbook = HSSFWorkbookController.readFile(orderFile)){
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);

            for (Row row : hssfSheet){
                Cell idCell = row.getCell(orderPrice.getProductIdCellNumber());
                Cell colsCell = row.getCell(orderPrice.getProductCollsCellNumber());
                if (idCell != null && colsCell != null){
                    String id = HSSFWorkbookController.getCellValue(idCell);
                    String cols = HSSFWorkbookController.getCellValue(colsCell);
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

}
