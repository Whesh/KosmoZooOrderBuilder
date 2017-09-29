package com.whesh.xlsorderbuilder.model;

public class PriceValta extends AbstractPrice {

    @Override
    protected void setSellerName() {
        sellerName = "Валта";
    }

    @Override
    protected void setProductIdCellNumber() {
        // Cell number set as Excel number - 1
        productIdCellNumber = 3;
    }

    @Override
    protected void setProductColsCellNumber() {
        // Cell number set as Excel number - 1
        productColsCellNumber = 6;
    }
}
