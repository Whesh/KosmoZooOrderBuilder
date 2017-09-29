package com.whesh.xlsorderbuilder.model;

public class PriceDemetra extends AbstractPrice{
    @Override
    protected void setSellerName() {
        sellerName = "Общество с ограниченной ответственностью «Компания ДЕМЕТРА»";
    }

    @Override
    protected void setProductIdCellNumber() {
        productIdCellNumber = 3;
    }

    @Override
    protected void setProductColsCellNumber() {
        productColsCellNumber = 12;
    }
}
