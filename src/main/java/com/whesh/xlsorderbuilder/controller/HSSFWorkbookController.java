package com.whesh.xlsorderbuilder.controller;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class HSSFWorkbookController {

    private HSSFWorkbookController(){

    }

    public static HSSFWorkbook readFile(File filename) throws IOException {
        try (FileInputStream fis = new FileInputStream(filename)) {
            return new HSSFWorkbook(fis);        // NOSONAR - should not be closed here
        }
    }

}
