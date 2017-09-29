package com.whesh.xlsorderbuilder.model;

public class PriceValta extends AbstractPrice {

    @Override
    public void setSellerName() {
        sellerName = "Валта";
    }

    @Override
    public void setProductIdCellNumber() {
        // Cell number set as Excel number - 1
        productIdCellNumber = 3;
    }

    @Override
    public void setProductColsCellNumber() {
        // Cell number set as Excel number - 1
        productColsCellNumber = 6;
    }
}
