package com.whesh.xlsorderbuilder.model;

public class PriceValta extends AbstractPrice {

    @Override
    protected void setSellerName() {
        sellerName = "Валта";
    }

    @Override
    protected void setProductArticulCellNumber() {
        // Cell number set as Excel number - 1
        productArticulCellNumber = 3;
    }

    @Override
    protected void setProductColsCellNumber() {
        // Cell number set as Excel number - 1
        productColsCellNumber = 6;
    }

    @Override
    protected void setProductNameCellNumber() {
        productNameCellNumber = 1;
    }

    @Override
    protected void setProductIdCellNumber() {
        productIdCellNumber = 0;
    }
}
