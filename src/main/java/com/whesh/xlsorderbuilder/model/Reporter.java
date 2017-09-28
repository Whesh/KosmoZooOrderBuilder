package com.whesh.xlsorderbuilder.model;

import java.util.HashMap;
import java.util.Map;

public class Reporter {

    private Map<String, Boolean> reportList = new HashMap<>();

    public Reporter(){

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

        return stringBuilder.toString();
    }

}
