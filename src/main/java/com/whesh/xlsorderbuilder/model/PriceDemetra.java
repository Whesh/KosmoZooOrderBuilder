package com.whesh.xlsorderbuilder.model;

public class PriceDemetra extends AbstractPrice{
    @Override
    protected void setSellerName() {
        sellerName = "«Компания ДЕМЕТРА»";
    }

    @Override
    protected void setProductArticulCellNumber() {
        productArticulCellNumber = 3;
    }

    @Override
    protected void setProductColsCellNumber() {
        productColsCellNumber = 12;
    }

    @Override
    protected void setProductNameCellNumber() {
        productNameCellNumber = 1;
    }

    @Override
    protected void setProductIdCellNumber() {
        productIdCellNumber = 2;
    }
}
