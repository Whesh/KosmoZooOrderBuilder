package com.whesh.xlsorderbuilder.controller;

import com.whesh.xlsorderbuilder.model.Order;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.imageio.stream.FileImageInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class OrderCopier {

//    private File orderFile;
//    private File priceFile;
    private Order order;
    private String priceOwner;

    public OrderCopier(String orderFilePath, String priceFilePath){
//        orderFile = new File(orderFilePath);
//        priceFile = new File(priceFilePath);
        this(new File(orderFilePath), new File(priceFilePath));


    }

    public OrderCopier(File orderFile, File priceFile){
//        this.orderFile = orderFile;
//        this.priceFile = priceFile;

        order = new Order(orderFile);

        PriceOwnerSelector priceOwnerSelector = new PriceOwnerSelector(priceFile);
        priceOwner = priceOwnerSelector.getPriceOwner();
    }

//    public File getPriceFile() {
//        return priceFile;
//    }

    public String getPriceOwner() {
        return priceOwner;
    }

    public Order getOrder(){
        return order;
    }
}
