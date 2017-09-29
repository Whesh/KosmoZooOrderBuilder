package com.whesh.xlsorderbuilder.controller;

import com.whesh.xlsorderbuilder.model.AbstractPrice;
import com.whesh.xlsorderbuilder.model.Order;
import com.whesh.xlsorderbuilder.model.PriceOrder;
import com.whesh.xlsorderbuilder.model.Reporter;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class OrderCopier {

//    private File orderFile;
    private File priceFile;
    private Order order;
    private AbstractPrice price;
//    private String priceOwner;

    public OrderCopier(String orderFilePath, String priceFilePath){
//        orderFile = new File(orderFilePath);
//        priceFile = new File(priceFilePath);
        this(new File(orderFilePath), new File(priceFilePath));


    }

    public OrderCopier(File orderFile, File priceFile){
//        this.orderFile = orderFile;
        this.priceFile = priceFile;

        order = new Order(orderFile, new PriceOrder());
        price = new PriceOwnerSelector(priceFile).getPrice();

//        PriceOwnerSelector priceOwnerSelector = new PriceOwnerSelector(priceFile);
//        priceOwner = priceOwnerSelector.getPriceOwner();
    }

//    public File getPriceFile() {
//        return priceFile;
//    }

    private String copyOrderToOwnerPrice(File priceFile, Map<String, Double> orderMap){
//        StringBuilder reportBuilder = new StringBuilder();
//        String report = new String();
        Reporter reporter = new Reporter();

        try (HSSFWorkbook hssfWorkbook = HSSFWorkbookController.readFile(priceFile)){

            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);


            for (Map.Entry<String, Double> entry : orderMap.entrySet()){

                for (Row row : hssfSheet){
                    Cell idCell = row.getCell(price.getProductIdCellNumber());
                    Cell colsCell = row.getCell(price.getProductColsCellNumber());

                    if (idCell != null){
                        String id = HSSFWorkbookController.getCellValue(idCell);
                        if (id.equals(entry.getKey())){
                            // do copy value in cols cell
                            if (colsCell == null){
                                colsCell = row.createCell(price.getProductColsCellNumber());
                            }
                            colsCell.setCellValue(entry.getValue());
                            reporter.append(entry.getKey(), true);
                        } else {
                            reporter.append(entry.getKey(), false);
                        }
                    }

                }

                //Create logging of failing
//                if (prevoisItemId.equals(entry.getKey())){
//                    continue;
//                } else {
//                    reportBuilder.append("ID: " + entry.getKey() + " not found. \n");
//                    System.out.println("ID: " + entry.getKey() + " not found. \n");
//                    prevoisItemId = entry.getKey();
//                }

            }


            try (FileOutputStream fos = new FileOutputStream(priceFile)){
                hssfWorkbook.write(fos);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

//        String report = "OK";
//        String report = new String(reportBuilder);
        return price.getSellerName() + ":\n" + reporter.getReport();
    }

    public String commit(){
        return copyOrderToOwnerPrice(priceFile, order.getOrderList());
    }

//    public String getPriceOwner() {
//        return priceOwner;
//    }

    public Order getOrder(){
        return order;
    }
}
