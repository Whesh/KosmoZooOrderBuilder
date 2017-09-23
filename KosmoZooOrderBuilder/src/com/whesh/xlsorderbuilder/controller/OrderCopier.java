package com.whesh.xlsorderbuilder.controller;

import javax.imageio.stream.FileImageInputStream;
import java.io.File;

public class OrderCopier {

    private File orderFile;
    private File priceFile;

    public OrderCopier(String orderFilePath, String priceFilePath){
//        orderFile = new File(orderFilePath);
//        priceFile = new File(priceFilePath);
        this(new File(orderFilePath), new File(priceFilePath));
    }

    public OrderCopier(File orderFile, File priceFile){
        this.orderFile = orderFile;
        this.priceFile = priceFile;
    }

    public File getPriceFile() {
        return priceFile;
    }
}
