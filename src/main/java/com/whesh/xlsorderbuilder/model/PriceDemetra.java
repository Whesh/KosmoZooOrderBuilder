package com.whesh.xlsorderbuilder.model;

public class PriceDemetra extends AbstractPrice{
    @Override
    public void setSellerName() {
        sellerName = "Общество с ограниченной ответственностью «Компания ДЕМЕТРА»";
    }

    @Override
    public void setProductIdCellNumber() {
        productIdCellNumber = 3;
    }

    @Override
    public void setProductColsCellNumber() {
        productColsCellNumber = 12;
    }
}
